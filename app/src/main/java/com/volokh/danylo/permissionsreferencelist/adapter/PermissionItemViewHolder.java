package com.volokh.danylo.permissionsreferencelist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.volokh.danylo.permissionsreferencelist.R;

/**
 * Created by danylo.volokh on 4/6/16.
 */
public class PermissionItemViewHolder extends RecyclerView.ViewHolder {

    public final TextView name;

    public PermissionItemViewHolder(View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.name);
    }
}
