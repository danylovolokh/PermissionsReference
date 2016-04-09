package com.volokh.danylo.permissionsreferencelist.adapter.visitors;

import com.volokh.danylo.permissionsreferencelist.adapter.PermissionItemViewHolder;
import com.volokh.danylo.permissionsreferencelist.adapter.permission_items.MethodItem;
import com.volokh.danylo.permissionsreferencelist.adapter.permission_items.header_like_items.PermissionListHeaderItem;

/**
 * Created by danylo.volokh on 4/7/16.
 *
 * This visitor is designed to help distinguishing different types of items
 * in {@link com.volokh.danylo.permissionsreferencelist.adapter.PermissionsReferenceAdapter}
 */
public interface PermissionItemVisitor {

    void visit(PermissionItemViewHolder holder, PermissionListHeaderItem headerItem);

    void visit(PermissionItemViewHolder holder, MethodItem methodItem);
}
