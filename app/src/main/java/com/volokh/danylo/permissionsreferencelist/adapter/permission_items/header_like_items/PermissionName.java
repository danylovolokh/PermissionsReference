package com.volokh.danylo.permissionsreferencelist.adapter.permission_items.header_like_items;

import com.volokh.danylo.permissionsreferencelist.R;

/**
 * Created by danylo.volokh on 4/7/16.
 */
public class PermissionName extends PermissionListHeaderItem {

    public PermissionName(String permissionName) {
        super(permissionName);
    }

    @Override
    public int getLayoutId() {
        return R.layout.permission_name;
    }
}
