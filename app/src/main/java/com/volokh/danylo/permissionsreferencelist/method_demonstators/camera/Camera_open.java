package com.volokh.danylo.permissionsreferencelist.method_demonstators.camera;

import android.graphics.Color;
import android.hardware.Camera;
import android.util.Log;

import com.volokh.danylo.permissionsreferencelist.R;
import com.volokh.danylo.permissionsreferencelist.adapter.PermissionItemViewHolder;
import com.volokh.danylo.permissionsreferencelist.adapter.visitors.PermissionItemVisitor;
import com.volokh.danylo.permissionsreferencelist.method_demonstators.MethodDemonstrator;

/**
 * Created by danylo.volokh on 4/16/16.
 *
 * This class is using old Camera API.
 *
 * Whe we don't have camera permission we get
 *
    java.lang.RuntimeException: Fail to connect to camera service
        at android.hardware.Camera.<init>(Camera.java:532)
        at android.hardware.Camera.open(Camera.java:360)

 */
public class Camera_open extends MethodDemonstrator{

    private static final String TAG = Camera_open.class.getSimpleName();

    public Camera_open(String methodName, DemonstratorCallback demonstratorCallback) {
        super(methodName, demonstratorCallback);
    }

    @Override
    public boolean callDangerousMethod() throws SecurityException {
        Log.v(TAG, ">> callDangerousMethod");

        int cameraId = -1;

        // try to get frontal camera first
        int frontalCameraId = getCameraID(Camera.CameraInfo.CAMERA_FACING_FRONT);

        if(frontalCameraId >=0 ){
            cameraId = frontalCameraId;
        } else {
            // try to get back camera
            int backCameraId = getCameraID(Camera.CameraInfo.CAMERA_FACING_BACK);
            if(backCameraId >=0){
                cameraId = backCameraId;
            }
        }

        if (cameraId < 0){
            callback().showToast("There is no camera on this device, or Camera is not available");
        }
        Camera camera;
        try {
            camera = Camera.open(cameraId);
        } catch (RuntimeException e){
            // camera doesn't throw Security Exception, so we create it here
            throw new SecurityException(e.getMessage());
        }

        if(camera != null){
            Log.i(TAG, "callDangerousMethod, camera object received " + camera);
            camera.release();
        }
        Log.v(TAG, "<< callDangerousMethod");

        return true;
    }

    /*
     * facing - Camera.CameraInfo.CAMERA_FACING_FRONT / BACK
     */
    private int getCameraID(int facing) {
        int cameraCount = 0;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();

        if (cameraCount < 2) {
            return 0;
        }

        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == facing) {
                return camIdx;
            }
        }
        return -1; // not found
    }
}
