package practicaps.space10;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    //declaro variables para los componentes
    private ImageView nave2;
    private ImageView nave;
    private ImageView disparo;
    public float coordenadaX = 0;
    private TextView tou;
    private SoundPool soundPool;
    private float coordenadaDisparo = 0;
    private int carga;
    ImageView[][]v = new ImageView[5][8];
    private GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //relaciono la variable nave con el objeto nave por su id, y así con el resto
        ImageView[][]v = new ImageView[5][8];
        grid =(GridLayout) findViewById(R.id.gridl);
        nave = (ImageView) findViewById(R.id.nave_id);
        tou = (TextView) findViewById(R.id.touch);
        disparo = (ImageView) findViewById(R.id.disp);
        soundPool = new SoundPool(8, AudioManager.STREAM_MUSIC,0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        carga = soundPool.load(this, R.raw.disparo,3);




        tou.setOnTouchListener(this);

        //obtengo el ancho del movil para guardarlo en una variable -> sin implementar, por el momento tamaños a mano

        //ejecuto el hilo
        new Thread(new Disparo()).start();


    }

    public void derecha(View view) {
        coordenadaX = nave.getX();
        Log.d("Posicion: ", String.valueOf(coordenadaX));
        if (coordenadaX < 968) {
            nave.setX(nave.getX() + 100);
        }
    }

    public void izquierda(View view) {
        coordenadaX = nave.getX();
        Log.d("Posicion", String.valueOf(coordenadaX));
        if (coordenadaX > 0) {
            nave.setX(nave.getX() - 100);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);
        float coorX = event.getX();
        int coorY = (int) event.getY();

        switch (action) {
            case (MotionEvent.ACTION_DOWN):


                /* Comentado, codigo para mover la nave mediante pulsacion en pantalla
                disparo.setVisibility(View.VISIBLE);
                disparo.setX(coorX);
                coordenadaDisparo = 964;
                for(int i=0; i<100; i++){
                    coordenadaDisparo = coordenadaDisparo - 5;

                    disparo.setY(coordenadaDisparo);
                    //SystemClock.sleep(10);
                    Log.d("PosicionDisparoX: ", String.valueOf(coorX));
                    Log.d("PosicionDisparoY: ", String.valueOf(coordenadaDisparo));
                }

                if ( coorX > 300) {

                    nave.setX(nave.getX() + 100);
                    return true;
                }
                if ( coorX < 300) {

                    nave.setX(nave.getX() - 100);
                    return true;
                }
                */

            case (MotionEvent.ACTION_MOVE):
                //Desplaza la nave siguiendo la pulsacion

                Log.d("Posicion: ", String.valueOf(coorX));
                Log.d("Posicion: ", String.valueOf(coorY));
                nave.setX(coorX);
                nave.setY(coorY - 100);
                if (coorY < 800) {
                    Log.d("Posicion: ", "Tocaste el limite");
                    nave.setY(700);
                }
                return true;
        }
        return false;
    }


    class Disparo implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                soundPool.play(carga,1,1,0,0,3);
                Disparo();
            }
        }

        public void Disparo() {
            Log.d("Disparo: ", "Se ha producido un disparo");
            coordenadaDisparo = nave.getY();

            disparo.setX(nave.getX());
            Log.d("Coordenada disparo: ", String.valueOf(coordenadaDisparo));

            for(int i=0; i<50; i++){
                int val = i;
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                coordenadaDisparo = coordenadaDisparo - val;
                Log.d("PUM: ",  String.valueOf(val));
                disparo.setY(coordenadaDisparo - val*20);
            }
        }
    }


}