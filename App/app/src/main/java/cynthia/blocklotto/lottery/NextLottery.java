package cynthia.blocklotto.lottery;

import android.widget.Button;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class NextLottery {

    private int id;
    private int photo;
    private String name;
    private double price;
    private String date;
    private float accumulated;
    private String information;

    public NextLottery() { }

    public NextLottery(int id, int photo, String name, String date, double price, float accumulated, String information) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.date = date;
        this.price = price;
        this.accumulated = accumulated;
        this.information = information;

    }

    public int getId() {
        return id;
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

    public String getAccumulated(){
        return accumulated + " BTC";
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
