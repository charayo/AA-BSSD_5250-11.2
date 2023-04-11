package com.example.javaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;

public class MoodActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    String pathfile;
    public static final String EXTRA_CHOICE = "com.example.javaandroid.EXTRA_CHOICE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
        String choice = intent.getStringExtra(MoodActivity.EXTRA_CHOICE);

        if(choice.equals("Blue")){
            findViewById(R.id.mood_layout).setBackgroundColor(Color.BLUE);
            pathfile = "rain";
            imageView.setImageResource(R.drawable.img);
        } else if (choice.equals("Yellow")) {
            findViewById(R.id.mood_layout).setBackgroundColor(Color.YELLOW);
            pathfile = "horn";
            imageView.setImageResource(R.drawable.yellow_img);
        }else{
            findViewById(R.id.mood_layout).setBackgroundColor(Color.GREEN);
            pathfile = "bell";
            imageView.setImageResource(R.drawable.green_img);
        }





        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        String path = "android.resource://" + this.getPackageName() + "/raw/"+ pathfile;
        Uri uri = Uri.parse(path);
        try{
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}