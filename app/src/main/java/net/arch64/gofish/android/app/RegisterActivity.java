package net.arch64.gofish.android.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gofish.R;

import net.arch64.gofish.android.client.Client;
import net.arch64.gofish.android.users.User;

public class RegisterActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button submittBtn = (Button)findViewById(R.id.submittBtn);
        submittBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fnameText = (EditText) findViewById(R.id.fnameText);
                EditText lnameText = (EditText) findViewById(R.id.lnameText);
                EditText emailText = (EditText) findViewById(R.id.emailText);
                EditText passText = (EditText) findViewById(R.id.passText);
                EditText reenterPassText = (EditText) findViewById(R.id.reenterPassText);

                String fname = fnameText.getText().toString();
                String lname = lnameText.getText().toString();
                String email = emailText.getText().toString();
                String pass = passText.getText().toString();
                String reenterPass = reenterPassText.getText().toString();

                //check passwords and parameters against database requirements
                Client client = new Client("10.0.2.2", 12345);
                client.authenticate("test", pass);
                //client.msgServer();
                client.close();
            }
        });
    }

}
