package cynthia.blocklotto.fragment;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import cynthia.blocklotto.lottery.ArchivedLottery;
import cynthia.blocklotto.R;
import cynthia.blocklotto.adaptor.Adaptor_archived_lottery;

/**
 * Created by Cynthia on 11/06/2018.
 */

public class Fragment_archived_lottery  extends Fragment{

    private RecyclerView recyclerViewLottery;
    private Adaptor_archived_lottery adapterLottery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_archived_lottery, container, false) ;
        recyclerViewLottery = view.findViewById(R.id.container_archivedLottery);
        directionControl();
        adapterLottery = new Adaptor_archived_lottery((getSorteos()));
        recyclerViewLottery.setAdapter(adapterLottery);
        return view;
    }

    private void directionControl(){
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerViewLottery.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        }
        else{
            recyclerViewLottery.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        }
    }

    public List<ArchivedLottery> getSorteos(){
        List<ArchivedLottery> archivedLotteries = new ArrayList<>();

        archivedLotteries.add( new ArchivedLottery( R.drawable.start2, "El Gordo Digital", "22/05/2020"));
        archivedLotteries.add( new ArchivedLottery( R.drawable.buddha_moneda, "FatBitoin", "9/8/2025"));
        archivedLotteries.add( new ArchivedLottery( R.drawable.flor, "CryptoLucky", "05/05/2080"));
        archivedLotteries.add( new ArchivedLottery( R.drawable.gato, "Extracoin", "14/7/2060"));
        archivedLotteries.add( new ArchivedLottery( R.drawable.start2, "El Gordo Digital", "22/05/2020"));
        archivedLotteries.add( new ArchivedLottery( R.drawable.buddha_moneda, "FatBitoin", "9/8/2025"));
        archivedLotteries.add( new ArchivedLottery( R.drawable.flor, "CryptoLucky", "05/05/2080"));
        archivedLotteries.add( new ArchivedLottery( R.drawable.gato, "Extracoin", "14/7/2060"));
        archivedLotteries.add( new ArchivedLottery( R.drawable.start2, "El Gordo Digital", "22/05/2020"));
        archivedLotteries.add( new ArchivedLottery( R.drawable.buddha_moneda, "FatBitoin", "9/8/2025"));
        archivedLotteries.add( new ArchivedLottery( R.drawable.flor, "CryptoLucky", "05/05/2080"));
        archivedLotteries.add( new ArchivedLottery( R.drawable.gato, "Extracoin", "14/7/2060"));
        return archivedLotteries;
    }



}
