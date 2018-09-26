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

import cynthia.blocklotto.lottery.CelebratedLottery;
import cynthia.blocklotto.R;
import cynthia.blocklotto.adaptor.Adaptor_celebrated_lottery;


public class Fragment_celebrated_lottery extends Fragment{

    private RecyclerView recyclerViewLottery;
    private Adaptor_celebrated_lottery adapterLottery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_celebrated_lottery, container, false) ;
        recyclerViewLottery = view.findViewById(R.id.container_celebratedLottery);
        directionControl();
        adapterLottery = new Adaptor_celebrated_lottery((getSorteos()));
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

    public List<CelebratedLottery> getSorteos(){
        List<CelebratedLottery> celebratedLotteries = new ArrayList<>();

        celebratedLotteries.add( new CelebratedLottery( R.drawable.start2, "El Gordo Digital", "22/05/2020", "Juan" , 1 ));
        celebratedLotteries.add( new CelebratedLottery( R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", null, 0));
        celebratedLotteries.add( new CelebratedLottery( R.drawable.flor, "CryptoLucky", "05/05/2080", null, 0));
        celebratedLotteries.add( new CelebratedLottery( R.drawable.gato, "Extracoin", "14/7/2060", "Juan", 5));
        celebratedLotteries.add( new CelebratedLottery( R.drawable.start2, "El Gordo Digital", "22/05/2020", null, 0));
        celebratedLotteries.add( new CelebratedLottery( R.drawable.buddha_moneda, "FatBitoin", "9/8/2025",null, 0));
        celebratedLotteries.add( new CelebratedLottery( R.drawable.flor, "CryptoLucky", "05/05/2080", null, 0));
        celebratedLotteries.add( new CelebratedLottery( R.drawable.gato, "Extracoin", "14/7/2060", null, 0));
        celebratedLotteries.add( new CelebratedLottery( R.drawable.start2, "El Gordo Digital", "22/05/2020", null, 0));
        celebratedLotteries.add( new CelebratedLottery( R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", null, 0));
        celebratedLotteries.add( new CelebratedLottery( R.drawable.flor, "CryptoLucky", "05/05/2080", null, 0));
        celebratedLotteries.add( new CelebratedLottery( R.drawable.gato, "Extracoin", "14/7/2060", null, 0));

        return celebratedLotteries;
    }
}
