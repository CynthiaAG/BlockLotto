package cynthia.blocklotto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cynthia on 11/06/2018.
 */

public class Fragment_archived_lottery  extends Fragment{
    private RecyclerView recyclerViewSorteo;
    private Adaptor_archived_lottery adapterLottery;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_archived_lottery, container, false) ;

        recyclerViewSorteo = view.findViewById(R.id.container_archivedLottery);
        recyclerViewSorteo.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterLottery = new Adaptor_archived_lottery((getSorteos()));
        recyclerViewSorteo.setAdapter(adapterLottery);
        return view;
    }

    public List<ArchivedLottery> getSorteos(){
        List<ArchivedLottery> ArchivedLotteries = new ArrayList<>();

        ArchivedLotteries.add( new ArchivedLottery( R.drawable.start2, "El Gordo Digital", "22/05/2020"));
        ArchivedLotteries.add( new ArchivedLottery( R.drawable.buddha_moneda, "FatBitoin", "9/8/2025"));
        ArchivedLotteries.add( new ArchivedLottery( R.drawable.flor, "CryptoLucky", "05/05/2080"));
        ArchivedLotteries.add( new ArchivedLottery( R.drawable.gato, "Extracoin", "14/7/2060"));
        return ArchivedLotteries;
    }

}
