package cynthia.blocklotto.conection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import cynthia.blocklotto.action.lottery.Purchase;
import cynthia.blocklotto.lottery.ArchivedLottery;
import cynthia.blocklotto.lottery.NextLottery;
import cynthia.blocklotto.lottery.PendingLottery;
import cynthia.blocklotto.notification.Notification;
import cynthia.blocklotto.wallet.Balance;
import cynthia.blocklotto.wallet.Transaction;

public class ResultFromJson {

    private final double satoshi = 0.00000001;

    public String getBalanceResult(String json_aux){
        Gson gson = new Gson();
        Balance balance = gson.fromJson (json_aux, Balance.class);
        if(balance.getTotal_balance()== null){
            return "0 BTC";
        }else{
            return balance.getTotal_balance() + " BTC";
        }
    }

    public String getBalanceChannelResult(String json_aux){
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject)jsonParser.parse(json_aux);
        if( jo != null){
            String s = jo.get("balance")+ "";
            double balance = Double.parseDouble(s);

            Locale.setDefault(Locale.US);
            DecimalFormat num = new DecimalFormat("#,##0.0#####");
            return num.format((balance*satoshi));
        } else{
            return null;
        }
    }

    public String sendBTCResult(String json_aux){
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject)jsonParser.parse(json_aux);
        if( jo != null){
            String s = jo.get("message")+ "";
            return s;
        } else{
            return null;
        }
    }

    public ArrayList<NextLottery> getNextLotteryResult(String json_aux){
        if(json_aux != null){
            Type listType = new TypeToken<ArrayList<NextLottery>>(){}.getType();
            return new Gson().fromJson(json_aux, listType);
        }else{
            return null;
        }
    }

    public ArrayList<PendingLottery> getPendingLotteryResult(String json_aux){
        if(json_aux != null) {
            Type listType = new TypeToken<ArrayList<PendingLottery>>() {
            }.getType();
            return new Gson().fromJson(json_aux, listType);
        }else{
            return null;
        }
    }

    public ArrayList<ArchivedLottery> getCelebratedLotteryResult(String json_aux){
        if(json_aux != null) {
            Type listType = new TypeToken<ArrayList<ArchivedLottery>>() {
            }.getType();
            return new Gson().fromJson(json_aux, listType);
        }else{
            return null;
        }
    }

    public String getBuyRaffleResult(String json_aux){
        Gson gson = new Gson();
        Purchase purchase = gson.fromJson (json_aux, Purchase.class);
        if((purchase == null) || (!purchase.getMessage().equals("Transaction completed"))){
            return "Error";
        }else{
            return "Transaction completed";
        }
    }

    public ArrayList<Transaction> getTransactionsResult(String json_aux){
        if(json_aux != null) {
            Type listType = new TypeToken<ArrayList<Transaction>>() {
            }.getType();
            return new Gson().fromJson(json_aux, listType);
        }else {
            return null;
        }
    }

    public ArrayList<Notification> getNotificationsResult(String json_aux){
        if(json_aux != null) {
            Type listType = new TypeToken<ArrayList<Notification>>() {
            }.getType();
            return new Gson().fromJson(json_aux, listType);
        }else{
            return null;
        }
    }

    public String setNotificationsReadResult(String json_aux) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(json_aux);
        if (jo != null) {
            String s = jo.get("message") + "";
            return s;
        } else {
            return null;
        }
    }
}
