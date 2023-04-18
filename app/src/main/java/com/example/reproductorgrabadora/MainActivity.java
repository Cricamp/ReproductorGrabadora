package com.example.reproductorgrabadora;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button play_pause, btn_repetir;
    MediaPlayer mp;
    ImageView iv;
    int repetir = 1, posicion = 0;
    //Crear Mediaplayer tipo array
    MediaPlayer vectormp [] = new MediaPlayer[3];

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play_pause = (Button) findViewById(R.id.btn_reproducir);
        btn_repetir = (Button) findViewById(R.id.btn_repetir);
        iv = (ImageView) findViewById(R.id.iv1);

        vectormp[0] = MediaPlayer.create(this, R.raw.race);
        vectormp[1] = MediaPlayer.create(this, R.raw.sound);
        vectormp[2] = MediaPlayer.create(this, R.raw.tea);
    }

    //Metodo para el boton play y pause
    public void PlayPause(View view){
        //si vectormp en la posicion en la que este,se esta reproduciendo
        if(vectormp[posicion].isPlaying()){
            //pausar reproduccion
            vectormp[posicion].pause();
            //cambiar imagen de play a pause
            play_pause.setBackgroundResource(R.drawable.reproducir);
            //comentario de pause
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        }else{
            //si no se esta reproduciendo en la posicion en la que este, reproducemelo(.start())
            vectormp[posicion].start();
            //Cambio de imagen de pause a play
            play_pause.setBackgroundResource(R.drawable.pausa);
            //comentario de play
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
        }
    }
    //Metodo para el boton stop
    public void Stop(View view){
        //Para el boton Stop se necesita borrar todo
        if(vectormp[posicion] !=null){
            vectormp[posicion].stop();

            vectormp[0] = MediaPlayer.create(this, R.raw.race);
            vectormp[1] = MediaPlayer.create(this, R.raw.sound);
            vectormp[2] = MediaPlayer.create(this, R.raw.tea);
            posicion = 0;
            play_pause.setBackgroundResource(R.drawable.reproducir);
            iv.setImageResource(R.drawable.portada1);
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para repetir pista
    public void repetir(View view){
        //s repetir es igual a uno significa que esA repitiendose
        if(repetir==1){
            btn_repetir.setBackgroundResource(R.drawable.repetir);
            Toast.makeText(this, "No repetir", Toast.LENGTH_SHORT).show();
            //para que no se repita
            vectormp[posicion].setLooping(false);
            repetir = 2;
        }else{
            btn_repetir.setBackgroundResource(R.drawable.no_repetir);
            Toast.makeText(this, "Repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(true);
            repetir = 1;
        }
    }
    //Metodo para siguiente cancion
    public void Siguiente(View view){

        //posicion (indice) vertormp (cantidad) por eso se tiene que restar 1. con .length sabe cuantas canciones hay dentro
        if(posicion < vectormp.length - 1){
            //El vector en la posicion que venga, .isPlaying(Esta reproduciendose??)
            if(vectormp[posicion].isPlaying()){
                //si esta reproduciendose paramelo
                vectormp[posicion].stop();
                vectormp[0] = MediaPlayer.create(this, R.raw.race);
                vectormp[1] = MediaPlayer.create(this, R.raw.sound);
                vectormp[2] = MediaPlayer.create(this, R.raw.tea);
                //aumentas la posicion
                posicion++;
                //Reproduce la cancion que entra
                vectormp[posicion].start();

                if(posicion==0){
                    iv.setImageResource(R.drawable.portada1);

                } else if (posicion==1) {
                    iv.setImageResource(R.drawable.portada2);

                } else if (posicion==2) {
                    iv.setImageResource(R.drawable.portada3);

                }

            }

        }else{


            Toast.makeText(this, "No hay mas canciones", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo Regresar cancion
    public void Anterior(View view){
        //posicion (indice) vertormp (cantidad) por eso se tiene que restar 1. con .length sabe cuantas canciones hay dentro
        if(posicion < vectormp.length - 1){
            //El vector en la posicion que venga, .isPlaying(Esta reproduciendose??)
            if(vectormp[posicion].isPlaying()){
                //si esta reproduciendose paramelo
                vectormp[posicion].stop();
                vectormp[0] = MediaPlayer.create(this, R.raw.race);
                vectormp[1] = MediaPlayer.create(this, R.raw.sound);
                vectormp[2] = MediaPlayer.create(this, R.raw.tea);
                //disminulles la posicion
                posicion--;
                //Reproduce la cancion que entra
                vectormp[posicion].start();

                if(posicion==0){
                    iv.setImageResource(R.drawable.portada1);

                } else if (posicion==1) {
                    iv.setImageResource(R.drawable.portada2);

                } else if (posicion==2) {
                    iv.setImageResource(R.drawable.portada3);

                }

            }

        }else{


            Toast.makeText(this, "No hay mas canciones", Toast.LENGTH_SHORT).show();
        }
    }
}



