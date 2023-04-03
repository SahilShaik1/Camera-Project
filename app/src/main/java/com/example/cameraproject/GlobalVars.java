package com.example.cameraproject;

import android.app.Application;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class GlobalVars extends Application {
    public static ArrayList<String> devices = new ArrayList<String>();
    public static ArrayList<String> ips = new ArrayList<String>();

    public static String ip_chosen;

    public static String username;

    public static String password;

    public static String url;
}
