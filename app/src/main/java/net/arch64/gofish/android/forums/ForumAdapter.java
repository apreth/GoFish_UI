package net.arch64.gofish.android.forums;

import android.content.Context;
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
                if (upvote.getText().equals("upvote")) {
                    upvote.setBackgroundColor(50);
                    upvote.setText("upvoted ✔");
                    downvote.setEnabled(false);
                } else if (upvote.getText().equals("upvoted ✔")) {
                    upvote.setBackgroundColor(100);
                    upvote.setText("upvote");
                    downvote.setEnabled(true);
                }
            }
        });

        downvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downvote.getText().equals("downvote")) {
                    downvote.setBackgroundColor(50);
                    downvote.setText("downvoted ✗");
                    upvote.setEnabled(false);
                } else if (downvote.getText().equals("downvoted ✗")) {
                    downvote.setBackgroundColor(100);
                    downvote.setText("downvote");
                    upvote.setEnabled(true);
                }
            }
        });

        return view;
    }

}
