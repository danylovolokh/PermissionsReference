package com.volokh.danylo.permissionsreferencelist.adapter.permission_items.header_like_items;

import com.volokh.danylo.permissionsreferencelist.adapter.PermissionItemViewHolder;
import com.volokh.danylo.permissionsreferencelist.adapter.visitors.PermissionItemVisitor;
import com.volokh.danylo.permissionsreferencelist.adapter.permission_items.PermissionListItem;

/**
 * Created by danylo.volokh on 4/7/16.
 */
public abstract class PermissionListHeaderItem implements PermissionListItem {

    private final String mName;

    protected PermissionListHeaderItem(String name) {
        mName = name;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void accept(PermissionItemViewHolder holder, PermissionItemVisitor initializeItemVisitor) {
        initializeItemVisitor.visit(holder, this);
    }
}
