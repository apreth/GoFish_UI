package net.arch64.gofish.android.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import net.arch64.gofish.android.client.Client;


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

        //TODO: call databse and get info
        Client client = new Client("10.0.2.2", 12345);
        //get info
        client.close();

        TextView fnameText = (TextView) findViewById(R.id.fnameTextView);
        TextView lnameText = (TextView) findViewById(R.id.lnameTextView);
        TextView emailText = (TextView) findViewById(R.id.emailTextView);
        TextView userNameText = (TextView) findViewById(R.id.userNameTextView);
/* add once pulled from database
        fnameText.setText();
        lnameText.setText();
        emailText.setText();
        userNameTextView.setText();
        reputation

        //if photo is null option

*/



Button forumBtn = (Button)findViewById(R.id.forumBtn);
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
