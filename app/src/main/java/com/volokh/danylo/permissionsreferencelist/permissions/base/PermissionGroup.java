package com.volokh.danylo.permissionsreferencelist.permissions.base;

import com.volokh.danylo.permissionsreferencelist.adapter.permission_items.header_like_items.PermissionGroupName;
import com.volokh.danylo.permissionsreferencelist.adapter.permission_items.PermissionListItem;
import com.volokh.danylo.permissionsreferencelist.adapter.permission_items.header_like_items.PermissionName;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.MethodDemonstrator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danylo.volokh on 4/6/16.
 *
 * This abstract describes {@link android.Manifest.permission_group}
 * Also it provides items for {@link com.volokh.danylo.permissionsreferencelist.adapter.PermissionsReferenceAdapter}
 *
 * To get all items from this permission group call {@link #getPermissionListItems()}
 *
 */
public abstract class PermissionGroup {

    protected abstract List<Permission> getPermissionsOfThisGroup();

    public abstract String getPermissionGroupName();

    /**
     * The order is following:
     *
     *  _______________________
     * |                       |
     * | Permission Group Name |
     * |_______________________|__
     *   |                        |
     *   | Permission Name        |
     *   |________________________|__
     *      |                        |
     *      |  Method that needs it  |
     *      |________________________|
     *      |                        |
     *      |  Method that needs it  |
     *    __|________________________|
     *   |                        |
     *   | Permission Name        |
     *   |________________________|__
     *      |                        |
     *      |  Method that needs it  |
     *      |________________________|
     *      |                        |
     *      |  Method that needs it  |
     *      |________________________|
     *
     */
    public final List<PermissionListItem> getPermissionListItems(){

        List<PermissionListItem> permissionListItems = new ArrayList<>();

        addGroupName(permissionListItems);
        addPermissions(permissionListItems);

        return permissionListItems;
    }

    private void addPermissions(List<PermissionListItem> permissionListItems) {
        List<Permission> permissionsOfThisGroup = getPermissionsOfThisGroup();

        for (Permission permission: permissionsOfThisGroup) {

            addPermission(permissionListItems, permission);
            addMethodDemonstrators(permissionListItems, permission);

        }
    }

    private void addMethodDemonstrators(List<PermissionListItem> permissionListItems, Permission permission) {
        for(MethodDemonstrator demonstrator : permission.getMethodDemonstrators()){
            demonstrator.setPermissionName(permission.getPermissionName());
            permissionListItems.add(demonstrator);
        }
    }

    private void addPermission(List<PermissionListItem> permissionListItems, Permission permission) {
        PermissionListItem permissionName = new PermissionName(permission.getPermissionName());
        permissionListItems.add(permissionName);
    }

    private void addGroupName(List<PermissionListItem> permissionListItems) {
        PermissionListItem groupName = new PermissionGroupName(getPermissionGroupName());
        permissionListItems.add(groupName);
    }
}
