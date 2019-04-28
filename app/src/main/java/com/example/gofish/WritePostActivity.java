package com.example.gofish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WritePostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

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
