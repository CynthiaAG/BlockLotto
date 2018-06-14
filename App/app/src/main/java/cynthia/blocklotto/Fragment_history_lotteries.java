package cynthia.blocklotto;

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


public class Fragment_history_lotteries extends Fragment{


    private RecyclerView recyclerViewSorteo;
    private Adaptor_celebrated_lottery adapterLottery;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_history_lotteries, container, false) ;

        recyclerViewSorteo = view.findViewById(R.id.contenedorCelebratedLottery);

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerViewSorteo.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        }
        else{
            recyclerViewSorteo.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        }

        adapterLottery = new Adaptor_celebrated_lottery((getSorteos()));
        recyclerViewSorteo.setAdapter(adapterLottery);
        return view;
    }

    public List<CelebratedLottery> getSorteos(){
        List<CelebratedLottery> CelebratedLotteries = new ArrayList<>();

        CelebratedLotteries.add( new CelebratedLottery( R.drawable.start2, "El Gordo Digital", "22/05/2020"));
        CelebratedLotteries.add( new CelebratedLottery( R.drawable.buddha_moneda, "FatBitoin", "9/8/2025"));
        CelebratedLotteries.add( new CelebratedLottery( R.drawable.flor, "CryptoLucky", "05/05/2080"));
        CelebratedLotteries.add( new CelebratedLottery( R.drawable.gato, "Extracoin", "14/7/2060"));
        CelebratedLotteries.add( new CelebratedLottery( R.drawable.start2, "El Gordo Digital", "22/05/2020"));
        CelebratedLotteries.add( new CelebratedLottery( R.drawable.buddha_moneda, "FatBitoin", "9/8/2025"));
        CelebratedLotteries.add( new CelebratedLottery( R.drawable.flor, "CryptoLucky", "05/05/2080"));
        CelebratedLotteries.add( new CelebratedLottery( R.drawable.gato, "Extracoin", "14/7/2060"));
        CelebratedLotteries.add( new CelebratedLottery( R.drawable.start2, "El Gordo Digital", "22/05/2020"));
        CelebratedLotteries.add( new CelebratedLottery( R.drawable.buddha_moneda, "FatBitoin", "9/8/2025"));
        CelebratedLotteries.add( new CelebratedLottery( R.drawable.flor, "CryptoLucky", "05/05/2080"));
        CelebratedLotteries.add( new CelebratedLottery( R.drawable.gato, "Extracoin", "14/7/2060"));

        return CelebratedLotteries;
    }
}
