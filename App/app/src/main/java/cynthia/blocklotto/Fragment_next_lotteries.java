package cynthia.blocklotto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Fragment_next_lotteries extends Fragment {

    private RecyclerView recyclerViewSorteo;
    private RecyclerViewAdaptador adapterLottery;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_next_lotteries, container, false) ;

       recyclerViewSorteo = view.findViewById(R.id.contenedorRecycle);
       recyclerViewSorteo.setLayoutManager(new LinearLayoutManager(getActivity()));
       adapterLottery = new RecyclerViewAdaptador((getSorteos()));
       recyclerViewSorteo.setAdapter(adapterLottery);
        return view;
    }

    public List<NextLottery> getSorteos(){
        List<NextLottery> NextLotteries = new ArrayList<>();

        NextLotteries.add( new NextLottery( R.drawable.start2, "El Gordo Digital", "22/05/2020"));
        NextLotteries.add( new NextLottery( R.drawable.f2, "El BitMillonario", "29/11/2045"));
        NextLotteries.add( new NextLottery( R.drawable.buddha_moneda, "FatBitoin", "9/8/2025"));
        NextLotteries.add( new NextLottery( R.drawable.flor, "CryptoLucky", "05/05/2080"));
        NextLotteries.add( new NextLottery( R.drawable.gato, "Extracoin", "14/7/2060"));
        return NextLotteries;
    }

}
