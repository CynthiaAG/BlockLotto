package cynthia.blocklotto.fragment.operation;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import cynthia.blocklotto.R;
import cynthia.blocklotto.adapter.operation.Adapter_operation;

public class Fragment_operation extends Fragment {

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

    private void initialize(){
        inflater = getLayoutInflater();
        customToast = inflater.inflate(R.layout.custom_toast, (ViewGroup) view.findViewById(R.id.custom_toast_container));
        text = (TextView) customToast.findViewById(R.id.textToast);
        text.setText("Error durante el escaneo");

        balance = view.findViewById(R.id.balanceOperation);
        refreshBalance = view.findViewById(R.id.refresh_balance);

        tabOperation = view.findViewById(R.id.tabOperation);
        viewPagerOperation = view.findViewById(R.id.viewPagerOperation);
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
                balance.setText("6 BTC");
            }
        });
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
                    // String codeFormat = scanningResult.getFormatName(); Format to Scan in this case QR_CODE
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
}
