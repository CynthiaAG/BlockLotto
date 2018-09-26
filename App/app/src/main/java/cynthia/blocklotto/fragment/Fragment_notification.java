package cynthia.blocklotto.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cynthia.blocklotto.R;

public class Fragment_notification extends Fragment {

    private ListView notificationList;
    private ArrayList<String> notification;
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_notification, container, false);
        /*notificationList = (ListView) view.findViewById(R.id.notificationList);

        notification = new ArrayList<String>();
        notification.add("Ha ganado el sorteo CryptoLucky del día: 24/02/2018.");
        adapter = new ArrayAdapter<String>(getContext() ,android.R.layout.simple_list_item_1, notification);
        notificationList.setAdapter(adapter);*/

        return view;
    }

}
