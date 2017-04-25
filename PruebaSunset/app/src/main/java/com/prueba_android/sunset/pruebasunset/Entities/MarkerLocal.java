package com.prueba_android.sunset.pruebasunset.Entities;

/**
 * Created by ProgramaTuTambien on 25/04/2017.
 */

public class MarkerLocal {

    int id;
    String name;
    String picture;
    double latitude;
    double longitude;

    public MarkerLocal(){
        //empty construct
    }

    public MarkerLocal(int id, String name, String picture, double latitude, double longitude){
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
