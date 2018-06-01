package cynthia.blocklotto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Fragment_history_lotteries extends Fragment{



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_lotteries, container, false);

        String[] values = new String[]{"El CriptoGordo: 25/05/2017", "El Extracoin: 29/06/2016"};

        ListView history = (ListView) view.findViewById(R.id.history_lotteries_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);

        history.setAdapter(adapter);


        // Inflate the layout for this fragment
        return view;
    }
}
