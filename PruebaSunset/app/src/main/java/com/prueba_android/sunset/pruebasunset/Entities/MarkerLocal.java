package com.prueba_android.sunset.pruebasunset.Entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ProgramaTuTambien on 25/04/2017.
 */

public class MarkerLocal {

    @SerializedName("id")
    private int id;
    @SerializedName("order")
    private int order;
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("description")
    private String description;
    @SerializedName("lat")
    private double lat;
    @SerializedName("lon")
    private double lon;
    @SerializedName("type")
    private String type;
    @SerializedName("capacity")
    private int capacity;
    @SerializedName("bikes")
    private int bikes;
    @SerializedName("places")
    private String places;
    @SerializedName("picture")
    private String picture;
    @SerializedName("bikes_state")
    private String bikesState;
    @SerializedName("places_state")
    private String placesState;
    @SerializedName("closed")
    private int closed;
    @SerializedName("cdo")
    private int cdo;

    public MarkerLocal(int id, int order, String name, String address, String description, double lat, double lon, String type,
                       int capacity, int bikes, String places, String picture, String bikesState, String placesState, int closed, int cdo){
        this.id = id;
        this.order = order;
        this.name = name;
        this.address = address;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.type = type;
        this.capacity = capacity;
        this.bikes = bikes;
        this.places = places;
        this.picture = picture;
        this.bikesState = bikesState;
        this.placesState = placesState;
        this.closed = closed;
        this.cdo = cdo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBikes() {
        return bikes;
    }

    public void setBikes(int bikes) {
        this.bikes = bikes;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBikesState() {
        return bikesState;
    }

    public void setBikesState(String bikesState) {
        this.bikesState = bikesState;
    }

    public String getPlacesState() {
        return placesState;
    }

    public void setPlacesState(String placesState) {
        this.placesState = placesState;
    }

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    public int getCdo() {
        return cdo;
    }

    public void setCdo(int cdo) {
        this.cdo = cdo;
    }
}
