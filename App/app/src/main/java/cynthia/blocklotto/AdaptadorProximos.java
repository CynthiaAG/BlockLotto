package cynthia.blocklotto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Cynthia on 04/05/2018.
 */

public class AdaptadorProximos extends BaseAdapter {

    private Context context;
    private ArrayList<Future_lottery> sorteos;

    public AdaptadorProximos(Context context, ArrayList<Future_lottery> sorteos) {
        this.context = context;
        this.sorteos = sorteos;
    }

    @Override
    public int getCount() {
        return sorteos.size();
    }

    @Override
    public Object getItem(int i) {
        return sorteos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
/*
        Future_lottery item = (Future_lottery) getItem(i);
        view= LayoutInflater.from(context).inflate(R.layout.item, null);
        TextView nombre = (TextView) view.findViewById(R.id.name);
        TextView fecha = view.findViewById(R.id.date);
        TextView priceOne = view.findViewById(R.id.price);
        Button buton = view.findViewById(R.id.buy);

        nombre.setText(item.getNombre());
        fecha.setText(item.getFecha());
        priceOne.setText(item.getPrice()+"");
*/
        return null;
    }
}
