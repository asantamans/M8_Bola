package com.example.tnb_20.m8_bola;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    /*
    Funcionament del codi similar a MainActivityOld
    que es la app amb una pilota rebotant per la pantalla
     */
    private ArrayList<Pilota> arrayPilotes;
    //Nota: Serveix epr a obtenir les dimensions de la pantalla App i que no es surti la pilota
    private int status;
    private DisplayMetrics displayMetrics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.displayMetrics = this.getBaseContext().getResources().getDisplayMetrics();
        this.status = getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height", "dimen", "android"));

        renderitzarPilotes();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                mourePilota();
            }
        };
        //Refresc de la pantalla per actualitzar el moviment de les pilotes
        Timer timer = new Timer();
        timer.schedule(task, 100, 30);
    }
    private void renderitzarPilotes() {
        arrayPilotes = new ArrayList<>();

       //Pilota 1
        Pilota bola = new Pilota();
        bola.setDespX(20.0);
        bola.setDespY(20.0);
        bola.setX(4);
        bola.setY(4);
        bola.setPilota((ImageView) findViewById(R.id.pilota1));
        bola.setAmple(this.displayMetrics.widthPixels);
        bola.setAlt(this.displayMetrics.heightPixels);

        //Pilota2
        Pilota bola2 = new Pilota();
        bola2.setDespX(8.0);
        bola2.setDespY(8.0);
        bola2.setX(2);
        bola2.setY(2);
        bola2.setPilota((ImageView) findViewById(R.id.pilota1));
        bola2.setAmple(this.displayMetrics.widthPixels);
        bola2.setAlt(this.displayMetrics.heightPixels);

        arrayPilotes.add(bola);
        arrayPilotes.add(bola2);
    }

    private void mourePilota() {
        for (Pilota pilota: arrayPilotes) {
            double positionX = pilota.getX() + (pilota.getX() * pilota.getDespX());
            double positionY = pilota.getY() + (pilota.getY() * pilota.getDespY());

    compPosX(pilota, positionX);
    compPosY(pilota, positionY);

    calcularPosCreuament();
}
    }

private void calcularPosCreuament(){
        // Calculem la nova posicio a la que es desplaçaran les pilotes a rao de invertir el eix x i y per simular que xoquen (faltarien variables de pes per a mes realisme)
        if(arrayPilotes.get(0).getX() >= arrayPilotes.get(1).getX() || arrayPilotes.get(0).getX() <= arrayPilotes.get(1).getX() + arrayPilotes.get(1).getAmple()){
        arrayPilotes.get(0).setDespX(arrayPilotes.get(0).getDespX() * (-1));
        arrayPilotes.get(1).setDespX(arrayPilotes.get(1).getDespX() * (-1));
        }
        }


        //FUncions per a recalcular la posicioX i Y de la pilota
private void compPosX(Pilota pilota, double posicioX){
        if(pilota.getX() > 0){
        if(posicioX + pilota.getAmple() < pilota.getAmple()){
        pilota.setX((float) posicioX);
        }else{
        pilota.setX((float) pilota.getAmple() - pilota.getAmple());
        pilota.setDespX(pilota.getDespX() * (-1));
        }
        }else{
        if (posicioX > 0) {
        pilota.setX((double) posicioX);
        } else{
        pilota.setX(0);
        pilota.setDespX(pilota.getDespX() * (-1));
        }
        }
        }

private void compPosY(Pilota pilota, double posicioY) {
        if (pilota.getY() > 0) {
        if (posicioY + pilota.getAlt() + this.status < pilota.getAlt()) {
        pilota.setY((double) posicioY);
        } else {
        pilota.setY((double) pilota.getAlt() - pilota.getAlt() - this.status);
        pilota.setDespY(pilota.getDespY() * (-1));
        }
        } else {
        if (posicioY > 0) {
        pilota.setY((double) posicioY);
        } else {
        pilota.setY(0);
        pilota.setDespY(pilota.getDespY() * (-1));
        }
        }
        }


}

