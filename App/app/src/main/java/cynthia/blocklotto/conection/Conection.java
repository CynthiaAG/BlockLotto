package cynthia.blocklotto.conection;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cynthia.blocklotto.R;
import cynthia.blocklotto.wallet.Wallet;

public class Conection extends AsyncTask<String, Void, String> {
    public ConectionResponse conectionResponse = null;
    private String json_aux;
    private Context context;

    //ORDER to params --> url (api), request
    @Override
    protected String doInBackground(String... urls) {
        if((!isNetEnabled(context)) || (!isOnline())){
            json_aux = null;
            return null;
        }else {
            return connectAPI(urls[0], urls[1]);
        }

    }

    @Override
    protected void onPostExecute(String result) {
        try {
            conectionResponse.processFinish(result);
            this.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private boolean isNetEnabled(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();
        return (actNetInfo != null && actNetInfo.isConnected());
    }

    private Boolean isOnline() {
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");
            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void createWallet(String pass, Context context){
        this.context = context;
        String url = "http://178.62.118.25:8080/BlockLotto/service/nw";
        String pin ="{" + "\"pass\""+ ":" + "\"" + pass + "\"" + "}";
        this.doInBackground(url, pin);
    }
    public String[] getResultCreateWallet(){
        String [] result = new String [4];

        if(json_aux == null){
            return null;
        }else {
            Gson gson = new Gson();
            Wallet element = gson.fromJson(json_aux, Wallet.class);

            String[] words = element.getSeedWords();
            String twentywords = "";
            for (String word : words) {
                twentywords = twentywords + " " + word;
            }

            result[0] = element.getId();
            result[1] = element.getPub();
            result[2] = element.getAddress();
            result[3] = twentywords.trim();

            return result;
        }
    }

    public void recuperateWallet(String pass, String twentyFourWords, Context context){
        this.context = context;
        String url = "http://178.62.118.25:8080/BlockLotto/service/rw";
        String pin ="\"pass\""+ ":" + "\"" + pass + "\"";
        String words = "\"seedWords\"" + ":" + twentyFourWords;

        String request = "{" + pin + "," + " " + words + "}";
        this.doInBackground(url, request);
    }

    public String [] getResultRecuperateWallet(){
        String [] result = new String [3];
        if(json_aux == null){
            return null;
        }else {
            Gson gson = new Gson();
            Wallet element = gson.fromJson(json_aux, Wallet.class);

            if (element.isUnlock()) {
                result[0] = element.getId();
                result[1] = element.getPub();
                result[2] = element.getAddress();
                return result;
            } else {
                return null;
            }
        }
    }

    public void getBalance(Context context){
        this.context = context;
        String url = "http://178.62.118.25:8080/BlockLotto/service/wb";
        String request = checkDataFile(context);
        request = "{" + "\"" + "id" + "\"" + ":" + "\""+ request + "\"" + "}";
        this.execute(url, request);
    }


    public void getBalanceChannel(Context context){
        this.context = context;
        String url = "http://178.62.118.25:8080/BlockLotto/service/tbc";
        String request = checkDataFile(context);
        request = "{" + "\"" + "id" + "\"" + ":" + "\""+ request + "\"" + "}";
        this.execute(url, request);
    }

    public void getBTCFromChannels(Context context){
        this.context = context;
        String url = "http://178.62.118.25:8080/BlockLotto/service/wf";
        String request = checkDataFile(context);
        request = "{" + "\"" + "id" + "\"" + ":" + "\""+ request + "\"" + "}";
        this.execute(url, request);
    }

    public void getNextLotteries(Context context){
        this.context = context;
        String url = "http://178.62.118.25:8080/BlockLotto/service/listud";
        String request = checkDataFile(context);
        request = "{" + "\"" + "id" + "\"" + ":" + "\""+ request + "\"" + "}";
        this.execute(url, request);
    }

    public void getPendingLotteries(Context context){
        this.context = context;
        String url = "http://178.62.118.25:8080/BlockLotto/service/listdp";
        String request = checkDataFile(context);
        request = "{" + "\"" + "id" + "\"" + ":" + "\""+ request + "\"" + "}";
        this.execute(url, request);
    }

    public void getCelebratedLotteries(Context context){
        this.context = context;
        String url = "http://178.62.118.25:8080/BlockLotto/service/listpast";
        String request = checkDataFile(context);
        request = "{" + "\"" + "id" + "\"" + ":" + "\""+ request  + "\"" + "}";
        this.execute(url, request);
    }

    public void getNotifications(Context context){
        this.context = context;
        String url = "http://178.62.118.25:8080/BlockLotto/service/notifications";
        String request = checkDataFile(context);
        request = "{" + "\"" + "id" + "\"" + ":" + "\""+ request + "\"" + "}";
        this.execute(url, request);
    }

    public void setNotificationsRead(int id, Context context){
        this.context = context;
        String url = "http://178.62.118.25:8080/BlockLotto/service/nr";
        String request = "{" + "\"" + "id" + "\"" + ":" + id  + "}";
        this.execute(url, request);
    }

    public void buyLottery(int idLottery, int amountTicket, Context context){
        this.context = context;
        String url = "http://178.62.118.25:8080/BlockLotto/service/br";
        String idWallet = checkDataFile(context);
        String request = "{" + "\"" + "id" + "\"" + ":" + "\""+ idWallet + "\"" + ","
                + "\"" + "codRaffle" + "\"" + ":" + idLottery + ","
                + "\"" + "number" + "\"" + ":" + amountTicket + "}";
        this.execute(url, request);
    }

    public void getTransaction(Context context){
        this.context = context;
        String url = "http://178.62.118.25:8080/BlockLotto/service/pm";
        String request = checkDataFile(context);
        request = "{" + "\"" + "id" + "\"" + ":" + "\""+ request + "\"" + "}";
        this.execute(url, request);
    }

    public void sendBTC(Context context, String pub, Double amount){
        this.context = context;
        String url = "http://178.62.118.25:8080/BlockLotto/service/tc";
        String id = checkDataFile(context);
        String request = "{" + "\"" + "id" + "\"" + ":" + "\"" + id + "\"" + ","
                + "\"" + "toWallet" + "\"" + ":" + "\"" + pub + "\""  + ","
                + "\"" + "amount" + "\"" + ":" + amount + "}";
        this.execute(url, request);
    }


    private String connectAPI(String uri, String json) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(300000); //set timeout to 5 min

            //request
            JSONObject cred = new JSONObject(json);
            OutputStreamWriter wr= new OutputStreamWriter(connection.getOutputStream());
            wr.write(cred.toString());
            OutputStream os = connection.getOutputStream();
            os.write(cred.toString().getBytes("UTF-8"));
            connection.connect();

            //response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            json_aux = "";
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            json_aux = response.toString();
            connection.disconnect();
           // jsonArray = null;
          //  jsonArray = new JSONObject(json_aux);

          /*  for (int i = 0; i < jsonArray.length(); i++) {
                //trajo el json del servidor
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                System.out.println(jsonObject);
                System.out.println("SALIDAA"+ jsonObject.optString("seedWords") );
            }*/
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json_aux;
    }

    private String checkDataFile(Context context){
        SharedPreferences preference = context.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String id = preference.getString("id","-1");
        System.out.println("AQUIII"+ id);
        return id;

    }

}
