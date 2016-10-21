package practicaps.space10;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.VideoView;
import android.net.Uri;


public class Menu extends AppCompatActivity {

    public Button boton_empezar;
    public Button boton_musica;
    SoundPool soundPool;
    int carga;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        //declaraciones para salto a otra pantalla
        boton_musica=(Button) findViewById(R.id.boton_mu);
        boton_empezar = (Button) findViewById(R.id.bot_emp);
        VideoView videoView = (VideoView)findViewById(R.id.videoGif);
        soundPool = new SoundPool(8, AudioManager.STREAM_MUSIC,0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        carga= soundPool.load(this,R.raw.disparo,1);
        mediaPlayer= new MediaPlayer();
        boton_musica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_mp();
                soundPool.play(carga,1,1,0,0,2);

            }
        });


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });



        Uri path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.gif2);

        videoView.setVideoURI(path);

        videoView.start();



        boton_empezar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent boton_empezar = new Intent(Menu.this, MainActivity.class);
                startActivity(boton_empezar);


            }

        });
    }
    public void play_mp(){
        Thread playThread = new Thread(){
            public void run()
            {
                mediaPlayer = MediaPlayer.create(Menu.this,R.raw.sonido);
                mediaPlayer.start();

            }
        };
        playThread.start();
    }
}

