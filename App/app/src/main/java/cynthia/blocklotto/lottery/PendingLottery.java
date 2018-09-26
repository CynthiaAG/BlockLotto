package cynthia.blocklotto.lottery;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class PendingLottery {

    private int photo;
    private String name;
    private String date;
    private int amountTicket;
    private String accumulated;

    public PendingLottery() { }

    public PendingLottery(int photo, String name, String date, int amountTicket, String accumulated) {
        this.photo = photo;
        this.name = name;
        this.date = date;
        this.amountTicket=amountTicket;
        this.accumulated=accumulated;
    }

    public void setAmountTicket(int amountTicket){ this.amountTicket=amountTicket; }

    public int getAmountTicket(){
        return amountTicket;
    }
    public String getAccumulated(){
        return accumulated;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
