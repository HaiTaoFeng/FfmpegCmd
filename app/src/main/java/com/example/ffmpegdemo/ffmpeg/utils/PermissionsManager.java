package com.example.ffmpegdemo.ffmpeg.utils;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

/**
 * <p>
 * 权限管理类
 */
public class PermissionsManager {

    /**
     * 拍照需要的权限
     */
    public  static final String[] CAMERA_REQUEST = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    /**
     * 拍照需要的权限
     */
    public  static final String[] VIDEO_PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET};
    /**
     * 拍照权限的请求code
     */
    public static final int CAMERA_REQUEST_CODE = 110;
    /**
     * 拍照权限的请求code
     */
    public static final int VIDEO_REQUEST_CODE = 120;

    /**
     * 申请相机权限
     */
    public static void requestCameraPermission(Activity activity){
        ActivityCompat.requestPermissions(activity,CAMERA_REQUEST,CAMERA_REQUEST_CODE);
    }
    /**
     * 申请录像权限
     */
    public static void requestVideoPermission(Activity activity){
        ActivityCompat.requestPermissions(activity,VIDEO_PERMISSIONS,VIDEO_REQUEST_CODE);
    }

    /**
     * 检查相机权限
     * @param activity
     * @return
     */
    public static boolean checkCameraPermission(Activity activity){
        if (!hasPermissionGranted(activity,CAMERA_REQUEST)) {
            requestCameraPermission(activity);
            return false;
        }
        return true;
    }
    /**
     * 检查录像权限
     * @param activity
     * @return
     */
    public static boolean checkVideoPermission(Activity activity){
        if (!hasPermissionGranted(activity,VIDEO_PERMISSIONS)) {
            requestVideoPermission(activity);
            return false;
        }
        return true;
    }

    /**
     * 检查权限组中的每个权限是否授权
     * @param activity
     * @param permissions
     * @return
     */
    private static boolean hasPermissionGranted(Activity activity, String[] permissions){
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity,permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
