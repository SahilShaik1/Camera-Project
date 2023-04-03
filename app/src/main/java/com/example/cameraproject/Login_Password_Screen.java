package com.example.cameraproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Password_Screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.username_password_screen);
        EditText username = (EditText) findViewById(R.id.Username);
        EditText password = (EditText) findViewById(R.id.Password);
        Button submitBtn = (Button) findViewById(R.id.submit_area);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString() == "" && password.getText().toString() == ""){
                    GlobalVars.username = "";
                    GlobalVars.password = "";
                    Toast.makeText(getApplicationContext(), "Nothing entered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Camera_Page.class);
                    startActivity(intent);
                    getApplicationContext().startActivity(intent);
                }
                if(username.getText().toString() != "" && password.getText().toString() != ""){
                    GlobalVars.username = username.getText().toString();
                    GlobalVars.password = password.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), Camera_Page.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Both values entered", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please Enter Both or No Values", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
