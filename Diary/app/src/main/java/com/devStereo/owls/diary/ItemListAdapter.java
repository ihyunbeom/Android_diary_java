package com.devStereo.owls.diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.devStereo.owls.R;

import java.util.ArrayList;

/**
 * Created by Cloud on 2017-03-12.
 */

public class ItemListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Item> itemsList;

    public ItemListAdapter(Context context, int layout, ArrayList<Item> itemsList) {
        this.context = context;
        this.layout = layout;
        this.itemsList = itemsList;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private  class  ViewHolder{
        TextView textTopic,textContents;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder = new ViewHolder();

        if(row==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            viewHolder.textTopic=(TextView)row.findViewById(R.id.topic);
            viewHolder.textContents=(TextView)row.findViewById(R.id.contents);
            row.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) row.getTag();
        }

        Item item = itemsList.get(position);
        viewHolder.textTopic.setText(item.getTopic());
        viewHolder.textContents.setText(item.getContents());

        return row;
    }
}
