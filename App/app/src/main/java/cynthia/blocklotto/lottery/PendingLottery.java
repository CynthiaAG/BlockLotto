package cynthia.blocklotto.lottery;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class PendingLottery {

    private int photo;
    private String name;
    private String date;
    private String button;

    public PendingLottery() { }

    public PendingLottery(int photo, String name, String date, String button) {
        this.photo = photo;
        this.name = name;
        this.date = date;
        this.button=button;
    }

    public String getButton(){ return button; }

    public void setButton(String button){
        this.button=button;
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
