package cynthia.blocklotto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Fragment02 extends Fragment {

    private RecyclerView recyclerViewSorteo;
    private RecyclerViewAdaptador adaptadorSorteo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_fragment02, container, false) ;

       /* String [] values = new String[]{"El CriptoMillón: 29/11/2020, 0.005B", "CuarentaBillionCoin: 02/05/2019, 0.000008B" };
        ListView future = (ListView) view.findViewById(R.id.future_lotteries_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);

        future.setAdapter(adapter);*/


       recyclerViewSorteo = view.findViewById(R.id.contenedorRecycle);
       recyclerViewSorteo.setLayoutManager(new LinearLayoutManager(getActivity()));
       adaptadorSorteo = new RecyclerViewAdaptador((getSorteos()));
       recyclerViewSorteo.setAdapter(adaptadorSorteo);
        return view;
    }

    public List<sorteo> getSorteos(){
        List<sorteo> sorteos = new ArrayList<>();

        sorteos.add( new sorteo( R.drawable.start2, "El Gordo Digital", "22/05/2020"));
        sorteos.add( new sorteo( R.drawable.f2, "El BitMillonario", "29/11/2045"));
        sorteos.add( new sorteo( R.drawable.buddha_moneda, "FatBitoin", "9/8/2025"));
        sorteos.add( new sorteo( R.drawable.flor, "CryptoLucky", "05/05/2080"));
        sorteos.add( new sorteo( R.drawable.gato, "Extracoin", "14/7/2060"));
        return sorteos;
    }

}
