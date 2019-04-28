package net.arch64.gofish.android.app;

import android.content.Intent;
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

import net.arch64.gofish.android.client.Client;
import net.arch64.gofish.android.users.User;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        final Button submittBtn = (Button)findViewById(R.id.submitBtn);
        submittBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ready = true;

                EditText userNameText = (EditText) findViewById(R.id.userNameText);
                EditText fnameText = (EditText) findViewById(R.id.fnameText);//set up all text fields
                EditText lnameText = (EditText) findViewById(R.id.lnameText);
                EditText emailText = (EditText) findViewById(R.id.emailText);
                EditText passText = (EditText) findViewById(R.id.passText);
                EditText reenterPassText = (EditText) findViewById(R.id.reenterPassText);
                CheckBox notifyCheckBox = findViewById(R.id.notifyCheckBox);

                // check to make sure fields are not empty
                emptyText(userNameText);
                emptyText(fnameText);
                emptyText(lnameText);
                emptyText(emailText);
                emptyText(passText);
                emptyText(reenterPassText);

                // check length of necessary fields
                checkLength(128,fnameText);
                checkLength(128,lnameText);
                checkLength(128,emailText);
                checkLength(128,passText);
                checkLength(16,userNameText);

                // convert text to strings
                String user = userNameText.getText().toString();
                String fname = fnameText.getText().toString();
                String lname = lnameText.getText().toString();
                String email = emailText.getText().toString();
                String pass = passText.getText().toString();
                String reenterPass = reenterPassText.getText().toString();
                boolean notify = notifyCheckBox.isChecked();

                //check passwords and parameters against database requirements
                if (ready) {
                    Client client = new Client("10.0.2.2", 12345);
                    boolean registered = client.register(user, pass, fname, lname, email, notify);
                    client.close();

                    if (registered == true) {
                        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(loginIntent);
                    } else {
                        Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainActivityIntent);
                    }
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
