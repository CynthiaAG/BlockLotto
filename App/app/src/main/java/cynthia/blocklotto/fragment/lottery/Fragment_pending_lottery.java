package cynthia.blocklotto.fragment.lottery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cynthia.blocklotto.conection.ResultFromJson;
import cynthia.blocklotto.conection.Conection;
import cynthia.blocklotto.conection.ConectionResponse;
import cynthia.blocklotto.lottery.PendingLottery;
import cynthia.blocklotto.R;
import cynthia.blocklotto.adapter.lottery.Adapter_pending_lottery;

public class Fragment_pending_lottery extends Fragment implements ConectionResponse {

    private RecyclerView recyclerViewLottery;
    private Adapter_pending_lottery adapterLottery;
    private List<PendingLottery> pendingLotteries;
    private SwipeRefreshLayout swipeRefresh;
    private ProgressBar progressBar;
    private TextView lotteryEmpty;
    private View view;
    private boolean priceAsc;
    private boolean priceDesc;
    private boolean sortDate;
    private boolean creation;

    private void initialize(){
        progressBar = view.findViewById(R.id.progress_pendingLottery);
        progressBar.setVisibility(View.VISIBLE);
        lotteryEmpty = view.findViewById(R.id.pendingEmpty);

        recyclerViewLottery = view.findViewById(R.id.container_PendingLottery);
        recyclerViewLottery.setLayoutManager(new LinearLayoutManager(getActivity()));
        pendingLotteries = new ArrayList<>();
        adapterLottery = new Adapter_pending_lottery(pendingLotteries);
        getLotteries();

        swipeRefresh = view.findViewById(R.id.swipePendingLottery);
        swipeRefresh.setColorSchemeResources(R.color.colorSecondary, R.color.colorButton, R.color.colorAccent);
        priceAsc = false;
        priceDesc = false;
        sortDate = true;
        creation = true;
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(creation && isVisibleToUser){
            priceAsc = false;
            priceDesc = false;
            sortDate = true;
            getLotteries();
        }
    }

    private void controlSwipe(){
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getLotteries();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void getLotteries(){
        Conection con = new Conection();
        con.conectionResponse=this;
        con.getPendingLotteries(getContext());
    }

    @Override
    public void processFinish(String output) {
        ResultFromJson resultFromJson = new ResultFromJson();
        pendingLotteries.clear();
        adapterLottery.notifyDataSetChanged();
        if(output == null){
            progressBar.setVisibility(View.INVISIBLE);
            lotteryEmpty.setText("No se pueden mostrar los sorteos pendientes. Revise su conexión a internet.");
            lotteryEmpty.setVisibility(View.VISIBLE);
        }else {
            ArrayList<PendingLottery> aux = resultFromJson.getPendingLotteryResult(output);
            progressBar.setVisibility(View.INVISIBLE);
            if ((!aux.isEmpty()) && (aux != null)) {
                if (sortDate) {
                    pendingLotteries.addAll(aux);
                } else if (priceAsc) {
                    sortPriceAsc(aux);
                } else if (priceDesc) {
                    sortPriceDesc(aux);
                }
                adapterLottery.notifyDataSetChanged();
                lotteryEmpty.setVisibility(View.INVISIBLE);
            } else {
                lotteryEmpty.setText("No hay sorteos pendientes.");
                lotteryEmpty.setVisibility(View.VISIBLE);
            }
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(!item.isChecked());
        int id = item.getItemId();

        if (id == R.id.sort_price_asc) {
            progressBar.setVisibility(View.VISIBLE);
            priceAsc=true;
            priceDesc=false;
            sortDate=false;
            getLotteries();
            return true;
        }else if(id== R.id.sort_price_desc){
            progressBar.setVisibility(View.VISIBLE);
            priceAsc=false;
            priceDesc=true;
            sortDate=false;
            getLotteries();
            return true;
        }else if(id == R.id.sort_date){
            progressBar.setVisibility(View.VISIBLE);
            priceAsc=false;
            priceDesc=false;
            sortDate=true;
            getLotteries();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void sortPriceAsc(ArrayList<PendingLottery> aux){
        Collections.sort(aux, new Comparator<PendingLottery>() {
            @Override
            public int compare(PendingLottery pendingLottery, PendingLottery t1) {
                return Double.compare(pendingLottery.getPrice(), t1.getPrice());
            }
        });
        pendingLotteries.addAll(aux);
    }

    private void sortPriceDesc(ArrayList<PendingLottery> aux){
        Collections.sort(aux, new Comparator<PendingLottery>() {
            @Override
            public int compare(PendingLottery pendingLottery, PendingLottery t1) {
                return Double.compare(t1.getPrice(), pendingLottery.getPrice());
            }
        });
        pendingLotteries.addAll(aux);
    }

}
