package net.arch64.gofish.android.forums;

import android.content.Context;
import android.support.v4.graphics.drawable.IconCompat;
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
    private ArrayList<Forum> list = new ArrayList<Forum>();
    private Context context;
    private View view;

    public ForumAdapter(ArrayList<Forum> list, Context context) {
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
        view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lv_item, null);
        }

        final TextView title = (TextView)view.findViewById(R.id.title);
        final TextView content = (TextView)view.findViewById(R.id.content);
        final TextView ratio = (TextView)view.findViewById(R.id.ratio);
        final Button upvote = (Button)view.findViewById(R.id.upvote);
        final Button downvote = (Button)view.findViewById(R.id.downvote);

        final Forum post = list.get(position);

        title.setText(post.getUsername() + " @ " + post.getTimeStamp());
        content.setText(post.getContent());
        ratio.setText((int)post.getLikes() + " : " + (int)post.getDislikes());

        upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("UPVOTETEXT", upvote.getText().toString());
                if (upvote.getText().toString().equals("Upvote")) {
                    upvote.setText("Upvoted ✔");
                    upvote.setBackgroundColor(view.getResources().getColor(R.color.colorPrimary));
                    downvote.setEnabled(false);
                    ratio.setText(((int)post.getLikes() + 1) + " : " + ((int)post.getDislikes()));
                } else if (upvote.getText().toString().equals("Upvoted ✔")) {
                    upvote.setText("Upvote");
                    upvote.setBackgroundColor(view.getResources().getColor(R.color.design_default_color_primary));
                    downvote.setEnabled(true);
                    ratio.setText(((int)post.getLikes()) + " : " + ((int)post.getDislikes()));
                }
            }
        });

        downvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downvote.getText().toString().equals("Downvote")) {
                    downvote.setText("Downvoted ✗");
                    downvote.setBackgroundColor(view.getResources().getColor(R.color.colorAccent));
                    upvote.setEnabled(false);
                    ratio.setText(((int)post.getLikes()) + " : " + ((int)post.getDislikes() + 1));
                } else if (downvote.getText().toString().equals("Downvoted ✗")) {
                    downvote.setText("Downvote");
                    downvote.setBackgroundColor(view.getResources().getColor(R.color.design_default_color_primary));
                    upvote.setEnabled(true);
                    ratio.setText(((int)post.getLikes()) + " : " + ((int)post.getDislikes()));
                }
            }
        });

        return view;
    }

}