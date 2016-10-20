package com.example.danic.myapplicationspi;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView nave;
    private ImageView rayo;
    private Button boton;
    private Button boton1;
    private Button boton2;
    private Button boton4;
    MediaPlayer mediaPlayer;
    SoundPool soundPool;
    int carga;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nave =(ImageView) findViewById(R.id.imageView7);
        rayo=(ImageView) findViewById(R.id.imageView8);
        boton = (Button) findViewById(R.id.button);
        boton1 =(Button)findViewById(R.id.button2);
        boton2 = (Button) findViewById(R.id.button4);
        boton4=(Button)findViewById(R.id.button5);

        boton.setOnClickListener(this);
        boton1.setOnClickListener(this);
        boton4.setOnClickListener(this);
        boton2.setOnClickListener(this);
        mediaPlayer = new MediaPlayer();
        soundPool= new SoundPool(8, AudioManager.STREAM_MUSIC,0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        carga= soundPool.load(this,R.raw.disparo,1);






        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Inicio.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button4:
                soundPool.play(carga,1,1,0,0,1);
                play_mp();

            case R.id.button5:
                soundPool.play(carga,1,1,0,0,1);
                stop_mp();

            case R.id.button:
                soundPool.play(carga,1,1,0,0,1);

            case R.id.button2:
                soundPool.play(carga,1,1,0,0,1);
            }

    }

    private void play_mp() {
        Thread playThread = new Thread() {
            public void run() {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.mu);
                mediaPlayer.start();
            }
        };
        playThread.start();

    }
    private void stop_mp(){
        mediaPlayer.stop();
    }
}
