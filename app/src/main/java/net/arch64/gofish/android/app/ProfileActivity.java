package net.arch64.gofish.android.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import net.arch64.gofish.android.client.Client;
import net.arch64.gofish.android.users.User;


import com.example.gofish.R;

public class ProfileActivity extends AppCompatActivity {

    private RegisterActivity registerInstance;

    public ProfileActivity(RegisterActivity register){
        registerInstance = register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //User user = new User();

        //TODO: call databse and get info
        //Client client = new Client("10.0.2.2", 12345);//Connect to database and retreive necessary user info
        //get info
        //String fname = user.getFname();
        //String lname = user.getLname();
        //String email = user.getEmail();
        //String userName = user.getUsername();
        //client.close();//close connection

        TextView fnameText = (TextView) findViewById(R.id.fnameTextView);//find the text views so that they can be changed
        TextView lnameText = (TextView) findViewById(R.id.lnameTextView);
        TextView emailText = (TextView) findViewById(R.id.emailTextView);
        TextView userNameText = (TextView) findViewById(R.id.userNameTextView);

//add once pulled from database
        /*
        fnameText.setText(fname);//input info into each respective text view so that user sees their info
        lnameText.setText(lname);
        emailText.setText(email);
        userNameText.setText(userName);
        */
        //reputation

        //if photo is null option





Button forumBtn = (Button)findViewById(R.id.forumBtn);//return to forum page by clicking the forum button
        forumBtn.setOnClickListener(new View.OnClickListener() {//change to forum activity
            @Override
            public void onClick(View v) {
                //TODO uncomment once pushed
                //Intent forumIntent = new Intent(getApplicationContext(), ForumActivity.class);
                //startActivity(forumActivity);
            }
        });



    }
}
