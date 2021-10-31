package edu.nmhu.bssd5250.javaproject;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MoodActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "edu.nmhu.bssd5250.javaproject.MESSAGE";
    public ImageView image;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        findViewById(R.id.mood_layout).setBackgroundColor(Color.BLUE);
        image = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();
        String choice = intent.getStringExtra(EXTRA_MESSAGE);
        if(choice.equals("Blue")){ //Blue
            findViewById(R.id.mood_layout).setBackgroundColor(Color.BLUE);
            image.setImageResource(R.drawable.blue);
            String sound = "/raw/rain";
            playSound(sound);
        } else if(choice.equals("Yellow")) { //Yellow
            findViewById(R.id.mood_layout).setBackgroundColor(Color.YELLOW);
            image.setImageResource(R.drawable.yellow);
            String sound = "/raw/birds";
            playSound(sound);
        } else { //Green
            findViewById(R.id.mood_layout).setBackgroundColor(Color.GREEN);
            image.setImageResource(R.drawable.green);
            String sound = "/raw/wind";
            playSound(sound);
        }
    }

    void playSound(String sound) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        String path = "android.resource://" + this.getPackageName() + sound;
        Uri uri = Uri.parse(path);
        try {
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepare();
        } catch (IOException e){
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