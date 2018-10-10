package cynthia.blocklotto.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;

import cynthia.blocklotto.R;
import cynthia.blocklotto.Transaction;
import cynthia.blocklotto.adapter.Adapter_item_transaction;

public class Fragment_my_wallet extends Fragment {

    private ListView listTransaction;
    private Button sort;
    private ArrayList<Transaction> transactions;
    private Adapter_item_transaction adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_wallet, container, false);

        listTransaction = (ListView) view.findViewById(R.id.transactionWallet);
        sort = (Button) view.findViewById(R.id.sortTransaction);

        transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction("Premio CryptoLucky", "24/02/2018", "5"));
        transactions.add(new Transaction("Pago CryptoLucky", "22/02/2018", "2"));
        transactions.add(new Transaction("Premio CryptoLucky", "24/02/2018", "5"));
        transactions.add(new Transaction("Pago CryptoLucky", "22/02/2018", "2"));
        transactions.add(new Transaction("Premio CryptoLucky", "24/02/2018", "5"));
        transactions.add(new Transaction("Pago CryptoLucky", "22/02/2018", "2"));
        transactions.add(new Transaction("Premio CryptoLucky", "24/02/2018", "5"));
        transactions.add(new Transaction("Pago CryptoLucky", "22/02/2018", "2"));
        transactions.add(new Transaction("Premio CryptoLucky", "24/02/2018", "5"));
        transactions.add(new Transaction("Pago CryptoLucky", "22/02/2018", "2"));

        adapter = new Adapter_item_transaction(getContext(), transactions);
        listTransaction.setAdapter(adapter);

        /*
        operationWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context c = view.getContext();
                c.startActivity(new Intent(c , OperationWallet.class));
            }
        });*/

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
            transactions.clear();
            transactions.add(new Transaction("Premio CryptoLucky", "24/02/2018", "5"));
            transactions.add(new Transaction("Pago CryptoLucky", "22/02/2018", "2"));
            adapter.notifyDataSetChanged();
        }else if(menuItem.getTitle().equals("Pagos recibidos")) {
            transactions.clear();
            transactions.add(new Transaction("Premio CryptoLucky", "24/02/2018", "5"));
            adapter.notifyDataSetChanged();
        }else if(menuItem.getTitle().equals("Pagos enviados")) {
            transactions.clear();
            transactions.add(new Transaction("Pago CryptoLucky", "22/02/2018", "2"));
            adapter.notifyDataSetChanged();
        }
    }

}
