package cynthia.blocklotto.notification;

import android.annotation.SuppressLint;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cynthia.blocklotto.R;

public class Notification {

    private int id;
    private String codWallet;
    private String date;
    private String winningNumber;
    private String name;
    private double award;
    private int readed;

    private int icon;

    private final double satoshi = 0.00000001;

    public Notification(int id, String codWallet, String date, String winningNumber, String name, double award, int readed) {
        this.id = id;
        this.codWallet = codWallet;
        this.date = date;
        this.winningNumber = winningNumber;
        this.name = name;
        this.award = award;
        this.readed = readed;
    }

    public int getId(){ return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodWallet() {
        return codWallet;
    }

    public void setCodWallet(String codWallet) {
        this.codWallet = codWallet;
    }

    public String getDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaDate = null;

        try {
            fechaDate = formato.parse(date);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
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

    public String getWinningNumber() {
        return winningNumber;
    }

    public void setWinningNumber(String winningNumber) {
        this.winningNumber = winningNumber;
    }

    public String getName() {
        return "Ha ganado el premio " + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAward() {
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.0#####");
        return Double.parseDouble(num.format((award*satoshi)));
    }

    public void setAward(double award) {
        this.award = award;
    }

    public String getAwardBadge(){
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.0####");
        String result = num.format(award*satoshi);
        return result + " BTC";
    }

    public int getReaded() {
        return readed;
    }

    public void setReaded(int readed) {
        this.readed = readed;
    }

    public void setIcon() {
        if(readed == 1){
            icon= R.drawable.ic_notification_open;
        }else{
            icon = R.drawable.ic_notification_close;
        }
    }

    public int getIcon() {
        setIcon();
        return icon;
    }
}
