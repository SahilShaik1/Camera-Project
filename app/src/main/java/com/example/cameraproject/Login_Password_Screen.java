package com.example.cameraproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Login_Password_Screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.username_password_screen);
        EditText username = (EditText) findViewById(R.id.Username);
        EditText password = (EditText) findViewById(R.id.Password);
        Button submitBtn = (Button) findViewById(R.id.submit_area);
        EditText ending = (EditText) findViewById(R.id.rtsp_ending_input);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String endingText = ending.getText().toString();
                String userText = username.getText().toString();
                String passText = password.getText().toString();


                if(userText == "" && passText == ""){
                    GlobalVars.username = "";
                    GlobalVars.password = "";

                    Toast.makeText(getApplicationContext(), userText, Toast.LENGTH_SHORT).show();

                    if(endingText != "-1") {
                        GlobalVars.url = "rtsp://" + GlobalVars.ip_chosen + ":8080" + endingText;
                        Toast.makeText(getApplicationContext(), "Ending Specified: " + endingText, Toast.LENGTH_SHORT).show();
                    } else{
                        GlobalVars.url = "rtsp://" + GlobalVars.ip_chosen + ":8080/h264_pcm.sdp";
                    }
                    Intent intent = new Intent(getApplicationContext(), Camera_Page.class);
                    startActivity(intent);
                    getApplicationContext().startActivity(intent);
                }
                if(userText != "" && passText != ""){

                    if(endingText != "-1") {
                        GlobalVars.url = "rtsp://" + GlobalVars.username + ":" + GlobalVars.password + "@" + GlobalVars.ip_chosen + ":8080" + endingText;
                        Toast.makeText(getApplicationContext(), "Ending Specified: " + endingText, Toast.LENGTH_SHORT).show();
                    } else{
                        GlobalVars.url = "rtsp://" + GlobalVars.username + ":" + GlobalVars.password + "@" + GlobalVars.ip_chosen + ":8080/h264_pcm.sdp";
                    }
                    GlobalVars.username = userText;
                    GlobalVars.password = passText;
                    Toast.makeText(getApplicationContext(), userText, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Camera_Page.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please Enter Both or No Values", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
