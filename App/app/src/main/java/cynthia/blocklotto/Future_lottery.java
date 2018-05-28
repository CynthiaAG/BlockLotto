package cynthia.blocklotto;

import android.widget.Button;

/**
 * Created by Cynthia on 04/05/2018.
 */

public class Future_lottery {
    private String nombre;
    private String fecha;
    private float price;
    private int buy;

    public Future_lottery(String nombre, String fecha, float price, int buy){
        this.nombre=nombre;
        this.buy=buy;
        this.fecha=fecha;
        this.price=price;
    }
    public String getNombre(){
        return nombre;
    }

    public String getFecha(){
        return fecha;
    }

    public float getPrice(){
        return price;
    }

    public int getBuy(){
        return buy;
    }
}
