package edu.nmhu.bssd5250.javaproject;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MoodActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        findViewById(R.id.mood_layout).setBackgroundColor(Color.BLUE);

        Intent intent = getIntent();
        String choice = intent.getStringExtra("extra");
        if(choice.equals("Blue")){ //Blue
            findViewById(R.id.mood_layout).setBackgroundColor(Color.BLUE);
        } else if(choice.equals("Tellow")) { //Yellow
            findViewById(R.id.mood_layout).setBackgroundColor(Color.YELLOW);
        } else { //Green
            findViewById(R.id.mood_layout).setBackgroundColor(Color.GREEN);
        }

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        String path = "android.resource://" + this.getPackageName() + "/raw/rain";
        Uri uri = Uri.parse(path);
        try {
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepare();
        } catch (IOException e){
            e.printStackTrace();
        }
        mediaPlayer.start();
    }
}