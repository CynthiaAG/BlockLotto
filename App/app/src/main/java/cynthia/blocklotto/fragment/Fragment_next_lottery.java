package cynthia.blocklotto.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import cynthia.blocklotto.lottery.NextLottery;
import cynthia.blocklotto.R;
import cynthia.blocklotto.adaptor.Adaptor_next_lottery;

public class Fragment_next_lottery extends Fragment {

    private RecyclerView recyclerViewLottery;
    private Adaptor_next_lottery adapterLottery;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_next_lottery, container, false) ;

       recyclerViewLottery = view.findViewById(R.id.container_nextLottery);
       recyclerViewLottery.setLayoutManager(new LinearLayoutManager(getActivity()));
       adapterLottery = new Adaptor_next_lottery((getSorteos()));
       recyclerViewLottery.setAdapter(adapterLottery);

        return view;
    }

    public List<NextLottery> getSorteos(){
        List<NextLottery> nextLotteries = new ArrayList<>();

        nextLotteries.add( new NextLottery( R.drawable.start2, "El Gordo Digital", "22/05/2020", "0,000001 b", "Comprar"));
        nextLotteries.add( new NextLottery( R.drawable.buddha_moneda, "FatBitoin", "9/8/2025", "0,0012 b", "Comprar"));
        nextLotteries.add( new NextLottery( R.drawable.flor, "CryptoLucky", "05/05/2080", "0,00051 b", "Comprar"));
        nextLotteries.add( new NextLottery( R.drawable.gato, "Extracoin", "14/7/2060", "0,00015 b", "Comprar"));
        return nextLotteries;
    }

}
