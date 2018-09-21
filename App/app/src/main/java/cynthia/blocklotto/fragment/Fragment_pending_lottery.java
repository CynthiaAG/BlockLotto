package cynthia.blocklotto.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cynthia.blocklotto.lottery.PendingLottery;
import cynthia.blocklotto.R;
import cynthia.blocklotto.adaptor.Adaptor_pending_lottery;

public class Fragment_pending_lottery extends Fragment {

    private RecyclerView recyclerViewLottery;
    private Adaptor_pending_lottery adapterLottery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_pending_lottery, container, false) ;

        recyclerViewLottery = view.findViewById(R.id.container_PendingLottery);
        recyclerViewLottery.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterLottery = new Adaptor_pending_lottery((getSorteos()));
        recyclerViewLottery.setAdapter(adapterLottery);
        return view;
    }

    public List<PendingLottery> getSorteos(){
        List<PendingLottery> pendingLotteries = new ArrayList<>();

        pendingLotteries.add( new PendingLottery( R.drawable.flor, "El Gordo Digital", "22/05/2020", "Archivar"));
        pendingLotteries.add( new PendingLottery( R.drawable.flor, "FatBitoin", "9/8/2025","Archivar"));
        pendingLotteries.add( new PendingLottery( R.drawable.flor, "CryptoLucky", "05/05/2080", "Archivar"));
        pendingLotteries.add( new PendingLottery( R.drawable.flor, "Extracoin", "14/7/2060", "Archivar"));
        return pendingLotteries;
    }

}
