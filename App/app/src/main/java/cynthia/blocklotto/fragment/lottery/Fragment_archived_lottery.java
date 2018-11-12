package cynthia.blocklotto.fragment.lottery;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cynthia.blocklotto.action.lottery.InfoLottery;
import cynthia.blocklotto.lottery.ArchivedLottery;
import cynthia.blocklotto.R;
import cynthia.blocklotto.adapter.lottery.Adapter_archived_lottery;

/**
 * Created by Cynthia on 11/06/2018.
 */

public class Fragment_archived_lottery  extends Fragment{

    private RecyclerView recyclerViewLottery;
    private Adapter_archived_lottery adapterLottery;
    private List<ArchivedLottery> archivedLotteries;
    private SwipeRefreshLayout swipeRefresh;
    private boolean priceDesc;
    private boolean priceAsc;
    private boolean dateRecent;
    private View view;

    private Bundle savedInstanceState;

    private void initialize(){
        recyclerViewLottery = view.findViewById(R.id.container_archivedLottery);
        swipeRefresh = view.findViewById(R.id.swipeArchivedLottery);
        swipeRefresh.setColorSchemeResources(R.color.colorSecondary, R.color.colorButton, R.color.colorAccent);
        adapterLottery = new Adapter_archived_lottery((getLotteries()));
        dateRecent=false;
        priceAsc=false;
        priceDesc=false;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_archived_lottery, container, false) ;
        this.savedInstanceState=savedInstanceState;
        initialize();
        controlSwipe();
        directionControl();
        recyclerViewLottery.setAdapter(adapterLottery);
        elementClicked("Archived");
        setHasOptionsMenu(true);
        return view;
    }

    private void controlSwipe(){
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(priceAsc){
                    sortPriceAsc();
                }else if(priceDesc){
                    sortPriceDesc();
                }else{
                    sortDate();
                }
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && (savedInstanceState!=null)) {
            // Refresh your fragment here
            directionControl();
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        directionControl();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.sort_price_asc) {
            priceAsc=true;
            priceDesc=false;
            dateRecent=false;
            sortPriceAsc();
            return true;
        }else if(id== R.id.sort_price_desc){
            priceAsc=false;
            priceDesc=true;
            dateRecent=false;
            sortPriceDesc();
            return true;
        }else if(id == R.id.sort_date){
            priceAsc=false;
            priceDesc=false;
            dateRecent=true;
            sortDate();
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
                        sendElements(intent, position, typeLottery);
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
        intent.putExtra("award", adapterLottery.getListArchivedLottery().get(position).getAward());
        intent.putExtra("priceBadge", adapterLottery.getListArchivedLottery().get(position).getPriceBadge());
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

    private void sortPriceDesc(){
        archivedLotteries.clear();

        archivedLotteries.add( new ArchivedLottery(1, "RaffleCoin", "22/05/2020", 8, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(2,  "Lotto Boom", "9/8/2025", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(3, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery( 4,"ExtraCoin", "14/7/2060", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(5, "RaffleCoin", "22/05/2020", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(6, "RaffleCoin", "9/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(7, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(8, "ExtraCoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(9,  "CryptoLucky", "22/05/2020", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(10,  "Lotto Boom", "9/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(11, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(12,  "ExtraCoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        adapterLottery.notifyDataSetChanged();
    }

    private void sortPriceAsc(){

        archivedLotteries.clear();

        archivedLotteries.add( new ArchivedLottery(5, "RaffleCoin", "22/05/2020", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(6, "RaffleCoin", "9/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(7, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(8, "ExtraCoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(9,  "CryptoLucky", "22/05/2020", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(1, "RaffleCoin", "22/05/2020", 8, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(2,  "Lotto Boom", "9/8/2025", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(3, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery( 4,"ExtraCoin", "14/7/2060", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(10,  "Lotto Boom", "9/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(11, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(12,  "ExtraCoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        adapterLottery.notifyDataSetChanged();
    }

    private void sortDate(){
        archivedLotteries.clear();

        archivedLotteries.add( new ArchivedLottery(5, "RaffleCoin", "22/05/2020", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(9,  "CryptoLucky", "22/05/2020", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(1, "RaffleCoin", "22/05/2020", 8, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(2,  "Lotto Boom", "9/8/2025", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(10,  "Lotto Boom", "9/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(6, "RaffleCoin", "9/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(8, "ExtraCoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery( 4,"ExtraCoin", "14/7/2060", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(12,  "ExtraCoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(7, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(3, "ExtraCoin", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(11, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        adapterLottery.notifyDataSetChanged();
    }

    private List<ArchivedLottery> getLotteries(){
        archivedLotteries = new ArrayList<>();

        archivedLotteries.add( new ArchivedLottery(5, "RaffleCoin", "22/05/2020", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(9,  "CryptoLucky", "22/05/2020", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(1, "RaffleCoin", "22/05/2020", 8, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(2,  "Lotto Boom", "9/8/2025", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(10,  "Lotto Boom", "19/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(6, "RaffleCoin", "9/8/2025", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(8, "ExtraCoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery( 4,"ExtraCoin", "14/7/2060", 5, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(12,  "ExtraCoin", "14/7/2060", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(7, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(3, "ExtraCoin", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));
        archivedLotteries.add( new ArchivedLottery(11, "CryptoLucky", "05/05/2080", 1, 8, "Este sorteo se celebra cada miércoles.", 0.00005));

        return archivedLotteries;
    }

    private String datetoString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formating according to my need
        String dateObj = formatter.format(date);
        return dateObj;
    }


}
