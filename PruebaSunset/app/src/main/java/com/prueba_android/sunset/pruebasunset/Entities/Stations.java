package com.prueba_android.sunset.pruebasunset.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ProgramaTuTambien on 26/04/2017.
 */

public class Stations {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("desc")
    private String desc;
    @SerializedName("stations")
    private List<Stations> stations;
    @SerializedName("items")
    private List<MarkerLocal> items;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Stations> getStations() {
        return stations;
    }

    public void setStations(List<Stations> stations) {
        this.stations = stations;
    }

    public List<MarkerLocal> getItems() {
        return items;
    }

    public void setItems(List<MarkerLocal> items) {
        this.items = items;
    }
}
