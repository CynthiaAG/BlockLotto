package cynthia.blocklotto.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cynthia.blocklotto.InfoLottery;
import cynthia.blocklotto.lottery.NextLottery;
import cynthia.blocklotto.R;
import cynthia.blocklotto.adapter.Adapter_next_lottery;

public class Fragment_next_lottery extends Fragment {

    private RecyclerView recyclerViewLottery;
    private Adapter_next_lottery adapterLottery;
    private  List<NextLottery> nextLotteries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_next_lottery, container, false) ;

       recyclerViewLottery = view.findViewById(R.id.container_nextLottery);
       recyclerViewLottery.setLayoutManager(new LinearLayoutManager(getActivity()));
       adapterLottery = new Adapter_next_lottery((getSorteos()));
       recyclerViewLottery.setAdapter(adapterLottery);
       setHasOptionsMenu(true);
       return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.sort_price_asc) {
            sortPriceAsc();
            adapterLottery.notifyDataSetChanged();
            return true;
        }else if(id== R.id.sort_price_desc){
            sortPriceDesc();
            adapterLottery.notifyDataSetChanged();
            return true;
        }else if(id == R.id.sort_date){
            sortDate();
            adapterLottery.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sortPriceDesc(){
        nextLotteries.clear();
        nextLotteries.add( new NextLottery(1,  R.drawable.gato, "Extracoin", "14/7/2060", 0.01,5, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(2, R.drawable.start2, "El Gordo Digital", "22/05/2020", 0.009,16, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(3, R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 0.0012, 9, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(4, R.drawable.flor, "CryptoLucky", "05/05/2080", 0.00051, 4, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));

    }
    public void sortPriceAsc(){
        nextLotteries.clear();

        nextLotteries.add( new NextLottery(5, R.drawable.flor, "CryptoLucky", "05/05/2080", 0.00051, 4, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(6, R.drawable.gato, "Extracoin", "14/7/2060", 0.008,5, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(7, R.drawable.start2, "El Gordo Digital", "22/05/2020", 0.009,16, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(8, R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 0.0012, 9, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
    }

    public void sortDate(){
        nextLotteries.clear();

        nextLotteries.add( new NextLottery(1, R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 0.0012, 9, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(2, R.drawable.start2, "El Gordo Digital", "22/05/2020", 0.009,16, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(3, R.drawable.gato, "Extracoin", "14/7/2060", 0.008,5, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery( 4,R.drawable.flor, "CryptoLucky", "05/05/2080", 0.00051, 4, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
    }

    public List<NextLottery> getSorteos(){
        nextLotteries = new ArrayList<>();

        nextLotteries.add( new NextLottery(5, R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 0.0012, 9, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery( 6,R.drawable.start2, "El Gordo Digital", "22/05/2020", 0.009,16, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(7, R.drawable.gato, "Extracoin", "14/7/2060", 0.008,5, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(8, R.drawable.flor, "CryptoLucky", "05/05/2080", 0.00051, 4, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        return nextLotteries;
    }

    private String datetoString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formating according to my need
        String dateObj = formatter.format(date);
        return dateObj;
    }

}
