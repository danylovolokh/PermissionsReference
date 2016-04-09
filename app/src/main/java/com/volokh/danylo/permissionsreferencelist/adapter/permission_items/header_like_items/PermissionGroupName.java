package com.volokh.danylo.permissionsreferencelist.adapter.permission_items.header_like_items;

import com.volokh.danylo.permissionsreferencelist.R;

/**
 * Created by danylo.volokh on 4/7/16.
 *
 * This model describes Permissions group name. See available groups:
 * {@link android.Manifest.permission_group}
 */
public class PermissionGroupName extends PermissionListHeaderItem {

    public PermissionGroupName(String groupName) {
        super(groupName);
    }

    @Override
    public int getLayoutId() {
        return R.layout.permission_group_name;
    }
}
