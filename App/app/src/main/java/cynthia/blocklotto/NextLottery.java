package cynthia.blocklotto;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class NextLottery {
    private int foto;
    private String name;
    private String price;
    private String date;
    private String button;


    public NextLottery() {

    }

    public NextLottery(int foto, String name, String date, String price, String button) {
        this.foto = foto;
        this.name = name;
        this.date = date;
        this.price=price;
        this.button=button;
    }

    public String getPrice(){ return price; }

    public void setPrice(String price){
        this.price=price;
    }

    public String getButton(){ return button; }

    public void setButton(String button){
        this.button=button;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
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
