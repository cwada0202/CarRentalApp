package com.example.semesterprojectver2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class MediaPlayerActivity extends AppCompatActivity {

    //TOOL BAR
    Toolbar tb;

    //declare text view and button
    TextView tvTitleMP;
    VideoView videoView;
    Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        //TOOLBAR SETTING
        tb=findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        // get textview and button from xml file
        tvTitleMP=findViewById(R.id.tv_title_MediaPlayer);
        videoView=findViewById(R.id.vv_MediaPlayer);
        btnPlay=findViewById(R.id.btn_player);

    }
    // method for playing video when user clicked it
    public void VideoPlayer(View vv){
        // video path of file in raw file
        videoView.setVideoPath("android.resource://com.example.semesterprojectver2/"+ R.raw.eiffel_tower2017);
        //start video when user click
        videoView.start();
    }
}
