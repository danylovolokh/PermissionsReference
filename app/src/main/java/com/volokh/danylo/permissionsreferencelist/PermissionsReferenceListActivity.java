package com.volokh.danylo.permissionsreferencelist;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.volokh.danylo.permissionsreferencelist.adapter.permission_items.PermissionListItem;
import com.volokh.danylo.permissionsreferencelist.adapter.PermissionsReferenceAdapter;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.MethodDemonstrator;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.ScrapHeap;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar.Calendar_ContentProvider_applyBatch;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar.Calendar_ContentProvider_bulkInsert;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar.Calendar_ContentProvider_canonicalize;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar.Calendar_ContentProvider_delete;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar.Calendar_ContentProvider_insert;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar.Calendar_ContentProvider_query;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar.Calendar_ContentProvider_uncanonicalize;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.calendar.Calendar_ContentProvider_update;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.camera.CameraManager_openCamera;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.camera.Camera_open;
import com.volokh.danylo.permissionsreferencelist.permissions.base.Permission;
import com.volokh.danylo.permissionsreferencelist.permissions.base.PermissionGroup;
import com.volokh.danylo.permissionsreferencelist.permissions.dangerous.DangerousPermissionGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PermissionsReferenceListActivity extends AppCompatActivity implements MethodDemonstrator.DemonstratorCallback {

    private static final int REQUEST_CODE = 1;
    private static final String TAG = PermissionsReferenceListActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private PermissionsReferenceAdapter mAdapter;

    PermissionGroup CALENDAR = new DangerousPermissionGroup(Manifest.permission_group.CALENDAR,
            new Permission(Manifest.permission.READ_CALENDAR,
                    new Calendar_ContentProvider_query(
                            "ContentResolver#query(Uri, String[], String, String[], String)",
                            this),
                    new Calendar_ContentProvider_canonicalize(
                            "ContentResolver#canonicalize(Uri)",
                            this),
                    new Calendar_ContentProvider_uncanonicalize(
                            "ContentResolver#uncanonicalize(Uri)",
                            this)
                    ),


            new Permission(Manifest.permission.WRITE_CALENDAR,


                    new Calendar_ContentProvider_insert(
                            "ContentResolver#insert(Uri url, ContentValues",
                            this),
                    new Calendar_ContentProvider_bulkInsert(
                            "ContentResolver#bulkInsert(Uri url, ContentValues[]",
                            this),
                    new Calendar_ContentProvider_update(
                            "ContentResolver#update(Uri, ContentValues, String, String[])",
                            this),
                    new Calendar_ContentProvider_applyBatch(
                            "ContentResolver#applyBatch(String, ArrayList<ContentProviderOperation>)",
                            this),
                    new Calendar_ContentProvider_delete(
                            "ContentResolver#delete(Uri)",
                            this)
            )
    );

    PermissionGroup CAMERA = new DangerousPermissionGroup(Manifest.permission_group.CAMERA,
            new Permission(Manifest.permission.CAMERA,
                    new Camera_open("Camera#open(int)", this),
                    new CameraManager_openCamera("CameraManager#openCamera(int)", this)
            )
    );

    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions_reference_list);

        if(savedInstanceState != null){
            ScrapHeap.onRestoreInstanceState(savedInstanceState);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.permissions_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //TODO: refactor this. Create factory for these elements
        List<PermissionListItem> permissionList = new ArrayList<>();
        List<PermissionListItem> calendarPermissions = CALENDAR.getPermissionListItems();
        permissionList.addAll(calendarPermissions);

        List<PermissionListItem> cameraPermissions = CAMERA.getPermissionListItems();
        permissionList.addAll(cameraPermissions);

        mAdapter = new PermissionsReferenceAdapter(permissionList);
        mRecyclerView.setAdapter(mAdapter);

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ScrapHeap.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.v(TAG, "onRequestPermissionsResult, permissions " + Arrays.toString(permissions));
        String singleRequesteedPermission = permissions[0];

        switch (requestCode) {
            case REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(this, "You gave the permission. Hooray! Please do the action again", Toast.LENGTH_SHORT).show();

                } else {

                    if (isDeniedForever(singleRequesteedPermission)) {
                        Log.v(TAG, "Permission was denied forever please grant in in Setting");
                        Toast.makeText(this, "Permission was denied forever please grant in in Setting", Toast.LENGTH_SHORT).show();
                    } else {

                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                        Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private boolean isDeniedForever(String permission) {
        return !hasPermission(permission) && !shouldShowRationale(permission);
    }


    private boolean hasPermission(String permission) {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean shouldShowRationale(String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
    }


    @Override
    public boolean shouldCrash() {
        switch (mRadioGroup.getCheckedRadioButtonId()) {
            case R.id.crash_check_button:
                return true;

            case R.id.ask_permission_check_button:
                return false;
            default:
                throw new RuntimeException("unhandled check button");
        }
    }

    @Override
    public Activity activity() {
        return this;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void requestPermissions(String permission) {
        Log.v(TAG, "requestPermissions, permission[" + permission + "]");
        ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_CODE);
    }
}
