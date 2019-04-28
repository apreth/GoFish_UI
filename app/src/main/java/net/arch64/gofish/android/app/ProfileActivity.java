package net.arch64.gofish.android.app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import net.arch64.gofish.android.client.Client;
import net.arch64.gofish.android.client.Cookie;
import net.arch64.gofish.android.users.User;

import com.example.gofish.R;

public class ProfileActivity extends AppCompatActivity {
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().getItem(2).setChecked(true);
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

        // request user data from seasick
        Client client = new Client("10.0.2.2", 12345);
        User user = client.profilePageRequest(Cookie.getUserId());
        client.close();

        //find the text views so that they can be changed
        TextView fnameText = (TextView) findViewById(R.id.fnameTextView);
        TextView lnameText = (TextView) findViewById(R.id.lnameTextView);
        TextView emailText = (TextView) findViewById(R.id.emailTextView);
        TextView userNameText = (TextView) findViewById(R.id.userNameTextView);

        // set text views with appropriate info from the user
        fnameText.setText(user.getFname());
        lnameText.setText(user.getLname());
        emailText.setText(user.getEmail());
        userNameText.setText(user.getUsername());
    }
}