package com.example.journalintime.modele;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.journalintime.R;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {


    public CommentAdapter(Context context, List<Comment> comments){
        super(context,0,comments);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_comment,parent, false);
        }

        CommentViewHolder viewHolder = (CommentViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new CommentViewHolder();
            viewHolder.row_date = (TextView) convertView.findViewById(R.id.row_date);
            viewHolder.row_comment = (TextView) convertView.findViewById(R.id.row_comment);
            convertView.setTag(viewHolder);
        }

        Comment comment = getItem(position);

        viewHolder.row_date.setText(comment.getDate());
        viewHolder.row_comment.setText(comment.getComment());

        return convertView;
    }

    private class CommentViewHolder{
        public TextView row_date;
        public TextView row_comment;
    }
}
