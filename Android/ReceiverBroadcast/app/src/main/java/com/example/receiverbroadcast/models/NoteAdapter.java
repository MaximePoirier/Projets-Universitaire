package com.example.receiverbroadcast.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.receiverbroadcast.R;

import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {


    public NoteAdapter(Context context, List<Note> notes){
        super(context,0,notes);
    }


    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_note,parent,false);
        }

        NoteViewHolder viewHolder = (NoteViewHolder) convertView.getTag();

        if(viewHolder == null){
            viewHolder = new NoteViewHolder();
            viewHolder.row_lat = (TextView) convertView.findViewById(R.id.row_lat);
            viewHolder.row_lon = (TextView) convertView.findViewById(R.id.row_lon);
            viewHolder.row_note = (TextView) convertView.findViewById(R.id.row_note);
            convertView.setTag(viewHolder);
        }

        Note note = getItem(position);

        viewHolder.row_lat.setText(note.getLat().toString());
        viewHolder.row_lon.setText(note.getLon().toString());
        viewHolder.row_note.setText(note.getNote());

        return convertView;
    }

    private class NoteViewHolder{
        public TextView row_lat;
        public TextView row_lon;
        public TextView row_note;
    }
}
