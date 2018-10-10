package cynthia.blocklotto.lottery;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class PendingLottery {

    private int id;
    private int photo;
    private String name;
    private String date;
    private int amountTicket;
    private float accumulated;
    private String information;
    private double price;
    private String priceTotal;

    public PendingLottery() { }

    public PendingLottery(int id, int photo, String name, String date, int amountTicket, float accumulated, String information, double price) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.date = date;
        this.amountTicket = amountTicket;
        this.accumulated = accumulated;
        this.information = information;
        this.price = price;
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
