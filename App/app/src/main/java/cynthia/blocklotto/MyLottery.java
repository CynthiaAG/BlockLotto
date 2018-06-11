package cynthia.blocklotto;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class MyLottery {
    private int foto;
    private String name;
    private String date;
    private String button;


    public MyLottery() {

    }

    public MyLottery(int foto, String name, String date, String button) {
        this.foto = foto;
        this.name = name;
        this.date = date;
        this.button=button;
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
