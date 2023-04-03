package com.example.cameraproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.stealthcopter.networktools.SubnetDevices;
import com.stealthcopter.networktools.subnet.Device;


import java.net.InetAddress;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    TextView tvWifiState;
    TextView tvScanning, tvResult;

    boolean visibleView = false;

    Button CameraPageBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set Buttons
        CameraPageBtn = (Button) findViewById(R.id.CameraButton);
        tvWifiState = (TextView)findViewById(R.id.WifiState);
        tvScanning = (TextView)findViewById(R.id.Scanning);
        tvResult = (TextView)findViewById(R.id.Result);
        findSubnetDevices();
        CameraPageBtn.setText("Go to Cameras");
        CameraPageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(visibleView == true) {
                    Intent intent = new Intent(MainActivity.this, AddressPage.class);
                    startActivity(intent);
                }
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
                        //For each device in the devices found, we add it to tvResult, which is just a
                        //text view displaying the string and adding ip and device name to global variables
                        for(Device device:devicesFound) {
                            GlobalVars.devices.add(device.hostname.toString() + "\n" + device.ip);
                            tvResult.append("Device " + device.hostname + "\n");
                            tvResult.append("IP : " + device.ip + "\n");
                            GlobalVars.ips.add(device.ip);
                            //Just for better user experience and for users not to see "null"
                            if(device.mac != null) {
                                tvResult.append("Mac : " + device.mac + "\n");
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
                //Sets the button to start working once the process is completed
                visibleView = true;
            }

        });

        // Below is example of how to cancel a running scan
        // subnetDevices.cancel();

    }
}