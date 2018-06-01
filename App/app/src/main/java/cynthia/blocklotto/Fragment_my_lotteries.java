package cynthia.blocklotto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

public class Fragment_my_lotteries extends Fragment {

    private Toolbar myToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_my_lotteries, container, false) ;


        String [] values = new String[]{"La CriptoLucky: 2boletos, 3días restantes", "FatBitcoin: 1boleto, 15días restantes" };
        ListView mylotteries = (ListView) view.findViewById(R.id.my_lottery_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);

        mylotteries.setAdapter(adapter);
        return view;
    }

}
