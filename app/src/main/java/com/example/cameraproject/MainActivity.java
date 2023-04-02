package com.example.cameraproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.stealthcopter.networktools.SubnetDevices;
import com.stealthcopter.networktools.subnet.Device;

import org.opencv.android.OpenCVLoader;

import java.net.InetAddress;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    TextView tvWifiState;
    TextView tvScanning, tvResult;

    Button CameraPageBtn;

    ArrayList<InetAddress> inetAddresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CameraPageBtn = (Button) findViewById(R.id.CameraButton);
        tvWifiState = (TextView)findViewById(R.id.WifiState);
        tvScanning = (TextView)findViewById(R.id.Scanning);
        tvResult = (TextView)findViewById(R.id.Result);


        findSubnetDevices();
        CameraPageBtn.setText("Go to Cameras");
        CameraPageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Camera_Page.class);
                startActivity(intent);
            }
        });
    }

    private void findSubnetDevices() {

        final long startTimeMillis = System.currentTimeMillis();
        tvScanning.setText("Scanning...");
        SubnetDevices subnetDevices = SubnetDevices.fromLocalAddress().findDevices(new SubnetDevices.OnSubnetDeviceFound() {

            @Override
            public void onDeviceFound(Device device) {

            }

            public void onFinished(ArrayList<Device> devicesFound) {
                float timeTaken =  (System.currentTimeMillis() - startTimeMillis)/1000.0f;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvScanning.setText("Scan Finished");
                        for(Device device:devicesFound) {

                            tvResult.append("Device " + device.hostname + "\n");
                            tvResult.append("IP : " + device.ip + "\n");
                            if(device.mac != null) {
                                tvResult.append("Mack : " + device.mac + "\n");
                            } else{
                                tvResult.append("Mac not Found\n");
                            }
                            tvResult.append("\n");
                            tvResult.append("\n");

                        }
                    }
                });

              /*  appendResultsText("Finished "+timeTaken+" s");
                setEnabled(subnetDevicesButton, true);*/
            }
        });

        // Below is example of how to cancel a running scan
        // subnetDevices.cancel();

    }
}