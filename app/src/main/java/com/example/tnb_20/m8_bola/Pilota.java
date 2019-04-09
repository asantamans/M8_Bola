package com.example.tnb_20.m8_bola;

import android.widget.ImageView;

public class Pilota {
    int pilotaId;//Identificador unic de les pilotes per a identificar quan colisiona amb una altra diferent
    ImageView pilotaView;
    float velX;
    float velY;


    //generat automaticament
    public Pilota(float velX, float velY, int id){
        this.velX = velX;
        this.velY = velY;
        this.pilotaId = id;
    }

    //Funcio per calcular moviment de les pilotes dins la pantalla SENSE colisions, en cas de colisio, veure xocPilota
    public void mourePilotes(float x, float y) {
        //Si X > 0, segons l'eix de coordenades es mou a la dreta,
        //En cas contrari, es mou cap a l'esquerra


        //EIX DE LES X
        //Per obtenir la nova pos, es tan facil com pos = posX(Actual) + velX*x donat que no hi ha variable T, fem servir X
        float recalculPosX = pilotaView.getX() + x * velX;
        if ( 0 < pilotaView.getX() ) {
            //Comprovacio per evitar que es surti de la pantalla la imatge i perdem una pilota
            if ( MainActivity.ample > pilotaView.getWidth()+recalculPosX ) {
                pilotaView.setX(recalculPosX);
            } else {//La pilota surt de la pantalla per el calcul de moviment, reposicionem la coordenada X al limit de la pantalla
                pilotaView.setX(MainActivity.ample - pilotaView.getWidth());
                velX=(-1)*velX;//Important el -1, ja que aixi invertim la direccio i evitem que s'encalli al mateix eix, simula que rebota
            }
        } else {
            //Mateix cas anterior explicat adalt, pero donat que X < 0, segons l'eix de coordenades es mou a l'esquerra
            if ( 0< recalculPosX ) {
                pilotaView.setX(recalculPosX);
            } else{
                //La coloquem a la posició 0 i fem que reboti; mateix cas anterior
                pilotaView.setX(0);
                velX=(-1)*velX;
            }
        }

        //EIX DE LES Y
        //Mateix cas anterior de l'eix X pero amb l'eix Y
        //A diferencia del X, si Y>0 --> Es mou cap a dalt
        //Si Y < 0 --> Es mou cap abaix
        float recalculPosY = pilotaView.getY() + y * velY;
        if ( 0 < pilotaView.getY() ) {
            if ( MainActivity.alt > MainActivity.statusBar + recalculPosY + pilotaView.getHeight()  ) {//
                pilotaView.setY(recalculPosY);
            } else {
                pilotaView.setY(MainActivity.alt - pilotaView.getHeight() - MainActivity.statusBar);
                velY=(-1)*velY;
            }
        }else {
            if ( 0 < recalculPosY) {
                pilotaView.setY(recalculPosY);
            } else {
                pilotaView.setY(0);
                velY=(-1)*velY;
            }
        }
        //Un cop recalculat entre parets, recalculem el resultat amb Colisio SI ES NECESARI --> es a dir, la pilota XOCA amb una altra pilota diferent
       // xocPilota(x, y); Obsolet, provoca que la app es bugueji --> Sense la funcio
        /*
        xocPilota(x,y) provoca que es recalculi la posicio de la pilota altre vegada, donant una altra posicio incorrecte a la pilota, provocant que aquesta es surti dels marges de l'app
        cal acabar de pulir que nomes recalculi la posicio si NOMES es necesari, es a dir, en cas de xoc amb una altra pilota --> id_pilota 1 != id_pilota_2 (dos pilotes diferents)
         */
    }

    public void xocPilota(float x, float y){
        //Per evitar que colisioni amb ella mateixa, tenen que ser dos pilotes diferents, es a dir, dos pilotesId diferents
        for (Pilota Pilota : MainActivity.arrayPilotes) {
            /*Mateixa logica anterior que el recalcular posicio, a excepcio, que s'han de invertir els eixos amb el de la pilota que xoqui segons COM sigui el xoc
            SI: X>0-->Moviment cap a la dreta
            Si X < 0 --> Moviment cap a l'esquerra
            Si Y >0 --> Moviment cap a dalt
            Si Y < 0 --> Moviment cap abaix
            Segons l'eix d'abcisses
             */
            //EIX DE LES X
            //Mirem que els id de les pilotes siguin diferents, ja que si no, xocaria entre ella continuament
            if (Pilota.pilotaId!=this.pilotaId){//CAL PULIR EL RECONEIXEMENT DELS ID
                float recalculPosX = this.pilotaView.getX() + x * velX;//Per obtenir la nova pos, es tan facil com pos = posX(Actual) + velX*x donat que no hi ha variable T, fem servir X
                if (this.pilotaView.getX() < Pilota.pilotaView.getX()) {
                    //Comprovacio per evitar que es surti de la pantalla la imatge i perdem una pilota--> En calcularXocPilota ens dona problemes
                    if ( Pilota.pilotaView.getX()+Pilota.pilotaView.getWidth() < recalculPosX + this.pilotaView.getWidth()) {
                        this.pilotaView.setX(recalculPosX);
                    } else {
                        //La pilota surt de la pantalla per el calcul de moviment, reposicionem la coordenada X al limit de la pantalla
                        this.pilotaView.setX(MainActivity.ample - this.pilotaView.getWidth());
                        velX=(-1)*velX;
                    }
                } else {// Fem el mateix pel moviment cap a l'esquerra
                    if (recalculPosX < Pilota.pilotaView.getX()) {
                        this.pilotaView.setX(recalculPosX);
                    } else{
                        this.pilotaView.setX(0);
                        velX=(-1)*velX;//Important el -1, ja que aixi invertim la direccio i evitem que s'encalli al mateix eix, simula que rebota
                    }
                }



                //Mateix cas anterior de l'eix X pero amb l'eix Y
                float recalculPosY = this.pilotaView.getY() + y * velY;
                if (this.pilotaView.getY() < Pilota.pilotaView.getY()) {
                    if (  Pilota.pilotaView.getY()+Pilota.pilotaView.getHeight() < recalculPosY + this.pilotaView.getHeight() + MainActivity.statusBar ) {
                        this.pilotaView.setY(recalculPosY);
                    } else {
                        this.pilotaView.setY(MainActivity.ample - this.pilotaView.getHeight() - MainActivity.statusBar);
                        velY=(-1)*velY;//Important el -1
                    }
                }else {
                    if (recalculPosY < Pilota.pilotaView.getY()) {
                        this.pilotaView.setY(recalculPosY);
                    } else {
                        this.pilotaView.setY(0);
                        velY=(-1)*velY;
                    }
                }

            }
        }
    }
}
