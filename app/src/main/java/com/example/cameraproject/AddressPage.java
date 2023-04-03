package com.example.cameraproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.MediaPlayer;

import java.util.ArrayList;

public class AddressPage extends AppCompatActivity {

    public ArrayList<String> data = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameras_viewer);
        for(int i =0; i < GlobalVars.devices.size(); i++){
            data.add(GlobalVars.devices.get(i));
        }
        ListView lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(new AddressHolder(this,R.layout.single_list_item, data));
    }

}
