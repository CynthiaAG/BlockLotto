package cynthia.blocklotto.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cynthia.blocklotto.InfoLottery;
import cynthia.blocklotto.action.BuyLottery;
import cynthia.blocklotto.lottery.ArchivedLottery;
import cynthia.blocklotto.R;
import cynthia.blocklotto.adapter.Adapter_archived_lottery;

/**
 * Created by Cynthia on 11/06/2018.
 */

public class Fragment_archived_lottery  extends Fragment{

    private RecyclerView recyclerViewLottery;
    private Adapter_archived_lottery adapterLottery;
    private List<ArchivedLottery> archivedLotteries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_archived_lottery, container, false) ;
        recyclerViewLottery = view.findViewById(R.id.container_archivedLottery);
        directionControl();
        adapterLottery = new Adapter_archived_lottery((getSorteos()));
        recyclerViewLottery.setAdapter(adapterLottery);
        elementClicked(null);
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

    private void elementClicked(final String typeLottery){
        final GestureDetector mGestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        recyclerViewLottery.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                try {
                    View child = recyclerViewLottery.findChildViewUnder(e.getX(), e.getY());

                    if (child != null && mGestureDetector.onTouchEvent(e)) {
                        int position = recyclerViewLottery.getChildAdapterPosition(child);
                        Context c = getContext();
                        Intent intent = new Intent(c , InfoLottery.class);
                        sendElements(intent, position, "Archived");
                        c.startActivity(intent);
                        return true;
                    }
                }catch (Exception ep){
                    ep.printStackTrace();
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    private void sendElements(Intent intent, int position, String type){
        intent.putExtra("type", type);
        intent.putExtra("id", adapterLottery.getListArchivedLottery().get(position).getId());
        intent.putExtra("date", adapterLottery.getListArchivedLottery().get(position).getDate());
        intent.putExtra("name", adapterLottery.getListArchivedLottery().get(position).getName());
        intent.putExtra("info", adapterLottery.getListArchivedLottery().get(position).getInformation());
        intent.putExtra("bot", adapterLottery.getListArchivedLottery().get(position).getAccumulated());
        intent.putExtra("price", adapterLottery.getListArchivedLottery().get(position).getPriceBadge());
        intent.putExtra("amount", adapterLottery.getListArchivedLottery().get(position).getNumTicketArchived());
        intent.putExtra("priceFinal", adapterLottery.getListArchivedLottery().get(position).getPriceTotal());
    }

    private void directionControl(){
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerViewLottery.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        }
        else{
            recyclerViewLottery.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        }
    }


    public void sortPriceDesc(){
        archivedLotteries.clear();

        archivedLotteries.add( new ArchivedLottery(1, R.drawable.start2, "El Gordo Digital", "22/05/2020", 8, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(2,  R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(3, R.drawable.flor, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery( 4, R.drawable.gato, "Extracoin", "14/7/2060", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(5,  R.drawable.start2, "El Gordo Digital", "22/05/2020", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(6,  R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(7,  R.drawable.flor, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(8,  R.drawable.gato, "Extracoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(9,  R.drawable.start2, "El Gordo Digital", "22/05/2020", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(10,  R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(11, R.drawable.flor, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(12,  R.drawable.gato, "Extracoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));

    }
    public void sortPriceAsc(){

        archivedLotteries.clear();

        archivedLotteries.add( new ArchivedLottery(11, R.drawable.flor, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(12,  R.drawable.gato, "Extracoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(1, R.drawable.start2, "El Gordo Digital", "22/05/2020", 8, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(2,  R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(3, R.drawable.flor, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery( 4, R.drawable.gato, "Extracoin", "14/7/2060", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(5,  R.drawable.start2, "El Gordo Digital", "22/05/2020", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(6,  R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(7,  R.drawable.flor, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(8,  R.drawable.gato, "Extracoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(9,  R.drawable.start2, "El Gordo Digital", "22/05/2020", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(10,  R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));

    }

    public void sortDate(){
        archivedLotteries.clear();

        archivedLotteries.add( new ArchivedLottery(5,  R.drawable.start2, "El Gordo Digital", "22/05/2020", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(1, R.drawable.start2, "El Gordo Digital", "22/05/2020", 8, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(2,  R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(3, R.drawable.flor, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery( 4, R.drawable.gato, "Extracoin", "14/7/2065", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(6,  R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(7,  R.drawable.flor, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(8,  R.drawable.gato, "Extracoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(9,  R.drawable.start2, "El Gordo Digital", "22/05/2090", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(10,  R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(11, R.drawable.flor, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(12,  R.drawable.gato, "Extracoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));

    }
    public List<ArchivedLottery> getSorteos(){
        archivedLotteries = new ArrayList<>();

        archivedLotteries.add( new ArchivedLottery(1, R.drawable.start2, "El Gordo Digital", "22/05/2020", 8, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(2,  R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(3, R.drawable.flor, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery( 4, R.drawable.gato, "Extracoin", "14/7/2060", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(5,  R.drawable.start2, "El Gordo Digital", "22/05/2020", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(6,  R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(7,  R.drawable.flor, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(8,  R.drawable.gato, "Extracoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(9,  R.drawable.start2, "El Gordo Digital", "22/05/2020", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(10,  R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(11, R.drawable.flor, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(12,  R.drawable.gato, "Extracoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        return archivedLotteries;
    }



}
