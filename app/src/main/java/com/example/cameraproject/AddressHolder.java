package com.example.cameraproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class AddressHolder extends ArrayAdapter<String> {
    private int layout;
    private List<String> mObjects;
    AddressHolder(Context context, int resource, List<String> objects) {
        //Sets a constructor to make it simple to create, with 3 parameters
        super(context, resource, objects);
        mObjects = objects;
        layout = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mainViewholder = null;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView.findViewById(R.id.list_item_text);
            viewHolder.button = (Button) convertView.findViewById(R.id.list_item_btn);
            convertView.setTag(viewHolder);
        }
        mainViewholder = (ViewHolder) convertView.getTag();
        mainViewholder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVars.ip_chosen = GlobalVars.ips.get(position);
                Toast.makeText(getContext(),"Chosen: " + GlobalVars.ip_chosen,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), Login_Password_Screen.class);
                getContext().startActivity(intent);
            }
        });
        mainViewholder.text.setText(getItem(position));

        return convertView;
    }
}

