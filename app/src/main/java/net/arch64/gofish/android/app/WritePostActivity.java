
package net.arch64.gofish.android.app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gofish.R;

public class WritePostActivity extends AppCompatActivity {
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().getItem(1).setChecked(true);
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

        EditText postText = (EditText) findViewById(R.id.postEditText);
        String post = postText.getText().toString();

        Button postSubmitt = (Button) findViewById(R.id.postSubmittBtn);
        postSubmitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //submitt post
            }
        });
    }
}