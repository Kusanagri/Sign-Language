package com.example.videoplayer;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.nio.file.Paths;

public class MainActivity extends AppCompatActivity {
    VideoView video;
    MediaController media;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        video = (VideoView)findViewById(R.id.videoView);
    }

    public void playVideo(View v){
        media = new MediaController(this);
        String str = "countdown"; //Suppose input word is "countdown"
        int videoResource = getResources().getIdentifier(str, "raw", getPackageName()); //calculating id for that video name
        String path = "android.resource://" + getPackageName() + "/" + videoResource; //using the id found above for the required video
        Uri u = Uri.parse(path);
        video.setMediaController(media);
        video.setVideoURI(u);
        video.start();


    }

}
