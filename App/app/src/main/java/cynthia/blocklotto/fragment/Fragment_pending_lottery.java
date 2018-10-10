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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cynthia.blocklotto.InfoLottery;
import cynthia.blocklotto.lottery.PendingLottery;
import cynthia.blocklotto.R;
import cynthia.blocklotto.adapter.Adapter_pending_lottery;

public class Fragment_pending_lottery extends Fragment {

    private RecyclerView recyclerViewLottery;
    private Adapter_pending_lottery adapterLottery;
    private List<PendingLottery> pendingLotteries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_pending_lottery, container, false) ;

        recyclerViewLottery = view.findViewById(R.id.container_PendingLottery);
        recyclerViewLottery.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterLottery = new Adapter_pending_lottery((getSorteos()));
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
        }else {
            sortDate();
            adapterLottery.notifyDataSetChanged();
            return true;
        }
    }

    public void sortPriceDesc(){
        pendingLotteries.clear();

        pendingLotteries.add( new PendingLottery( 1, R.drawable.flor, "Extracoin", "14/7/2060", 1, 11,"Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 2,R.drawable.flor, "CryptoLucky", "25/04/2050", 2, 8, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery(3, R.drawable.flor, "FatBitoin","9/8/2025",8, 7, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery(4, R.drawable.flor, "El Gordo Digital", "18/7/2050", 5, 3,"Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery(5, R.drawable.flor, "El Gordo Digital", "22/05/2020", 1, 5, "Este sorteo se celebra los jueves.", 0.0002));

    }
    public void sortPriceAsc(){
        pendingLotteries.clear();

        pendingLotteries.add( new PendingLottery(6, R.drawable.flor, "El Gordo Digital", "18/7/2050", 5, 3,"Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 7,R.drawable.flor, "El Gordo Digital", "22/05/2020", 1, 5, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 8,R.drawable.flor, "FatBitoin", "9/8/2025",8, 7, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery(9, R.drawable.flor, "CryptoLucky", "25/04/2050", 2, 8, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 10,R.drawable.flor, "Extracoin", "14/7/2060", 1, 11,"Este sorteo se celebra los jueves.", 0.0002));


    }

    public void sortDate(){
        pendingLotteries.clear();

        pendingLotteries.add( new PendingLottery(1, R.drawable.flor, "El Gordo Digital","22/05/2020", 1, 5, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 2,R.drawable.flor, "FatBitoin", "9/8/2025",8, 7, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 3,R.drawable.flor, "CryptoLucky", "25/04/2050", 2, 8, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 4,R.drawable.flor, "Extracoin","14/7/2060", 1, 11,"Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 5,R.drawable.flor, "El Gordo Digital", "18/7/2050", 5, 3,"Este sorteo se celebra los jueves.", 0.0002));


    }
    public List<PendingLottery> getSorteos(){
        pendingLotteries = new ArrayList<>();

        /*String strThatDay = "22/05/2018";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        Date d = null;
        try {
            d = formatter.parse(strThatDay);//catch exception
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        pendingLotteries.add( new PendingLottery( 1,R.drawable.flor, "El Gordo Digital", "22/05/2020", 1, 5, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 2,R.drawable.flor, "FatBitoin", "28/05/2025",8, 7, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 3,R.drawable.flor, "CryptoLucky", "10/05/2060", 2, 8, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery(4, R.drawable.flor, "Extracoin", "15/07/2019", 1, 11,"Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 5,R.drawable.flor, "El Gordo Digital", "05/04/2030", 5, 3,"Este sorteo se celebra los jueves.", 0.0002));
        return pendingLotteries;
    }

    private String datetoString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formating according to my need
        String dateObj = formatter.format(date);
        return dateObj;
    }


}
