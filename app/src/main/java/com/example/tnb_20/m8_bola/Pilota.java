package com.example.tnb_20.m8_bola;


import android.widget.ImageView;

public class Pilota {
    private ImageView pilota;
    private int ample;
    private int alt;
    private double x;
    private double y;
    private double despX;
    private double despY;


    //Generat tot de manera automatica
    public Pilota(){

    }

    public ImageView getPilota() {
        return pilota;
    }

    public int getAmple() {
        return ample;
    }

    public int getAlt() {
        return alt;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDespX() {
        return despX;
    }

    public double getDespY() {
        return despY;
    }

    public void setPilota(ImageView pilota) {
        this.pilota = pilota;
    }

    public void setAmple(int ample) {
        this.ample = ample;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setDespX(double despX) {
        this.despX = despX;
    }

    public void setDespY(double despY) {
        this.despY = despY;
    }
}
