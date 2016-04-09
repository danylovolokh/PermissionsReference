package com.volokh.danylo.permissionsreferencelist.adapter.permission_items;

import com.volokh.danylo.permissionsreferencelist.adapter.PermissionItemViewHolder;
import com.volokh.danylo.permissionsreferencelist.adapter.visitors.PermissionItemVisitor;

/**
 * Created by danylo.volokh on 4/7/16.
 *
 * Base interface for adapter models
 */
public interface PermissionListItem {

    String getName();

    int getLayoutId();

    void accept(PermissionItemViewHolder holder, PermissionItemVisitor initializeItemVisitor);
}
