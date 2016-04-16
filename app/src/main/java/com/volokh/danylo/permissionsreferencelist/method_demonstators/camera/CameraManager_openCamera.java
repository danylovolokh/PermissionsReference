package com.volokh.danylo.permissionsreferencelist.method_demonstators.camera;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.util.Log;

import com.volokh.danylo.permissionsreferencelist.method_demonstators.MethodDemonstrator;

/**
 * Created by danylo.volokh on 4/16/16.
 */
public class CameraManager_openCamera extends MethodDemonstrator {

    private static final String TAG = CameraManager_openCamera.class.getSimpleName();

    public CameraManager_openCamera(String methodName, DemonstratorCallback demonstratorCallback) {
        super(methodName, demonstratorCallback);
    }

    @Override
    public boolean callDangerousMethod() throws SecurityException {
        Log.v(TAG, ">> callDangerousMethod");

        CameraManager cameraManager = (CameraManager) callback().activity().getSystemService(Context.CAMERA_SERVICE);
        try {

            String[] cameraIds = cameraManager.getCameraIdList();
            String camera = null;
            for (String cameraId : cameraIds) {
                if (cameraId != null) {
                    camera = cameraId;
                    break;
                }
            }

            if(camera == null){
                callback().showToast("There is no camera on this device, or Camera is not available");
                return true;
            }

            cameraManager.openCamera(camera, new CameraDevice.StateCallback() {
                @Override
                public void onOpened(CameraDevice camera) {
                    Log.v(TAG, "onOpened, callDangerousMethod. camera " + camera);

                    camera.close();
                }

                @Override
                public void onDisconnected(CameraDevice camera) {

                }

                @Override
                public void onError(CameraDevice camera, int error) {

                }
            }, null);

        } catch (CameraAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Log.v(TAG, "<< callDangerousMethod");

        return true;
    }
}
