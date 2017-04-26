package com.prueba_android.sunset.pruebasunset.Utils;

import com.prueba_android.sunset.pruebasunset.Classes.Preferences;
import com.prueba_android.sunset.pruebasunset.Entities.MarkerLocal;
import com.prueba_android.sunset.pruebasunset.Entities.Stations;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ProgramaTuTambien on 26/04/2017.
 */

public interface ApiInterface {

    @GET(Preferences.BASE_URL)
    Call<Stations> getStations();

}
