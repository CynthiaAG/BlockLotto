package cynthia.blocklotto.fragment.operation;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import cynthia.blocklotto.R;
import cynthia.blocklotto.conection.ResultFromJson;
import cynthia.blocklotto.adapter.operation.Adapter_operation;
import cynthia.blocklotto.conection.Accessor;
import cynthia.blocklotto.conection.AccessorResponse;

public class Fragment_operation extends Fragment implements AccessorResponse {

    private View view;
    private TabLayout tabOperation;
    private ViewPager viewPagerOperation;
    private Adapter_operation adapter;

    private Fragment_sending fragmentSending;

    private LayoutInflater inflater;
    private View customToast;
    private TextView text;

    private TextView balance;
    private Button refreshBalance;

    private ProgressBar progressBarOperation;

    private View customToastInternet;
    private LayoutInflater inflaterInternet;
    private TextView textInternet;

    private boolean balanceB;

    private void initialize(){
        balanceB=false;


        inflater = getLayoutInflater();
        customToast = inflater.inflate(R.layout.custom_toast, (ViewGroup) view.findViewById(R.id.custom_toast_container));
        text = (TextView) customToast.findViewById(R.id.textToast);
        text.setText("Error durante el escaneo");

        inflaterInternet = getLayoutInflater();
        customToastInternet = inflaterInternet.inflate(R.layout.custom_toast, (ViewGroup) view.findViewById(R.id.custom_toast_container));
        textInternet = (TextView) customToastInternet.findViewById(R.id.textToast);
        textInternet.setText("Revise su conexión a internet.");

        balance = view.findViewById(R.id.balanceOperation);
        progressBarOperation= view.findViewById(R.id.progress_operation);
        refreshBalance = view.findViewById(R.id.refresh_balance);
        tabOperation = view.findViewById(R.id.tabOperation);
        viewPagerOperation = view.findViewById(R.id.viewPagerOperation);
        balance.setText("--");
        getBalance();

        adapter = new Adapter_operation(getChildFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_operations, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initialize();
        addFragment();
        viewPagerOperation.setAdapter(adapter);
        tabOperation.setupWithViewPager(viewPagerOperation);

        controlButton();
        setHasOptionsMenu(true);
        return view;
    }

    private void controlButton(){
        refreshBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBalance();
            }
        });
    }

    private void getBalance(){
        balanceB=true;
        progressBarOperation.setVisibility(View.VISIBLE);
        refreshBalance.setVisibility(View.INVISIBLE);
        Accessor con = new Accessor();
        con.accessorResponse =this;
        con.getBalance(getContext());

    }

    private void addFragment(){
        fragmentSending = new Fragment_sending();
        adapter.addFragment(new Fragment_receipt(), "Recibir");
        adapter.addFragment(new Fragment_request(), "Pedir");
        adapter.addFragment(fragmentSending, "Enviar");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_scan, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.scan_toolbar){
            scanQR();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void scanQR(){
        IntentIntegrator integrator = new IntentIntegrator(this.getActivity()).forSupportFragment(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setCameraId(0);
        integrator.setOrientationLocked(true);
        integrator.setPrompt("Escanea el código");
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        Bundle qr = new Bundle();//create bundle instance
        if(scanningResult!= null){
            if(scanningResult.getContents() == null){
            }else {
                try {
                    qr.putString("QR_SCAN", scanningResult.getContents().toString());
                    fragmentSending.setArguments(qr);
                    viewPagerOperation.setCurrentItem(2);
                    fragmentSending.controlScan();
                }catch(NullPointerException e){
                    Toast toast = new Toast(getContext());
                    toast.setView(customToast);
                    toast.show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    @Override
    public void processFinish(String output) {
        ResultFromJson resultFromJson = new ResultFromJson();
        if(output ==null){
            progressBarOperation.setVisibility(View.INVISIBLE);
            refreshBalance.setVisibility(View.VISIBLE);
            Toast toast = new Toast(getContext());
            toast.setView(customToastInternet);
            toast.show();
        }else if(balanceB) {
            balance.setText(resultFromJson.getBalanceResult(output));
            progressBarOperation.setVisibility(View.INVISIBLE);
            refreshBalance.setVisibility(View.VISIBLE);
            balanceB=false;
        }
    }
}
