package cynthia.blocklotto.lottery;

import java.text.DecimalFormat;
import java.util.Locale;

import cynthia.blocklotto.R;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class NextLottery {

    private int id;
    private int photo;
    private String name;
    private double price;
    private String date;
    private float award;
    private String information;

    public NextLottery() { }

    public NextLottery(int id, String name, String date, double price, float award, String information) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.price = price;
        this.award = award;
        this.information = information;
        setPhoto();

    }

    public int getId() {
        return id;
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

    public double getPrice(){ return price; }

    public String getInformation() {
        return information;
    }

    public String getPriceBadge(){
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.0####");
        return num.format(price) + " BTC";
    }

    public String getAward(){
        return award + " BTC";
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
