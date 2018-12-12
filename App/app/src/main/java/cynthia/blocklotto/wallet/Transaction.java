package cynthia.blocklotto.wallet;

import android.annotation.SuppressLint;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Transaction {

    private String to;
    private String from;
    private double amount;
    private String date;
    private String action;
    private double historicalAmount;
    private String concept;

    private final double satoshi = 0.00000001;

    public Transaction(String to, String from, double amount, String date, String action, double historicalAmount, String concept) {
        this.to = to;
        this.from = from;
        this.amount = amount;
        this.date = date;
        this.action = action;
        this.historicalAmount = historicalAmount;
        this.concept = concept;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAmount() {
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.0####");
        return num.format(amount*satoshi) + " BTC";
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public String getDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaDate = null;

        try {
            fechaDate = formato.parse(date);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dateObj = format.format(fechaDate);
            return dateObj;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setHistoricalAmount(double historicalAmount) {
        this.historicalAmount = historicalAmount;
    }

    public String getHistoricalAmount(){
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.0####");
        return num.format(historicalAmount*satoshi) + " BTC";
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }
    /*
    public String getAmountType(){
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.0####");
        String result = num.format(amount);

        if(type.equals("income")){
            return "+ " + result+ " BTC";
        }else{
            return "- " + result+ " BTC";
        }
    }*/
}
