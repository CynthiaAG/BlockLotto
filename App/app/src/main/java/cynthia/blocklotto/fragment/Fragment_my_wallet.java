package cynthia.blocklotto.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

import cynthia.blocklotto.R;
import cynthia.blocklotto.action.OperationWallet;
import cynthia.blocklotto.action.Transfer;

public class Fragment_my_wallet extends Fragment {

    private Button operation;
    private ListView listTransaction;
    private ArrayList<String> listContent;
    private Button sort;
    private ArrayAdapter<String> adapter;
    private Button operationWallet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_wallet, container, false);

        operation = (Button) view.findViewById(R.id.operationWallet);
        listTransaction = (ListView) view.findViewById(R.id.transactionWallet);
        sort = (Button) view.findViewById(R.id.sortTransaction);
        operationWallet = (Button) view.findViewById(R.id.operationWallet);

        listContent = new ArrayList<String>();
        listContent.add("24/02/2018                            +5 BTC\nPremio CryptoLucky");
        listContent.add("22/02/2018\t                          -0.002 BTC\nPago CryptoLucky");
        adapter = new ArrayAdapter<String>(getContext() ,android.R.layout.simple_list_item_1, listContent);
        listTransaction.setAdapter(adapter);

        operationWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context c = view.getContext();
                c.startActivity(new Intent(c , OperationWallet.class));
            }
        });
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu sortMenu = new PopupMenu(view.getContext(), sort);
                sortMenu.getMenuInflater().inflate(R.menu.sort_pay_menu, sortMenu.getMenu());
                sortMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        controlSortMenu(menuItem);
                        return true;
                    }
                });
                sortMenu.show();
            }});

        return view;
    }

    private void controlSortMenu(MenuItem menuItem){

        if(menuItem.getTitle().equals("Todos los pagos")) {
            listContent.clear();
            listContent.add("24/02/2018                            +5 BTC\nPremio CryptoLucky");
            listContent.add("22/02/2018\t                          -0.002 BTC\nPago CryptoLucky");
            adapter.notifyDataSetChanged();
        }else if(menuItem.getTitle().equals("Pagos recibidos")) {
            listContent.clear();
            listContent.add("24/02/2018                            +5 BTC\nPremio CryptoLucky");
            listTransaction.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else if(menuItem.getTitle().equals("Pagos enviados")) {
            listContent.clear();
            listContent.add("22/02/2018\t                          -0.002 BTC\nPago CryptoLucky");
            adapter.notifyDataSetChanged();
        }
    }

}
