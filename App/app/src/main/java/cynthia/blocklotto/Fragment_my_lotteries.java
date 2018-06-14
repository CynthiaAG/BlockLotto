package cynthia.blocklotto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Fragment_my_lotteries extends Fragment {

    private RecyclerView recyclerViewSorteo;
    private Adaptor_my_lottery adapterLottery;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_my_lotteries, container, false) ;

        recyclerViewSorteo = view.findViewById(R.id.contenedorMyLottery);
        recyclerViewSorteo.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterLottery = new Adaptor_my_lottery((getSorteos()));
        recyclerViewSorteo.setAdapter(adapterLottery);
        return view;
    }

    public List<MyLottery> getSorteos(){
        List<MyLottery> MyLotteries = new ArrayList<>();

        MyLotteries.add( new MyLottery( R.drawable.flor, "El Gordo Digital", "22/05/2020", "Archivar"));
        MyLotteries.add( new MyLottery( R.drawable.flor, "FatBitoin", "9/8/2025","Archivar"));
        MyLotteries.add( new MyLottery( R.drawable.flor, "CryptoLucky", "05/05/2080", "Archivar"));
        MyLotteries.add( new MyLottery( R.drawable.flor, "Extracoin", "14/7/2060", "Archivar"));
        return MyLotteries;
    }

}
