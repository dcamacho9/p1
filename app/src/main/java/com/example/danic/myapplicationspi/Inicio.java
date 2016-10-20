package com.example.danic.myapplicationspi;

import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Inicio extends AppCompatActivity implements View.OnTouchListener {
    public float coordenadaX = 0;
    private Button boton3;
    private ImageView nave;
    private float coordenadaDisparo = 0;
    private ImageView disparo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        boton3 = (Button) findViewById(R.id.button3);
        nave = (ImageView) findViewById(R.id.imageView7);
        disparo = (ImageView) findViewById(R.id.imageView8);
        new Thread(new Disparo()).start();


        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });
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
                Disparo();

            }
        }
    }

        public void Disparo() {
            Log.d("Disparo: ", "Se ha producido un disparo");
            coordenadaDisparo = nave.getY();

            disparo.setX(nave.getX());
            Log.d("Coordenada disparo: ", String.valueOf(coordenadaDisparo));

            for (int i = 0; i < 50; i++) {
                int val = i;
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                coordenadaDisparo = coordenadaDisparo - val;
                Log.d("PUM: ", String.valueOf(val));
                disparo.setY(coordenadaDisparo - val * 20);
            }
        }
    }


