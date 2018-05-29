package cit.mini.tp.techpedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by SARADHA on 04-03-2018.
 */

public class CommnetAdapter extends BaseAdapter {
    private CommentActivity activity;
    public ArrayList<Comment> commentlist;


    public CommnetAdapter(CommentActivity activity, ArrayList<Comment> commentlist) {
        this.activity = activity;
        this.commentlist = commentlist;


    }
    @Override
    public int getCount() {
        return commentlist.size() ;
    }

    @Override
    public Object getItem(int i) {
        return commentlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //return null;

        final CommnetAdapter.ViewHolder holder;
        final Comment user = (Comment) getItem(i);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.layout_commentlist, viewGroup, false);
            holder = new ViewHolder();
/*
            holder.iconText = (TextView) view.findViewById(R.id.ettyping);
*/
            holder.name = (TextView) view.findViewById(R.id.comment);
            //holder.iconText.setTypeface(typeface, Typeface.BOLD);
            //holder.iconText.setTextColor(activity.getResources().getColor(R.color.white));
            // holder.name.setTypeface(typeface, Typeface.NORMAL);

            view.setTag(holder);
        } else {
            // get view holder back
            holder = (CommnetAdapter.ViewHolder) view.getTag();
        }

        // bind text with view holder content view for efficient use
       /* holder.iconText.setText("#");*/
        holder.name.setText(user.getComments_typied());
       // view.setBackgroundResource(R.drawable.ic_search_white);

        return view;
    }

    public class ViewHolder {
       /* TextView iconText;*/
        TextView name;
    }

}
