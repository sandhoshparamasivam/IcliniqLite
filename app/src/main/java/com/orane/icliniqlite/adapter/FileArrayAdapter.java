package com.orane.icliniqlite.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.orane.icliniqlite.Model.Constants;
import com.orane.icliniqlite.Model.FileInfo;
import com.orane.icliniqlite.R;

import java.util.List;


@SuppressLint("DefaultLocale")
public class FileArrayAdapter extends ArrayAdapter<FileInfo> {

    private Context context;
    private int resorceID;
    private List<FileInfo> items;

    public FileArrayAdapter(Context context, int textViewResourceId,
                            List<FileInfo> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.resorceID = textViewResourceId;
        this.items = objects;
    }

    public FileInfo getItem(int i) {
        return items.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(resorceID, null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) convertView
                    .findViewById(android.R.id.icon);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.details = (TextView) convertView
                    .findViewById(R.id.details);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        FileInfo option = items.get(position);
        if (option != null) {

            if (option.getData().equalsIgnoreCase(Constants.FOLDER)) {
                viewHolder.icon.setImageResource(R.mipmap.logo);
            }

            viewHolder.name.setText(option.getName());
            viewHolder.details.setText(option.getData());

        }
        return convertView;
    }

    class ViewHolder {
        ImageView icon;
        TextView name;
        TextView details;
    }

}
