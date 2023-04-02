package com.example.cameraproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraActivity;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Camera_Page extends CameraActivity {
    private static String LOGTAG = "Debug";
    
    private CameraBridgeViewBase Camera;

    private BaseLoaderCallback mloaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status){
                case LoaderCallbackInterface.SUCCESS:{
                    Log.v(LOGTAG, "OpenCv loaded");
                    Camera.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_page);
        Camera = (CameraBridgeViewBase) findViewById(R.id.cameraview);
        Camera.setVisibility(SurfaceView.VISIBLE);
        Camera.setCvCameraViewListener(cvCameraViewListener);
    }

    @Override
    protected List<?extends CameraBridgeViewBase> getCameraViewList() {
        return Collections.singletonList(Camera);
    }

    private CameraBridgeViewBase.CvCameraViewListener2 cvCameraViewListener = new CameraBridgeViewBase.CvCameraViewListener2() {
        @Override
        public void onCameraViewStarted(int width, int height) {

        }

        @Override
        public void onCameraViewStopped() {

        }

        @Override
        public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
            return inputFrame.rgba();
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        if(Camera != null){
            Camera.disableView();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(LOGTAG,"OpenCV not found, Initializing");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, mloaderCallback);
        } else{
            mloaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Camera != null) {
            Camera.disableView();
        }
    }



}