package net.arch64.gofish.android.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


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
        String user = registerInstance.userName;

        //TODO: call databse and get info

        TextView fnameText = (TextView) findViewById(R.id.fnameTextView);
        TextView lnameText = (TextView) findViewById(R.id.lnameTextView);
        TextView emailText = (TextView) findViewById(R.id.emailTextView);
/* add once pulled from database
        fnameText.setText();
        lnameText.setText();
        emailText.setText();

        //if photo is null option

*/



Button forumBtn = (Button)findViewById(R.id.forumBtn);
        forumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: launch into forums
            }
        });



    }
}
