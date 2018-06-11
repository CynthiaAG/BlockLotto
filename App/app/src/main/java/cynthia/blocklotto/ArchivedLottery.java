package cynthia.blocklotto;

/**
 * Created by Cynthia on 31/05/2018.
 */

public class ArchivedLottery {
    private int foto;
    private String name;
    private String date;
    private String button;


    public ArchivedLottery() {

    }

    public ArchivedLottery(int foto, String name, String date) {
        this.foto = foto;
        this.name = name;
        this.date = date;
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
