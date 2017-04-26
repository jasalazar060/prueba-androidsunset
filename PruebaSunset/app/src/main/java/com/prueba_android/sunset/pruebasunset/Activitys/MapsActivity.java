package com.prueba_android.sunset.pruebasunset.Activitys;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.prueba_android.sunset.pruebasunset.Entities.MarkerLocal;
import com.prueba_android.sunset.pruebasunset.Entities.Stations;
import com.prueba_android.sunset.pruebasunset.R;
import com.prueba_android.sunset.pruebasunset.Utils.ApiClient;
import com.prueba_android.sunset.pruebasunset.Utils.ApiInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    //---VARIABLES---------------------
    LatLng myLocation;
    GoogleMap myMap;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //---------MOSTRAR PROGRESS DIALOG----------
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Obteniendo posiciones, espera un momento...");
        dialog.show();

        //------INSTANCIANDO MAPFRAGMENT------
        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.miMapa);
        mapFragment.getMapAsync(this);

        //------PETICION UTILIZANDO RETROFIT----
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Stations> call = apiService.getStations();
        call.enqueue(new Callback<Stations>() {
            @Override
            public void onResponse(Call<Stations> call, Response<Stations> response) {
                List<Stations> stations = response.body().getStations();
                Log.d("SIZE", "The size results is " + stations.size());
                showMarkers(stations);
            }

            @Override
            public void onFailure(Call<Stations> call, Throwable t) {
                //ERROR
                Log.d("ERROR", t.toString());
            }
        });

    }

    //----METODO ON MAP READY
    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
        myMap.setMyLocationEnabled(true);
        myMap.getUiSettings().setZoomControlsEnabled(true);
    }

    //----------METODO PARA MOSTRAR LOS MARCADORES OBTENIDOS EN EL MAPA------------
    public void showMarkers(List<Stations> stations){
        for (int i = 0; i < stations.size(); i++){
            List<MarkerLocal> markers = stations.get(i).getItems();
            //-------AGREGAR MARKERS SOBRE EL MAPA------
            for(int n = 0; n < markers.size(); n++){
                myLocation = new LatLng(markers.get(n).getLat(), markers.get(n).getLon());
                myMap.addMarker(new MarkerOptions()
                .position(myLocation)
                .title(markers.get(n).getName())
                .snippet(markers.get(n).getAddress()));
            }
            //----ANIMAR CAMARA EN EL MAPA-------
            if(i == stations.size() - 1){
                myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 12.5f));
                if(dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        }
    }

}
