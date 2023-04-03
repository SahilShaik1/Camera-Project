package com.example.cameraproject;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.util.VLCVideoLayout;

public class Camera_Page extends AppCompatActivity
{
//    private String url = "rtsp://a:b@10.0.0.155:8080/h264_pcm.sdp";
    private String url;

    private LibVLC libVlc;
    private MediaPlayer mediaPlayer;
    private VLCVideoLayout videoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_page);

        libVlc = new LibVLC(this);
        mediaPlayer = new MediaPlayer(libVlc);
        videoLayout = findViewById(R.id.videoLayout);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if(GlobalVars.username == "" && GlobalVars.password == ""){
           url = "rtsp://" + GlobalVars.ip_chosen + ":8080/h264_pcm.sdp";
        } else{
            url = "rtsp://" + GlobalVars.username + ":" + GlobalVars.password + "@" + GlobalVars.ip_chosen + ":8080/h264_pcm.sdp";
        }
        Toast.makeText(getApplicationContext(),url,Toast.LENGTH_SHORT).show();
        mediaPlayer.attachViews(videoLayout, null, false, false);
        Media media = new Media(libVlc, Uri.parse(url));
        media.setHWDecoderEnabled(true, false);
        media.addOption(":network-caching=600");


        mediaPlayer.setMedia(media);
        media.release();
        mediaPlayer.play();
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        mediaPlayer.stop();
        mediaPlayer.detachViews();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        mediaPlayer.release();
        libVlc.release();
    }
}