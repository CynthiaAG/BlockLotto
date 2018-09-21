package cynthia.blocklotto.lottery;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class ArchivedLottery {

    private int photo;
    private String name;
    private String date;

    public ArchivedLottery() { }

    public ArchivedLottery(int photo, String name, String date) {
        this.photo = photo;
        this.name = name;
        this.date = date;
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
