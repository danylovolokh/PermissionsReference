package com.volokh.danylo.permissionsreferencelist.permissions.dangerous;

import com.volokh.danylo.permissionsreferencelist.permissions.base.Permission;
import com.volokh.danylo.permissionsreferencelist.permissions.base.PermissionGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by danylo.volokh on 4/6/16.
 *
 * This model describes dangerous permission group.
 *
 * It contains list of all permissions in this group {@link #mPermissions}
 * For example:
 *
 *  Permission group: android.permission-group.CALENDAR
 *
 *  Permissions in this group:
 *                              android.permission.READ_CALENDAR
 *                              android.permission.WRITE_CALENDAR
 */
public class DangerousPermissionGroup extends PermissionGroup {

    private final String mPermissionGroupName;
    private final List<Permission> mPermissions;

    public DangerousPermissionGroup(String permissionGroupName, Permission... permissions) {
        mPermissionGroupName = permissionGroupName;
        mPermissions = new ArrayList<>(Arrays.asList(permissions));
    }

    @Override
    public List<Permission> getPermissionsOfThisGroup() {
        return mPermissions;
    }

    @Override
    public String getPermissionGroupName() {
        return mPermissionGroupName;
    }
}
