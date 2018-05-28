package cynthia.blocklotto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Fragment02 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_fragment02, container, false) ;

        String [] values = new String[]{"El CriptoMillón: 29/11/2020, 0.005B", "CuarentaBillionCoin: 02/05/2019, 0.000008B" };
        ListView future = (ListView) view.findViewById(R.id.future_lotteries_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);

        future.setAdapter(adapter);
        return view;
    }
}
