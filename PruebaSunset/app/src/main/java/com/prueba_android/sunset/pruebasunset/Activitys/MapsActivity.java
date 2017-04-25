package com.prueba_android.sunset.pruebasunset.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.prueba_android.sunset.pruebasunset.Classes.Preferences;
import com.prueba_android.sunset.pruebasunset.Entities.MarkerLocal;
import com.prueba_android.sunset.pruebasunset.R;
import com.prueba_android.sunset.pruebasunset.Utils.ApiService;
import com.prueba_android.sunset.pruebasunset.Utils.ApiServiceFather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, ApiServiceFather {

    //---VARIABLES---------------------
    LatLng myLocation;
    GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //------INSTANCIANDO MAPFRAGMENT------
        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.miMapa);
        mapFragment.getMapAsync(this);

        String res = ApiService.getInstance().request("GET", Preferences.SERVER, "", this, 0, null);
        if(res != ApiService.OK_CODE){
            Toast.makeText(this, "Error de conexión", Toast.LENGTH_SHORT).show();
        }

    }

    //----METODO ON MAP READY
    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
        myMap.setMyLocationEnabled(true);
    }

    //-----METODO QUE RECIVE LA RESPUESTA DEL SERVER
    @Override
    public void usarDatoRespuesta(String result, int flag) {
        try{

            JSONObject jsonObject = new JSONObject(result);
            JSONObject stations = jsonObject.getJSONArray("stations").getJSONObject(0);
            JSONArray items = stations.getJSONArray("items");

            if(stations.length() > 0){

                List<MarkerLocal> markers = new ArrayList<>();
                markers.clear();

                for(int i = 0; i < items.length(); i++){
                    JSONObject item = items.getJSONObject(i);
                    int id = item.getInt("id");
                    String name = item.getString("name");
                    String picture = item.getString("picture");
                    double latitude = item.getDouble("lat");
                    double longitude = item.getDouble("lon");
                    markers.add(new MarkerLocal(id, name, picture, latitude, longitude));
                }

                for(int i = 0; i < markers.size(); i++){
                    MarkerLocal mark = markers.get(i);

                    if(myLocation == null){
                        myLocation = new LatLng(mark.getLatitude(), mark.getLongitude());
                        myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15.0f));
                    }

                    myMap.addMarker(new MarkerOptions().title(mark.getName()).position(new LatLng(mark.getLatitude(), mark.getLongitude())));
                }
            }

        }catch (JSONException e){
            Toast.makeText(this, "Ups, ocurrió un problema. Intenta de nuevo!", Toast.LENGTH_SHORT).show();
            Log.d("ERROR", e.getLocalizedMessage());
        }
    }
}
