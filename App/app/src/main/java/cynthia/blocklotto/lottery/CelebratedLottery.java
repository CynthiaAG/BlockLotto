package cynthia.blocklotto.lottery;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class CelebratedLottery {

    private int photo;
    private String name;
    private String date;
    private String user;
    private int amount;

    public CelebratedLottery() { }

    public CelebratedLottery(int photo, String name, String date, String user, int amount) {
        this.photo = photo;
        this.name = name;
        this.date = date;
        this.user = user;
        this.amount = amount;
    }

    public int getAmount(){ return amount; }

    public String getUser(){ return user; }

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
