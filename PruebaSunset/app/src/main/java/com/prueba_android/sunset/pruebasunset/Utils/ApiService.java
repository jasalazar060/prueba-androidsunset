package com.prueba_android.sunset.pruebasunset.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ProgramaTuTambien on 25/04/2017.
 */

public class ApiService {

    public static String OK_CODE = "OK";
    public static String ERROR_CODE = "ERROR";
    private Context contexto;
    private Fragment fragment;
    private int flag;

    private static ApiService instance = null;

    protected ApiService() {
        // Exists only to defeat instantiation.
    }

    public static ApiService getInstance() {
        if(instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    public String request(String type, String url,String json,Context context,int flag,Fragment fragment) {
        //Crear manager para detectar conexión

        this.contexto = context;
        this.fragment = fragment;

        ConnectivityManager connMgr = (ConnectivityManager)
                this.contexto.getSystemService(Context.CONNECTIVITY_SERVICE);

        this.flag = flag;

        if(isConnected(connMgr)) {

            MyAsynTask task = new MyAsynTask();

            task.execute(url,json,type);

            return OK_CODE;

        }else{
            return ERROR_CODE;
        }

    }

    public boolean isConnected(ConnectivityManager connMgr ) {

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }

    }

    //Tarea asíncrona para peticiones al server
    private class MyAsynTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {

            String urlString = params[0];
            String json = params[1];
            String type = params[2];


            String respuesta = "Unable to retrieve web page. URL may be invalid.";

            switch (type) {

                case "GET":

                    respuesta = GET(urlString);
                    break;

                case "POST":

                    respuesta = POST(urlString, json);
                    break;
            }

            return respuesta;

        }
        @Override
        protected void onPreExecute(){

            progressDialog = new ProgressDialog(contexto);
            progressDialog.setMessage("Obteniendo posiciones, espere...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();

        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute (String result){

            Log.d("APISERVICE", "Operation ended " + result);

            if(contexto instanceof  ApiServiceFather){
                ApiServiceFather activity = (ApiServiceFather)contexto;
                activity.usarDatoRespuesta(result,flag);
            }else if(!Services.isNull(fragment) && fragment instanceof  ApiServiceFather){
                ApiServiceFather activity = (ApiServiceFather)fragment;
                activity.usarDatoRespuesta(result,flag);
            }

            if(progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }

    }

    //Lee el flujo de bytes obtenido de la respuesta del servidor
    private String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

    public String GET(String urlString){
        String respuesta="";
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                respuesta = getStringFromInputStream(in);
                Log.d("APISERVICE", respuesta);

            } finally {
                urlConnection.disconnect();
            }

        } catch (IOException e) {
            respuesta=e.getLocalizedMessage();
        }
        return respuesta;
    }

    public String POST(String urlString,String json){
        InputStream inputStream = null;
        String result = "";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/json");

            //send the POST out
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(json);
            out.flush();
            out.close();

            // Send POST output.
            int statusCode = conn.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                result="statuscode " + statusCode;
            }

            conn.connect();
            result=receiveResponse(conn);

        } catch (Exception e) {
            Log.d("InputStream", "error " + e.getLocalizedMessage());
            result=ERROR_CODE + "error " + e.getLocalizedMessage() + " " + json;
        }

        // 11. return result

        return result;
    }

    public String receiveResponse(HttpURLConnection conn)
            throws IOException {
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        // retrieve the response from server
        InputStream is = null;
        try {
            is = conn.getInputStream();
            int ch;
            StringBuffer sb = new StringBuffer();
            while ((ch = is.read()) != -1) {
                sb.append((char) ch);
            }
            return sb.toString();
        } catch (IOException e) {
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

}
