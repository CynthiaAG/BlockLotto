package cynthia.blocklotto.fragment.lottery;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import cynthia.blocklotto.ResultFromJson;
import cynthia.blocklotto.action.lottery.InfoLottery;
import cynthia.blocklotto.conection.Conection;
import cynthia.blocklotto.conection.ConectionResponse;
import cynthia.blocklotto.lottery.ArchivedLottery;
import cynthia.blocklotto.R;
import cynthia.blocklotto.adapter.lottery.Adapter_archived_lottery;
import cynthia.blocklotto.lottery.PendingLottery;

/**
 * Created by Cynthia on 11/06/2018.
 */

public class Fragment_archived_lottery  extends Fragment implements ConectionResponse {

    private RecyclerView recyclerViewLottery;
    private Adapter_archived_lottery adapterLottery;
    private List<ArchivedLottery> archivedLotteries;
    private SwipeRefreshLayout swipeRefresh;
    private boolean priceDesc;
    private boolean priceAsc;
    private boolean sortDate;
    private boolean creation;
    private TextView notFound;
    private ProgressBar progressBar;
    private View view;

    private Bundle savedInstanceState;

    private void initialize(){
        recyclerViewLottery = view.findViewById(R.id.container_archivedLottery);
        swipeRefresh = view.findViewById(R.id.swipeArchivedLottery);
        swipeRefresh.setColorSchemeResources(R.color.colorSecondary, R.color.colorButton, R.color.colorAccent);
        archivedLotteries = new ArrayList<>();
        notFound = view.findViewById(R.id.archivedEmpty);
        progressBar = view.findViewById(R.id.progress_archivedLottery);
        progressBar.setVisibility(View.VISIBLE);

        getLotteries();
        adapterLottery = new Adapter_archived_lottery(archivedLotteries);
        sortDate=true;
        creation = true;
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

    private void getLotteries(){
        Conection con = new Conection();
        con.conectionResponse=this;
        con.getCelebratedLotteries(getContext());

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
        if (isVisibleToUser && (savedInstanceState!=null)) {
            directionControl();
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }else if(creation && isVisibleToUser){
            priceAsc = false;
            priceDesc = false;
            sortDate = true;
            getLotteries();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        directionControl();
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
        intent.putExtra("amount", adapterLottery.getListArchivedLottery().get(position).getAmountTicket());
        intent.putExtra("priceFinal", adapterLottery.getListArchivedLottery().get(position).getPriceTotal());

        intent.putExtra("totalParticipations", adapterLottery.getListArchivedLottery().get(position).getTotalParticipations());
        intent.putExtra("currentParticipations", adapterLottery.getListArchivedLottery().get(position).getCurrentParticipations());
    }

    private void directionControl(){
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerViewLottery.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        }
        else{
            recyclerViewLottery.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        }
    }

    @Override
    public void processFinish(String output) {
        ResultFromJson resultFromJson = new ResultFromJson();
        archivedLotteries.clear();
        adapterLottery.notifyDataSetChanged();
        if(output == null){
            progressBar.setVisibility(View.INVISIBLE);
            notFound.setText("No se pueden mostrar los sorteos celebrados. Revise su conexión a internet.");
            notFound.setVisibility(View.VISIBLE);
        }else {
            ArrayList<ArchivedLottery> aux = resultFromJson.getCelebratedLotteryResult(output);
            progressBar.setVisibility(View.INVISIBLE);
            if ((!aux.isEmpty()) && (aux != null)) {
                if (sortDate) {
                    archivedLotteries.addAll(aux);
                } else if (priceAsc) {
                    sortPriceAsc(aux);
                } else if (priceDesc) {
                    sortPriceDesc(aux);
                }
                adapterLottery.notifyDataSetChanged();
                notFound.setVisibility(View.INVISIBLE);
            } else {
                notFound.setText("No hay sorteos celebrados.");
                notFound.setVisibility(View.VISIBLE);
            }
        }
    }

    private void sortPriceAsc(ArrayList<ArchivedLottery> aux){
        Collections.sort(aux, new Comparator<ArchivedLottery>() {
            @Override
            public int compare(ArchivedLottery archivedLottery, ArchivedLottery t1) {
                return Double.compare(archivedLottery.getPrice(), t1.getPrice());
            }
        });
        archivedLotteries.addAll(aux);
    }

    private void sortPriceDesc(ArrayList<ArchivedLottery> aux){
        Collections.sort(aux, new Comparator<ArchivedLottery>() {
            @Override
            public int compare(ArchivedLottery archivedLottery, ArchivedLottery t1) {
                return Double.compare(t1.getPrice(), archivedLottery.getPrice());
            }
        });
        archivedLotteries.addAll(aux);
    }
}
