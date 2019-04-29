package net.arch64.gofish.android.app;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gofish.R;

import net.arch64.gofish.android.client.Client;
import net.arch64.gofish.android.client.Cookie;
import net.arch64.gofish.android.forums.Forum;
import net.arch64.gofish.android.forums.ForumAdapter;
import net.arch64.gofish.android.users.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ForumActivity extends AppCompatActivity {
    private LocationManager locMan;
    private LocationListener locLst;
    private TextView locTextView;
    private BottomNavigationView navigation;
    private ListView forumListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        locTextView = (TextView) findViewById(R.id.locTextView);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().getItem(0).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //Log.d("NAV_TITLE", menuItem.getTitle().toString());
                Intent intent = null;
                switch(menuItem.getTitle().toString()) {
                    case "Forums":
                        intent = new Intent(getApplicationContext(), ForumActivity.class);
                        break;
                    case "Make Forum Post":
                        intent = new Intent(getApplicationContext(), WritePostActivity.class);
                        break;
                    case "Profile Page":
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        break;
                    default:
                        intent = new Intent(getApplicationContext(), ForumActivity.class);
                        break;
                }
                menuItem.setChecked(true);
                if (intent != null) {
                    startActivity(intent);
                }
                return false;
            }
        });

        forumListView = (ListView) findViewById(R.id.forumList);
        setListViewHeightBasedOnChildren(forumListView);
        forumListView.setOnTouchListener(new OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

//        ArrayList<String> forumList = new ArrayList<>();
//
//        String[] names = {"Joe", "Chris", "Rory", "Austin", "Megan",
//                "Benny", "Chrissy", "Yater", "Walter", "Bob",
//                "Jennifer", "Penny", "Tristen", "Katie", "Owen",
//                "Luke", "Pizza", "Obi Wan", "Darth Vader", "" + Cookie.getUserId()};
//        for (int i = 0; i < names.length; i++) {
//            forumList.add(names[i]);
//        }

        //ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, forumList);
        //forumListView.setAdapter(listAdapter);

        locMan = (LocationManager) getSystemService(LOCATION_SERVICE);
        locLst = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //Log.d("LOCATION", location.toString());
                //Toast.makeText(getApplicationContext(), location.getLongitude() + " " + location.getLatitude(), Toast.LENGTH_LONG).show();
                getLocale(location.getLatitude(), location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(settingsIntent);
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);
                return;
            }
        }
        locMan.requestLocationUpdates("gps", 5000, 0, locLst);
    }

    public void getLocale(double lat, double lng) {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String loc = obj.getLocality() + ", " + obj.getAdminArea() + ", " + obj.getCountryCode();
            //Log.d("LOCATION-STRING", loc);
            //Toast.makeText(getApplicationContext(), loc, Toast.LENGTH_LONG).show();
            if (!locTextView.getText().equals(loc)) {
                locTextView.setText(loc);
            }

            Client client = new Client("10.0.2.2", 12345);
            ArrayList<Forum> forumList = client.forumsRequest(Cookie.getUserId(), obj.getCountryCode(), obj.getAdminArea(), obj.getLocality());
            client.close();

            Forum first = null;
            ForumAdapter adapter = (ForumAdapter)forumListView.getAdapter();

            if (adapter != null) {
                first = (Forum)adapter.getItem(0);
            }

            if (first != null && forumList.get(0).getId() != first.getId()) {
                ForumAdapter forumAdapter = new ForumAdapter(forumList, this);
                forumListView.setAdapter(forumAdapter);
            } else if (first == null) {
                ForumAdapter forumAdapter = new ForumAdapter(forumList, this);
                forumListView.setAdapter(forumAdapter);
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
