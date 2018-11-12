package cynthia.blocklotto.lottery;

import java.text.DecimalFormat;
import java.util.Locale;

import cynthia.blocklotto.R;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class PendingLottery {

    private int id;
    private int photo;
    private String name;
    private String date;
    private int amountTicket;
    private float award;
    private String information;
    private double price;
    private String priceTotal;

    public PendingLottery() { }

    public PendingLottery(int id, String name, String date, int amountTicket, float award, String information, double price) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.amountTicket = amountTicket;
        this.award = award;
        this.information = information;
        this.price = price;
        setPhoto();
    }

    public void setPriceTotal(String priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getPriceTotal() {
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.0####");
        priceTotal= num.format(price*amountTicket) + " BTC";
        return priceTotal;
    }

    public int getId() {
        return id;
    }

    public String getInformation() {
        return information;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceBadge(){
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.0####");
        return num.format(price) + " BTC";
    }

    public int getAmountTicket(){
        return amountTicket;
    }

    public String getAward(){
        return award + " BTC";
    }

    private void setPhoto(){
        switch (name){
            case "CryptoLucky":
                photo = R.drawable.cryptolucky;
                break;

            case "ExtraCoin":
                photo = R.drawable.extracoin;
                break;

            case "Lotto Boom":
                photo = R.drawable.lottoboom;
                break;

            case "RaffleCoin":
                photo = R.drawable.rafflecoin;
                break;
        }
    }

    public int getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}
