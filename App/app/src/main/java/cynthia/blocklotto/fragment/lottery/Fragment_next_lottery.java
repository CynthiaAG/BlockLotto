package cynthia.blocklotto.fragment.lottery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import cynthia.blocklotto.lottery.NextLottery;
import cynthia.blocklotto.R;
import cynthia.blocklotto.adapter.lottery.Adapter_next_lottery;

public class Fragment_next_lottery extends Fragment {

    private RecyclerView recyclerViewLottery;
    private Adapter_next_lottery adapterLottery;
    private List<NextLottery> nextLotteries;
    private SwipeRefreshLayout swipeRefresh;
    private View view;
    private boolean priceAsc;
    private boolean priceDesc;
    private boolean sortDate;

    private void initialize(){
        swipeRefresh = view.findViewById(R.id.swipeNextLottery);
        swipeRefresh.setColorSchemeResources(R.color.colorSecondary, R.color.colorButton, R.color.colorAccent);
        recyclerViewLottery = view.findViewById(R.id.container_nextLottery);
        recyclerViewLottery.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterLottery = new Adapter_next_lottery((getLotteries()));
        priceAsc=false;
        priceDesc=false;
        sortDate=false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_next_lottery, container, false) ;
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
        if (isResumed() && isVisibleToUser) {
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
        }else if(id == R.id.sort_date){
            priceAsc=false;
            priceDesc=false;
            sortDate=true;
            sortDate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sortPriceDesc(){
        nextLotteries.clear();

        nextLotteries.add( new NextLottery(1,"ExtraCoin", "14/07/2060", 0.01,5, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(2,"Lotto Boom", "22/05/2020", 0.009,16, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(3, "RaffleCoin", "09/08/2025", 0.0012, 9, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(4,"CryptoLucky", "05/05/2080", 0.00051, 4, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));

        adapterLottery.notifyDataSetChanged();
    }

    private void sortPriceAsc(){
        nextLotteries.clear();

        nextLotteries.add( new NextLottery(5, "CryptoLucky", "05/05/2080", 0.00051, 4, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(6,"ExtraCoin", "14/07/2060", 0.008,5, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(7, "Lotto Boom", "22/05/2020", 0.009,16, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(8, "RaffleCoin", "09/08/2025", 0.0012, 9, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));

        adapterLottery.notifyDataSetChanged();
    }

    private void sortDate(){
        nextLotteries.clear();

        nextLotteries.add( new NextLottery(1, "RaffleCoin", "09/08/2025", 0.0012, 9, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(2, "Lotto Boom", "22/05/2020", 0.009,16, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(3, "ExtraCoin", "14/07/2060", 0.008,5, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery( 4, "CryptoLucky", "05/05/2080", 0.00051, 4, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));

        adapterLottery.notifyDataSetChanged();
    }

    private List<NextLottery> getLotteries(){
        nextLotteries = new ArrayList<>();

        nextLotteries.add( new NextLottery(5, "RaffleCoin", "09/08/2025", 0.0012, 9, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery( 6, "Lotto Boom", "22/05/2020", 0.009,16, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(7,"ExtraCoin", "14/07/2060", 0.008,5, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));
        nextLotteries.add( new NextLottery(8, "CryptoLucky", "05/05/2080", 0.00051, 4, "Este sorteo se celebra dos veces por semana (jueves y sábados)."));

        return nextLotteries;
    }

    private String datetoString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formating according to my need
        String dateObj = formatter.format(date);
        return dateObj;
    }
}
