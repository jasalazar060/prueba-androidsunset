package com.prueba_android.sunset.pruebasunset.Utils;

import com.prueba_android.sunset.pruebasunset.Classes.Preferences;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ProgramaTuTambien on 26/04/2017.
 */

public class ApiClient {

    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Preferences.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
