package cynthia.blocklotto.fragment.wallet;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import cynthia.blocklotto.R;
import cynthia.blocklotto.conection.ResultFromJson;
import cynthia.blocklotto.conection.Accessor;
import cynthia.blocklotto.conection.AccessorResponse;
import cynthia.blocklotto.wallet.Transaction;
import cynthia.blocklotto.adapter.wallet.Adapter_item_transaction;

public class Fragment_my_wallet extends Fragment implements AccessorResponse {

    private View view;
    private SwipeRefreshLayout swipeWallet;
    private Button sort;
    private Button updateBalance;
    private Button getBTCFromChannel;
    private TextView balanceWallet;
    private TextView balanceChannel;
    private ListView listTransaction;
    private ArrayList<Transaction> transactions;
    private Adapter_item_transaction adapter;
    private ProgressBar progressBarWallet;
    private ProgressBar progressTransaction;
    private TextView notTransactions;

    private boolean balanceWT;
    private boolean balanceCH;
    private boolean balanceGet;
    private boolean transactionB;
    private boolean creation;

    private String balanceCHNL;

    private boolean paySend;
    private boolean payReceived;

    private PopupMenu sortMenu;

    private void initialize(){
        balanceWT = false;
        balanceCH = false;
        balanceGet = false;
        transactionB = false;
        creation = true;

        paySend=false;
        payReceived=false;

        updateBalance = view.findViewById(R.id.refresh_balance);
        progressBarWallet = view.findViewById(R.id.progress_wallet);
        progressTransaction = view.findViewById(R.id.progress_transaction);
        swipeWallet = (SwipeRefreshLayout) view.findViewById(R.id.swipeWallet);
        listTransaction = (ListView) view.findViewById(R.id.transactionWallet);
        sort = (Button) view.findViewById(R.id.sortTransaction);
        balanceChannel = view.findViewById(R.id.balanceChannel);
        getBTCFromChannel = view.findViewById(R.id.getBalanceFromChannels);

        progressTransaction.setVisibility(View.VISIBLE);
        getBTCFromChannel.setVisibility(View.INVISIBLE);

        balanceWallet = view.findViewById(R.id.balanceWallet);
        balanceWallet.setText("--");

        getBalance();

        swipeWallet.setColorSchemeResources(R.color.colorSecondary, R.color.colorButton, R.color.colorAccent);
        balanceChannel.setText("--");

        notTransactions = view.findViewById(R.id.textNotTransactions);
        transactions = new ArrayList<Transaction>();

        sortMenu = new PopupMenu(view.getContext(), sort);
        sortMenu.getMenuInflater().inflate(R.menu.sort_pay_menu, sortMenu.getMenu());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_wallet, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        initialize();
        adapter = new Adapter_item_transaction(getContext(), transactions);
        listTransaction.setAdapter(adapter);
        controlButton();
        return view;
    }

    private void getBalance(){
        balanceWT = true;
        progressBarWallet.setVisibility(View.VISIBLE);
        updateBalance.setVisibility(View.INVISIBLE);
        Accessor con = new Accessor();
        con.accessorResponse =this;
        con.getBalance(getContext());
    }

    private void getBalanceChannel(){
        balanceCH = true;
        Accessor con = new Accessor();
        con.accessorResponse =this;
        con.getBalanceChannel(getContext());

    }

    private void getBTCFromChannel(){
        balanceGet = true;
        Accessor con = new Accessor();
        con.accessorResponse =this;
        con.getBTCFromChannels(getContext());

    }

    private void getTransactions(){
        transactionB = true;
        Accessor con = new Accessor();
        con.accessorResponse =this;
        con.getTransaction(getContext());
    }

    private void showNotInternet(){
        transactions.clear();
        adapter.notifyDataSetChanged();
        balanceWT = false;
        balanceCH = false;
        balanceGet = false;
        transactionB = false;
        progressTransaction.setVisibility(View.INVISIBLE);
        progressBarWallet.setVisibility(View.INVISIBLE);
        updateBalance.setVisibility(View.VISIBLE);
        sort.setVisibility(View.INVISIBLE);
        notTransactions.setText("No se pueden mostrar tus movimientos. Revise su conexión a internet.");
        notTransactions.setVisibility(View.VISIBLE);
    }


    @Override
    public void processFinish(String output) {
        ResultFromJson resultFromJson = new ResultFromJson();
        if(output == null){
            showNotInternet();

        } else if (balanceWT){
            balanceWallet.setText(resultFromJson.getBalanceResult(output));
            balanceWT = false;
            getBalanceChannel();

        } else if(balanceCH) {
            balanceCHNL = resultFromJson.getBalanceChannelResult(output);
            if (balanceCHNL == null || balanceCHNL.equals("0.0")) {
                getBTCFromChannel.setVisibility(View.INVISIBLE);
                balanceChannel.setText("0 BTC");
            } else {
                getBTCFromChannel.setVisibility(View.VISIBLE);
                balanceChannel.setText(balanceCHNL + " BTC");
            }
            balanceCH = false;
            progressBarWallet.setVisibility(View.INVISIBLE);
            updateBalance.setVisibility(View.VISIBLE);
            if(creation){
                creation = false;
                getTransactions();
            }
        } else if(balanceGet) {
            balanceGet = false;
            getBalance();

        } else if(transactionB) {
            transactionB = false;
            progressTransaction.setVisibility(View.INVISIBLE);
            transactions.clear();
            ArrayList<Transaction> aux = resultFromJson.getTransactionsResult(output);
            if ((!aux.isEmpty()) && (aux != null)) {
               showTransactions(aux);
            } else {
                sort.setVisibility(View.INVISIBLE);
                notTransactions.setText("No hay movimientos.");
                notTransactions.setVisibility(View.VISIBLE);
            }
        }

    }

    private void showTransactions( ArrayList<Transaction> aux){
        notTransactions.setVisibility(View.INVISIBLE);
        sort.setVisibility(View.VISIBLE);
        if(payReceived){
            for (Transaction mov: aux) {
                if(mov.getAction().equals("receive")){
                    transactions.add(mov);
                }
            }
            if(transactions.isEmpty() || transactions == null){
                payReceived = true;
                notTransactions.setText("No se encuentran pagos recibidos.");
                notTransactions.setVisibility(View.VISIBLE);
            }
        }else if(paySend){
            for (Transaction mov: aux) {
                if(mov.getAction().equals("send")){
                    transactions.add(mov);
                }
            }
            if(transactions.isEmpty() || transactions == null){
                paySend = true;
                notTransactions.setText("No se encuentran pagos enviados.");
                notTransactions.setVisibility(View.VISIBLE);
            }
        }else if((!paySend) && (!payReceived)){
            for (Transaction mov : aux) {
                transactions.add(mov);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void controlButton(){
        swipeWallet.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                System.out.println("POR AQUI");
                getTransactions();
                swipeWallet.setRefreshing(false);
            }
        });

        updateBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBalance();
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        controlSortMenu(menuItem);
                        menuItem.setChecked(!menuItem.isChecked());
                        return true;
                    }
                });
                sortMenu.show();
            }});
        getBTCFromChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(getContext(), R.style.MyDialogTheme);
                builder.setTitle("Recuperar fondos de los canales")
                        .setMessage("¿Estas seguro que quieres cerrar todos los canales? Se acumularán " + balanceCHNL + " BTC al saldo de tu wallet.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                getBTCFromChannel();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(R.drawable.ic_warning)
                        .show();

            }
        });
    }

    private void controlSortMenu(MenuItem menuItem){
        if(menuItem.getTitle().equals("Todos los pagos")) {
            paySend=false;
            payReceived=false;
        }else if(menuItem.getTitle().equals("Pagos recibidos")) {
            paySend=false;
            payReceived=true;
        }else if(menuItem.getTitle().equals("Pagos enviados")) {
            paySend=true;
            payReceived=false;
        }
        getTransactions();
    }
}
