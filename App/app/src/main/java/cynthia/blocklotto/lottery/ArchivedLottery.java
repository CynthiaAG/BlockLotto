package cynthia.blocklotto.lottery;

import android.annotation.SuppressLint;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cynthia.blocklotto.R;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class ArchivedLottery {

    private int id;
    private double price;
    private float award;
    private int amountTicket;
    private int totalParticipations;
    private int currentParticipations;
    private double priceTotal;
    private int blocked;
    private String name;
    private String date;
    private String information;
    private String state;
    private String winningNumber;

    private int photo;

    private final double satoshi = 0.00000001;

    public ArchivedLottery() {
    }

    public ArchivedLottery(int id, double price, float award, int amountTicket, int totalParticipations, int currentParticipations, double priceTotal, int blocked, String name, String date, String information, String state, String winningNumber) {
        this.id = id;
        this.price = price;
        this.award = award;
        this.amountTicket = amountTicket;
        this.totalParticipations = totalParticipations;
        this.currentParticipations = currentParticipations;
        this.priceTotal = priceTotal;
        this.blocked = blocked;
        this.name = name;
        this.date = date;
        this.information = information;
        this.state = state;
        this.winningNumber = winningNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.0#####");
        return Double.parseDouble(num.format(price*satoshi));
    }

    public String getPriceBadge() {
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.0#####");
        return num.format(price*satoshi) + " BTC";
    }


    public void setAward(float award) {
        this.award = award;
    }

    public String getAward() {
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.0#####");
        return num.format(award*satoshi) + " BTC";
    }

    public int getAmountTicket() {
        return amountTicket;
    }

    public void setAmountTicket(int amountTicket) {
        this.amountTicket = amountTicket;
    }

    public int getTotalParticipations() {
        return totalParticipations;
    }

    public void setTotalParticipations(int totalParticipations) {
        this.totalParticipations = totalParticipations;
    }

    public int getCurrentParticipations() {
        return currentParticipations;
    }

    public void setCurrentParticipations(int currentParticipations) {
        this.currentParticipations = currentParticipations;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getPriceTotal() {
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.0#####");
        return num.format(priceTotal*satoshi) + " BTC";
    }

    public int getBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setInformation(String information) {
        this.information = information;
    }

    public String getInformation() {
        return information;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWinningNumber() {
        return winningNumber;
    }

    public void setWinningNumber(String winningNumber) {
        this.winningNumber = winningNumber;
    }

    private void setPhoto() {
        switch (name){
            case "Crypto Lucky":
                photo = R.drawable.cryptolucky;
                break;

            case "Extracoin":
                photo = R.drawable.extracoin;
                break;

            case "LottoBoom":
                photo = R.drawable.lottoboom;
                break;

            case "RaffleCoin":
                photo = R.drawable.rafflecoin;
                break;
        }
    }

    public int getPhoto() {
        setPhoto();
        return photo;
    }
}
