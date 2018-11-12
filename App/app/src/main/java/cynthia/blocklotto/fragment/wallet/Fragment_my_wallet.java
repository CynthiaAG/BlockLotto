package cynthia.blocklotto.fragment.wallet;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

import cynthia.blocklotto.R;
import cynthia.blocklotto.wallet.Transaction;
import cynthia.blocklotto.adapter.wallet.Adapter_item_transaction;

public class Fragment_my_wallet extends Fragment {

    private View view;
    private SwipeRefreshLayout swipeWallet;
    private Button sort;
    private Button updateBalance;
    private TextView balanceWallet;
    private ListView listTransaction;
    private ArrayList<Transaction> transactions;
    private Adapter_item_transaction adapter;

    private boolean paySend;
    private boolean payReceived;

    private void initialize(){
        updateBalance = view.findViewById(R.id.refresh_balance);
        balanceWallet = view.findViewById(R.id.balanceWallet); //Get Balance in Server
        swipeWallet = (SwipeRefreshLayout) view.findViewById(R.id.swipeWallet);
        swipeWallet.setColorSchemeResources(R.color.colorSecondary, R.color.colorButton, R.color.colorAccent);
        listTransaction = (ListView) view.findViewById(R.id.transactionWallet);
        sort = (Button) view.findViewById(R.id.sortTransaction);

        paySend=false;
        payReceived=false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_wallet, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        initialize();
        getTransactions();
        adapter = new Adapter_item_transaction(getContext(), transactions);
        listTransaction.setAdapter(adapter);
        controlButton();
        return view;
    }

    private void controlButton(){
        swipeWallet.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(paySend){
                    paySend();
                    transactions.add(new Transaction("Pago ExtraCoin", "17/02/2016", "payment",5, 30));
                    adapter.notifyDataSetChanged();
                }else if(payReceived){
                    payReceived();
                    transactions.add(new Transaction("Premio RaffleCoin", "20/02/2017", "income",15, 45));
                    adapter.notifyDataSetChanged();
                }else{
                    allPay();
                    transactions.add(new Transaction("Premio RaffleCoin", "24/02/2018", "income",5, 5));
                    transactions.add(new Transaction("Pago CryptoLucky", "22/02/2018", "payment",10, 0));
                    adapter.notifyDataSetChanged();
                }
                swipeWallet.setRefreshing(false);
            }
        });

        updateBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                balanceWallet.setText("6 BTC");
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
    }

    private void controlSortMenu(MenuItem menuItem){

        if(menuItem.getTitle().equals("Todos los pagos")) {
            paySend=false;
            payReceived=false;
            allPay();
            adapter.notifyDataSetChanged();
        }else if(menuItem.getTitle().equals("Pagos recibidos")) {
            paySend=false;
            payReceived=true;
            payReceived();
            adapter.notifyDataSetChanged();
        }else if(menuItem.getTitle().equals("Pagos enviados")) {
            paySend=true;
            payReceived=false;
            paySend();
            adapter.notifyDataSetChanged();
        }
    }

    private void getTransactions(){
        transactions = new ArrayList<Transaction>();

        transactions.add(new Transaction("Premio Lotto Boom", "24/02/2018", "income",5, 5));
        transactions.add(new Transaction("Pago CryptoLucky", "22/02/2018", "payment",10, 0));
        transactions.add(new Transaction("Pago RaffleCoin", "20/02/2018", "payment",5, 10));
        transactions.add(new Transaction("Pago Lotto Boom", "29/01/2018","payment",15, 15));
        transactions.add(new Transaction("Pago Extracoin", "25/01/2018", "payment",5, 30));
        transactions.add(new Transaction("Pago CryptoLucky", "22/01/2018", "payment",10, 35));
        transactions.add(new Transaction("Premio CryptoLucky", "21/01/2018", "income",5, 45));
        transactions.add(new Transaction("Pago CryptoLucky", "22/02/2017", "payment",5, 40));
        transactions.add(new Transaction("Premio CryptoLucky", "20/02/2017", "income",15, 45));
        transactions.add(new Transaction("Pago CryptoLucky", "17/02/2016", "payment",5, 30));
    }

    private void allPay(){
        transactions.clear();
        transactions.add(new Transaction("Premio RaffleCoin", "24/02/2018", "income",5, 5));
        transactions.add(new Transaction("Pago CryptoLucky", "22/02/2018", "payment",10, 0));
        transactions.add(new Transaction("Pago RaffleCoin", "20/02/2018", "payment",5, 10));
        transactions.add(new Transaction("Pago Lotto Boom", "29/01/2018","payment",15, 15));
        transactions.add(new Transaction("Pago Extracoin", "25/01/2018", "payment",5, 30));
        transactions.add(new Transaction("Pago CryptoLucky", "22/01/2018", "payment",10, 35));
        transactions.add(new Transaction("Premio CryptoLucky", "21/01/2018", "income",5, 45));
        transactions.add(new Transaction("Pago CryptoLucky", "22/02/2017", "payment",5, 40));
        transactions.add(new Transaction("Premio CryptoLucky", "20/02/2017", "income",15, 45));
        transactions.add(new Transaction("Pago CryptoLucky", "17/02/2016", "payment",5, 30));

    }

    private void payReceived(){
        transactions.clear();
        transactions.add(new Transaction("Premio Lotto Boom", "24/02/2018", "income",5, 5));
        transactions.add(new Transaction("Premio CryptoLucky", "21/01/2018", "income",5, 45));
        transactions.add(new Transaction("Premio CryptoLucky", "20/02/2017", "income",15, 45));
    }

    private void paySend(){
        transactions.clear();
        transactions.add(new Transaction("Pago CryptoLucky", "22/02/2018", "payment",10, 0));
        transactions.add(new Transaction("Pago Lotto Boom", "20/02/2018", "payment",5, 10));
        transactions.add(new Transaction("Pago RaffleCoin", "29/01/2018","payment",15, 15));
        transactions.add(new Transaction("Pago Extracoin", "25/01/2018", "payment",5, 30));
        transactions.add(new Transaction("Pago CryptoLucky", "22/01/2018", "payment",10, 35));
        transactions.add(new Transaction("Pago CryptoLucky", "22/02/2017", "payment",5, 40));
        transactions.add(new Transaction("Pago CryptoLucky", "17/02/2016", "payment",5, 30));

    }

}
