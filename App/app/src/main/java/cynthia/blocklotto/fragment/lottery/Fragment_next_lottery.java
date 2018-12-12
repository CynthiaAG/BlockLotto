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

import cynthia.blocklotto.ResultFromJson;
import cynthia.blocklotto.conection.Conection;
import cynthia.blocklotto.conection.ConectionResponse;
import cynthia.blocklotto.lottery.NextLottery;
import cynthia.blocklotto.R;
import cynthia.blocklotto.adapter.lottery.Adapter_next_lottery;

public class Fragment_next_lottery extends Fragment implements ConectionResponse {

    private RecyclerView recyclerViewLottery;
    private Adapter_next_lottery adapterLottery;
    private List<NextLottery> nextLotteries;
    private SwipeRefreshLayout swipeRefresh;
    private ProgressBar progressBar;
    private TextView notfound;
    private View view;
    private boolean priceAsc;
    private boolean priceDesc;
    private boolean sortDate;
    private boolean creation;

    private void initialize(){
        notfound = view.findViewById(R.id.textNotFound);
        progressBar = view.findViewById(R.id.progress_nextLottery);
        progressBar.setVisibility(View.VISIBLE);

        swipeRefresh = view.findViewById(R.id.swipeNextLottery);
        swipeRefresh.setColorSchemeResources(R.color.colorSecondary, R.color.colorButton, R.color.colorAccent);
        recyclerViewLottery = view.findViewById(R.id.container_nextLottery);
        recyclerViewLottery.setLayoutManager(new LinearLayoutManager(getActivity()));
        nextLotteries = new ArrayList<>();
        getLotteries();
        adapterLottery = new Adapter_next_lottery(nextLotteries);
        priceAsc=false;
        priceDesc=false;
        sortDate=true;
        creation=true;
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
                getLotteries();
                swipeRefresh.setRefreshing(false);
            }
        });
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

    private void getLotteries(){
        Conection con = new Conection();
        con.conectionResponse=this;
        con.getNextLotteries(getContext());

    }

    @Override
    public void onResume() {
        super.onResume();
        getLotteries();
    }

    @Override
    public void processFinish(String output) {
        ResultFromJson resultFromJson = new ResultFromJson();
        nextLotteries.clear();
        adapterLottery.notifyDataSetChanged();
        if(output == null){
            progressBar.setVisibility(View.INVISIBLE);
            notfound.setText("No se pueden mostrar los sorteos. Revise su conexión a internet.");
            notfound.setVisibility(View.VISIBLE);
        }else {
            ArrayList<NextLottery> aux = resultFromJson.getNextLotteryResult(output);
            progressBar.setVisibility(View.INVISIBLE);
            if ((!aux.isEmpty()) && (aux != null)) {
                if (sortDate) {
                    nextLotteries.addAll(aux);
                } else if (priceAsc) {
                    sortPriceAsc(aux);
                } else if (priceDesc) {
                    sortPriceDesc(aux);
                }
                adapterLottery.notifyDataSetChanged();
                notfound.setVisibility(View.INVISIBLE);
            } else {
                notfound.setText("No hay sorteos disponibles.");
                notfound.setVisibility(View.VISIBLE);
            }
        }


    }

    private void sortPriceAsc(ArrayList<NextLottery> aux){
        Collections.sort(aux, new Comparator<NextLottery>() {
            @Override
            public int compare(NextLottery nextLottery, NextLottery t1) {
                return Double.compare(nextLottery.getPrice(), t1.getPrice());
            }
        });
        nextLotteries.addAll(aux);
    }

    private void sortPriceDesc(ArrayList<NextLottery> aux){
        Collections.sort(aux, new Comparator<NextLottery>() {
            @Override
            public int compare(NextLottery nextLottery, NextLottery t1) {
                return Double.compare(t1.getPrice(), nextLottery.getPrice());
            }
        });
        nextLotteries.addAll(aux);
    }

}