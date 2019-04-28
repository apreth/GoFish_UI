package net.arch64.gofish.android.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.gofish.R;

import java.util.ArrayList;

public class ForumActivity extends AppCompatActivity {

    private ListView lv;
    public static ArrayList<Model> modelArrayList;
    private CustomAdapter customAdapter;
    //private Button btnnext;
    private String[] userList = new String[]{"Rory: I like fishing", "Austin: IDK how to fish", "Joe: I want to play counter strike", "Chris: Where is Megan","Megan: ... jjjjjjjkkkilvlujvyut vuvuvuyvbyvvjhjvhvjhv jhvjhvjhvjj vvvvvvvvv vvvv", "Courtney: Sunshine","Erin: I hurt my back","Samuel: I like to use big words","Tyler: I am better than you","Liam: Socialism isn't enough","Colleen: I like running"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        lv = (ListView) findViewById(R.id.forumList);
        //setListViewHeightBasedOnChildren(lv);
       // btnnext = (Button) findViewById(R.id.next);

        modelArrayList = getModel();
        customAdapter = new CustomAdapter(this);
        lv.setAdapter(customAdapter);
        /**
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumActivity.this,NextActivity.class);
                startActivity(intent);
            }
        });
        **/
    }

    private ArrayList<Model> getModel(){
        ArrayList<Model> list = new ArrayList<>();
        for(int i = 0; i < userList.length; i++){

            Model model = new Model();
            model.setNumber(1);
            model.setFruit(userList[i]);
            list.add(model);
        }
        return list;
    }





    /**
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        ListView forumListView = (ListView) findViewById(R.id.forumList);
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

        ArrayList<String> forumList = new ArrayList<>();

        String[] names = {"Joe", "Chris", "Rory", "Austin", "Megan",
                "Benny", "Chrissy", "Yater", "Walter", "Bob",
                "Jennifer", "Penny", "Tristen", "Katie", "Owen",
                "Luke", "Pizza", "Obi Wan", "Darth Vader"};
        for (int i = 0; i < names.length; i++) {
            forumList.add(names[i]);
        }

        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, forumList);
        forumListView.setAdapter(listAdapter);
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
