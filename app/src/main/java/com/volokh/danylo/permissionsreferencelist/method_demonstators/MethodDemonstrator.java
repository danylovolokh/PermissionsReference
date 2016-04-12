package com.volokh.danylo.permissionsreferencelist.method_demonstators;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.volokh.danylo.permissionsreferencelist.adapter.permission_items.MethodItem;

/**
 * Created by danylo.volokh on 4/8/16.
 */
public abstract class MethodDemonstrator extends MethodItem {

    private static final String TAG = MethodDemonstrator.class.getSimpleName();

    private String mPermission;

    public interface DemonstratorCallback {
        boolean shouldCrash();
        Activity activity();
        void showToast(String message);
        void requestPermissions(String permission);
    }

    private final DemonstratorCallback mDemonstratorCallback;

    public MethodDemonstrator(String methodName, DemonstratorCallback demonstratorCallback) {
        super(methodName);
        mDemonstratorCallback = demonstratorCallback;
    }

    public abstract boolean callDangerousMethod() throws SecurityException;

    public void setPermissionName(String permissionName) {
        mPermission = permissionName;
    }

    protected Context context() {
        return callback().activity();
    }

    @Override
    public void demonstrate() {
        Log.v(TAG, ">> demonstrate, mPermission[" + mPermission + "]");

        int permissionCheck = ContextCompat.checkSelfPermission(callback().activity(),
                mPermission);

        Log.v(TAG, "demonstrate, shouldCrash " + callback().shouldCrash());

        if (callback().shouldCrash()) {

            demonstrateCrash(permissionCheck);

        } else {

            demonstrateAskPermission(permissionCheck);
        }

        Log.v(TAG, "<< demonstrate");
    }

    private void demonstrateAskPermission(int permissionCheck) {

        switch (permissionCheck) {
            case PackageManager.PERMISSION_GRANTED:
                Log.v(TAG, "demonstrate, PackageManager.PERMISSION_GRANTED");
                boolean successful = callDangerousMethod();

                if(successful){
                    callback().showToast("Method " + getClass().getSimpleName() + " called successfully");
                }

                break;
            case PackageManager.PERMISSION_DENIED:
                Log.v(TAG, "demonstrate, PackageManager.PERMISSION_DENIED");
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(callback().activity(),
                        Manifest.permission.READ_CONTACTS)) {

                    callback().showToast("Could you please give us the permission ? :)");

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    Log.v(TAG, "demonstrate, requestPermissions, mPermission[" + mPermission + "]");

                    callback().requestPermissions(mPermission);

                } else {
                    Log.v(TAG, "demonstrate, No explanation needed, we can request the permission, mPermission[" + mPermission + "]");


                    // No explanation needed, we can request the permission.

                    callback().requestPermissions(mPermission);
                }

                break;
        }
    }

    protected DemonstratorCallback callback() {
        return mDemonstratorCallback;
    }

    private void demonstrateCrash(int permissionCheck) {

        switch (permissionCheck) {
            case PackageManager.PERMISSION_GRANTED:
                Log.v(TAG, "demonstrateCrash, PackageManager.PERMISSION_GRANTED");

                callback().showToast(
                        "Sorry, I cannot crash. " +
                                "Permission is already granted. Please revoke permissions in Settings");
                break;
            case PackageManager.PERMISSION_DENIED:
                Log.v(TAG, "demonstrateCrash, PackageManager.PERMISSION_DENIED");

                try {
                    // this should cause crash
                    callDangerousMethod();

                } catch (SecurityException e) {
                    Log.e(TAG, "Application Crashed", e);
                    callback().showToast(
                            "Application Crashed. " +
                                    "See LogCat for details");
                }
                break;
        }
    }
}
