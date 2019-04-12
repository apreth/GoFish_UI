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
import java.util.regex.*;

import com.example.gofish.R;

public class RegisterActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private boolean ready = true;//flag for if all info is ready
    Pattern emailRegex = Pattern.compile("[A-Z a-z 0-9]+[@][A-Z a-z 0-9]+[.][A-Z a-z 0-9]+");

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


        final Button submittBtn = (Button)findViewById(R.id.submittBtn);
        submittBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ready = true;

                EditText fnameText = (EditText) findViewById(R.id.fnameText);//set up all text fields
                EditText lnameText = (EditText) findViewById(R.id.lnameText);
                EditText emailText = (EditText) findViewById(R.id.emailText);
                EditText passText = (EditText) findViewById(R.id.passText);
                EditText reenterPassText = (EditText) findViewById(R.id.reenterPassText);
                EditText userNameText = (EditText) findViewById(R.id.userNameText);

                emptyText(fnameText);//check length for validity
                emptyText(lnameText);
                emptyText(emailText);
                emptyText(passText);
                emptyText(reenterPassText);
                emptyText(userNameText);

                checkLength(128,fnameText);//check length of necessary fields
                checkLength(128,lnameText);
                checkLength(128,emailText);
                checkLength(128,passText);
                checkLength(16,userNameText);

                String fname = fnameText.getText().toString();//convert text to strings
                String lname = lnameText.getText().toString();
                String email = emailText.getText().toString();
                String pass = passText.getText().toString();
                String reenterPass = reenterPassText.getText().toString();
                String userName = userNameText.getText().toString();

                passCheck(pass, passText);//checks validity of password

                if(pass.equals(reenterPass) != true){
                    reenterPassText.setError("Passwords must match.");
                    ready = false;
                }

                if(ready == false){
                    submittBtn.setError("Please enter correct information above.");
                }
                else{//connect and verify it is a new account
                    //check if email exists, username exists

                }

                //check passwords and parameters against database requirements

            }
        });
    }

    public void emptyText(EditText field){
        if(field.length()==0) {
            field.setError("Information Missing");
            ready = false;
        }
    }
    public void emailCheck(EditText field, String email){
        Matcher emailMatch = emailRegex.matcher(email);

        if(!emailMatch.find()){
            ready = false;
            field.setError("Please enter a valid email.");
        }
    }
    public void passCheck(String password, EditText passText){
        Boolean capital = false;//flags for password conditions
        Boolean lower = false;
        Boolean number = false;
        Boolean special = false;

        int i;
        for(i = 0; i < password.length(); i++){//loop through string and check for conditions to password
            char value = password.charAt(i);
            if(value >= '!' && value <= '+')//special characters
                special = true;
            else if(value >= 'A' && value <= 'Z')//capital letters
                capital = true;
            else if(value >= 'a' && value <= 'z')//lower case letters
                lower = true;
            else if(value >= '0' && value <= '9')//number
                number = true;
        }

        if(capital == false || lower == false || number == false || special == false){//checks to see if all conditions are true
            ready = false;
            passText.setError("Pleas enter password follwoing the guidlines below.");//error message for invalid passwords
        }
    }
    public void checkLength(int length, EditText field){
        if(field.length() > length){
            field.setError("Value entered is too long. Please enter a shorter one.");
            ready = false;
        }
    }
}
