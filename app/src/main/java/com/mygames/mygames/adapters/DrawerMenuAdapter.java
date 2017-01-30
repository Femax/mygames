package com.mygames.mygames.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.mygames.mygames.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by fedosov on 7/19/16.
 */
public class DrawerMenuAdapter extends BaseAdapter {

    private List<DrawerItem> drawerItems;

    public DrawerMenuAdapter(List<DrawerItem> drawerItems) {
        this.drawerItems = drawerItems == null ? new ArrayList<DrawerItem>(0) : drawerItems;
    }

    @Override
    public int getCount() {
        return drawerItems.size();
    }

    @Override
    public DrawerItem getItem(int position) {
        return drawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getItemPosition(DrawerItem item) {
        return drawerItems.indexOf(item);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
            view.setTag(new ViewHolder(view));
        }
        holder = (ViewHolder) view.getTag();
        holder.applyData(drawerItems.get(position));
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.item_image)
        protected ImageView image;
        @BindView(R.id.item_name)
        protected TextView activityName;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }

        public void applyData(DrawerItem item) {
            image.setImageResource(item.getImageRes());

            activityName.setText(item.getActivityName());

        }
    }



}
