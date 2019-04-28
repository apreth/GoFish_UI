package net.arch64.gofish.android.forums;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.gofish.R;

import java.util.ArrayList;

public class ForumAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    //private Button upvote;
    //private Button downvote;

    public ForumAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lv_item, null);
        }

        TextView title = (TextView)view.findViewById(R.id.title);
        TextView content = (TextView)view.findViewById(R.id.content);
        final Button upvote = (Button)view.findViewById(R.id.upvote);
        final Button downvote = (Button)view.findViewById(R.id.downvote);

        upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("UPVOTETEXT", upvote.getText().toString());
                if (upvote.getText().toString().equals("Upvote")) {
                    upvote.setText("Upvoted ✔");
                    downvote.setEnabled(false);
                } else if (upvote.getText().toString().equals("Upvoted ✔")) {
                    upvote.setText("Upvote");
                    downvote.setEnabled(true);
                }
            }
        });

        downvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downvote.getText().toString().equals("Downvote")) {
                    downvote.setText("Downvoted ✗");
                    upvote.setEnabled(false);
                } else if (downvote.getText().toString().equals("Downvoted ✗")) {
                    downvote.setText("Downvote");
                    upvote.setEnabled(true);
                }
            }
        });

        return view;
    }

}