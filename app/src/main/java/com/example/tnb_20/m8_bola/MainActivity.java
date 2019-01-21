package com.example.tnb_20.m8_bola;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
   public static Pilota[] arrayPilotes;
    public static int statusBar;
    public static int ample;
    public static int alt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout layout = findViewById(R.id.pantalla);
        statusBar = getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height", "dimen", "android")); // Calculem la mida de la pantalla per a que les pilotes no surtin dels marges
        DisplayMetrics display = this.getBaseContext().getResources().getDisplayMetrics();
        ample = display.widthPixels;//Ample de la pantalla
        alt = display.heightPixels; //Alt de la pantalla

        //Coloquem les 4 pilotes generades en posicions preestablertes de inici tal que quedin dispersades unes de les altres
        float[] posX= {alt/10*1, alt/10*2, alt/10*4, alt/10*8};
        float[] posY = {ample/10*1, ample/10*2, ample/10*4, ample/10*8};

        //Posem una velocitat a les pilotes (Es poden posar mes velocitats
        float[] vel = {2, -2};
        arrayPilotes = new Pilota[4];//Creem un array de 4 pilotes; Falta implementar demanar per pantalla cuantes pilotes vol l'usuari
        for (int i = 0; i<4 ; i++) {
            //Randomitzem la velocitat de les pilotes en els vectors X i Y
            Float tmpVelX = vel[new Random().nextInt(2)];
            Float tmpVelY =  vel[new Random().nextInt(2)];
            arrayPilotes[i]=new Pilota(tmpVelX,tmpVelY, i);
            arrayPilotes[i].pilotaView = new ImageView(getApplicationContext());//Asignem un imageView a la pilota
            layout.addView(arrayPilotes[i].pilotaView);//Asignem la imatge al layout dinamic

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) arrayPilotes[i].pilotaView.getLayoutParams();
            params.width = 120;
            params.height = 120;
            arrayPilotes[i].pilotaView.setLayoutParams(params);

            arrayPilotes[i].pilotaView.setImageResource(R.drawable.bola);//Asignem la imatge a la pilota
            //Randomitzem la posició d'inici de la pilota,caldria veure la probabilitat de que spawnejin 2 pilotes al mateix lloc
            Float tmpPosX = posY[new Random().nextInt(4)];
            Float tmpPosY = posX[i];
            arrayPilotes[i].pilotaView.setX(tmpPosX);
            arrayPilotes[i].pilotaView.setY(tmpPosY);
        }

        //Movem les pilotes de manera continua establint una tasa de refresc
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for(int i =0; i<4; i++) {
                    arrayPilotes[i].mourePilotes(2, 2);
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 1000,30);
    }



}
