package cynthia.blocklotto.conection;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Conection extends AsyncTask<String, Void, String> {
    private String urlString = "URL DE LA API";
    private String address = "prueba";
    private String pub = "prueba";
    private String id = "prueba";
    private String field = "";


    @Override
    protected String doInBackground(String... urls) {
        establishAPI();
        return "CORRECT";
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            this.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void establishAPI() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = null;
        HttpURLConnection connection;

        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            String json = "";
            while ((inputLine = buffer.readLine()) != null) {
                response.append(inputLine);
            }

            json = response.toString();
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.d("SALIDA", jsonObject.optString(field));

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setField(String field) {
        this.field = field;
    }

    //Méthod for results
    public void getResult() {
    }
}
