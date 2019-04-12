package net.arch64.gofish.android.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.util.regex.*;

import com.example.gofish.R;

public class RegisterActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private boolean ready = true;//flag for if all info is ready

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +//regex pattern for passwords
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=!,?;:,/])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{4,}" +               //at least 4 characters
            "$");

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

                CheckBox notifyCheckBox = findViewById(R.id.notifyCheckBox);
                boolean notify = notifyCheckBox.isChecked();//is the email notificaiton box selected

                emptyText(fnameText);//check to make sure fields are not empty
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
                emailCheck(emailText, email);

                if(pass.equals(reenterPass) != true){//checks if passwords match
                    reenterPassText.setError("Passwords must match.");
                    ready = false;
                }

                if(ready == false){//fields are not properly filled in
                    submittBtn.setError("Please enter correct information above.");
                }
                else{
                    submittBtn.setError(null);
                    //TODO: check if email exists, username exists
                    //TODO: connect to databse, check if username is used, create new profile, launch to next page

                }

            }
        });
    }

    public void emptyText(EditText field){
        if(field.length()==0) {
            field.setError("Field cannot be left empty.");
            ready = false;
        }
    }
    public void emailCheck(EditText field, String email){
        if(email == null || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            ready = false;
            field.setError("Please enter a valid email address.");
        }
        else{
            field.setError(null);
        }
    }
    public void passCheck(String password, EditText passText){//validates password strength
         if (!PASSWORD_PATTERN.matcher(password).matches()) {
            passText.setError("Password too weak");
            ready = false;
        } else {
            passText.setError(null);
        }
    }
    public void checkLength(int length, EditText field){
        if(field.length() > length){
            field.setError("Value entered is too long. Please enter a shorter one.");
            ready = false;
        }
    }
}
