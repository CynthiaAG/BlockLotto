package cynthia.blocklotto.fragment.lottery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cynthia.blocklotto.MainActivity;
import cynthia.blocklotto.lottery.PendingLottery;
import cynthia.blocklotto.R;
import cynthia.blocklotto.adapter.lottery.Adapter_pending_lottery;

public class Fragment_pending_lottery extends Fragment {

    private RecyclerView recyclerViewLottery;
    private Adapter_pending_lottery adapterLottery;
    private List<PendingLottery> pendingLotteries;
    private SwipeRefreshLayout swipeRefresh;
    private View view;
    private boolean priceAsc;
    private boolean priceDesc;
    private boolean sortDate;

    private void initialize(){
        recyclerViewLottery = view.findViewById(R.id.container_PendingLottery);
        recyclerViewLottery.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterLottery = new Adapter_pending_lottery((getLotteries()));
        swipeRefresh = view.findViewById(R.id.swipePendingLottery);
        swipeRefresh.setColorSchemeResources(R.color.colorSecondary, R.color.colorButton, R.color.colorAccent);
        priceAsc = false;
        priceDesc = false;
        sortDate = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_pending_lottery, container, false) ;
        initialize();
        controlSwipe();
        recyclerViewLottery.setAdapter(adapterLottery);
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
                }else {
                    sortDate();
                }
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    /*
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refresh your fragment here
        }
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.sort_price_asc) {
            priceAsc=true;
            priceDesc=false;
            sortDate=false;
            sortPriceAsc();
            return true;
        }else if(id== R.id.sort_price_desc){
            priceAsc=false;
            priceDesc=true;
            sortDate=false;
            sortPriceDesc();
            return true;
        }else {
            sortDate();
            priceAsc=false;
            priceDesc=false;
            sortDate=true;
            return true;
        }
    }

    private void sortPriceDesc(){
        pendingLotteries.clear();

        pendingLotteries.add( new PendingLottery( 1, "ExtraCoin", "14/7/2060", 1, 11,"Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 2, "CryptoLucky", "25/04/2050", 2, 8, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery(3, "Lotto Boom","9/8/2025",8, 7, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery(4, "RaffleCoin", "18/7/2050", 5, 3,"Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery(5, "CryptoLucky", "22/05/2020", 1, 5, "Este sorteo se celebra los jueves.", 0.0002));

        adapterLottery.notifyDataSetChanged();
    }

    private void sortPriceAsc(){
        pendingLotteries.clear();

        pendingLotteries.add( new PendingLottery(6, "RaffleCoin", "18/7/2050", 5, 3,"Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 7, "CryptoLucky", "22/05/2020", 1, 5, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 8, "Lotto Boom", "9/8/2025",8, 7, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery(9, "CryptoLucky", "25/04/2050", 2, 8, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 10, "ExtraCoin", "14/7/2060", 1, 11,"Este sorteo se celebra los jueves.", 0.0002));

        adapterLottery.notifyDataSetChanged();
    }

    private void sortDate(){
        pendingLotteries.clear();

        pendingLotteries.add( new PendingLottery(1, "CryptoLucky","22/05/2020", 1, 5, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 2, "Lotto Boom", "9/8/2025",8, 7, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 3 ,"CryptoLucky", "25/04/2050", 2, 8, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 4, "ExtraCoin","14/7/2060", 1, 11,"Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 5, "RaffleCoin", "18/7/2050", 5, 3,"Este sorteo se celebra los jueves.", 0.0002));

        adapterLottery.notifyDataSetChanged();
    }

    private List<PendingLottery> getLotteries(){
        pendingLotteries = new ArrayList<>();

        /*String strThatDay = "22/05/2018";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        Date d = null;
        try {
            d = formatter.parse(strThatDay);//catch exception
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        pendingLotteries.add( new PendingLottery( 1, "CryptoLucky", "22/05/2020", 1, 5, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 2, "ExtraCoin", "28/05/2025",8, 7, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 3, "CryptoLucky", "10/05/2060", 2, 8, "Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery(4, "ExtraCoin", "15/07/2019", 1, 11,"Este sorteo se celebra los jueves.", 0.0002));
        pendingLotteries.add( new PendingLottery( 5, "Lotto Boom", "05/04/2030", 5, 3,"Este sorteo se celebra los jueves.", 0.0002));
        return pendingLotteries;
    }

    private String datetoString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formating according to my need
        String dateObj = formatter.format(date);
        return dateObj;
    }


}
