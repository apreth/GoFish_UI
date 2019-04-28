package net.arch64.gofish.android.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gofish.R;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        tv = (TextView) findViewById(R.id.tv);

        for (int i = 0; i < 5; i++) {
            String text = tv.getText().toString();
            tv.setText(text + ForumActivity.modelArrayList.get(i).getFruit()+" -> "+ForumActivity.modelArrayList.get(i).getNumber()+"\n");
        }
    }
}