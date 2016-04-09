package com.volokh.danylo.permissionsreferencelist.permissions.base;

import com.volokh.danylo.permissionsreferencelist.method_demonstators.MethodDemonstrator;

/**
 * Created by danylo.volokh on 4/6/16.
 *
 * This model describes a single permission and methods
 */
public class Permission {

    /**
     * This is permission name. Example {@link android.Manifest.permission#ACCESS_CHECKIN_PROPERTIES}
     */
    private final String mPermission;
    /**
     * This is a list of methods, calling which user has to
     * grant "this" permission
     */
    private final MethodDemonstrator[] mMethodsDemonstrators;

    public Permission(String permission, MethodDemonstrator... methodDemonstrators) {
        mPermission = permission;
        mMethodsDemonstrators = methodDemonstrators;
    }

    public String getPermissionName(){
        return mPermission;
    }

    public MethodDemonstrator[] getMethodDemonstrators() {
        return mMethodsDemonstrators;
    }
}
