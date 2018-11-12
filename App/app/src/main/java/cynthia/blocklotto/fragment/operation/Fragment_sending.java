package cynthia.blocklotto.fragment.operation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cynthia.blocklotto.R;

public class Fragment_sending extends Fragment {

    private View view;

    private Button sendButtonFinal;
    private EditText amountSend;
    private EditText addressReception;
    private String addressScan;
    private String amount;

    private View customToast;
    private LayoutInflater inflater;
    private TextView text;

    private String stringScan;

    public void initialize(){
        sendButtonFinal = view.findViewById(R.id.sendButtonFinal);
        addressReception = view.findViewById(R.id.addressReception);
        amountSend = view.findViewById(R.id.amountSend);

        inflater = getLayoutInflater();
        customToast = inflater.inflate(R.layout.custom_toast, (ViewGroup) view.findViewById(R.id.custom_toast_container));
        text = (TextView) customToast.findViewById(R.id.textToast);
        text.setText("BTC enviados");

      /*  try{
            stringScan = getArguments().getString("QR_SCAN");
        }catch (NullPointerException e){
           */ stringScan = "";
      //  }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sending, container, false);
        initialize();
       // controlScan();
        controlButton();
        return view;
    }

    public void controlScan(){
        try {
            stringScan = getArguments().getString("QR_SCAN");
            if(stringScan!=null && (!stringScan.equals("")) && (!stringScan.isEmpty())) {
                stringScan = getArguments().getString("QR_SCAN");
                for (int i = 0; i < stringScan.length(); i++) {
                    if(i != stringScan.length()-1) {
                        if (stringScan.charAt(i) == '.' && stringScan.charAt(i + 1) == '.') {
                            addressScan = stringScan.substring(0, i);
                            amount = stringScan.substring(i + 2);
                            break;
                        }
                    }
                        addressScan = stringScan;
                        amount = "";
                    }
                    showAlertScanElement();
            }else{
                addressScan = "";
                amount = "";
            }
        } catch(NullPointerException e){
            addressScan = "";
            amount = "";
        }
    }

    private void showAlertScanElement(){
        if(! amount.isEmpty()){
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Enviar BTC")
                    .setMessage("Estas seguro que quieres enviar " + amount + " BTC a la dirección: " + addressScan)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //Proccess to send BTC (convert amount in double and send de address)
                            addressReception.getText().clear();
                            amountSend.getText().clear();
                            Toast toast = new Toast(getContext());
                            toast.setView(customToast);
                            toast.show();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            addressReception.setError(null);
                            amountSend.setError(null);
                            addressReception.setText(addressScan);
                            amountSend.setText(amount);
                        }
                    })
                    .setIcon(R.drawable.ic_warning)
                    .show();
        }else{
            amountSend.setError(null);
            amountSend.getText().clear();
            addressReception.setError(null);
            addressReception.setText(addressScan);
        }
    }

    private void controlButton(){
        sendButtonFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendManualBTC();
            }
        });
    }

    private void sendManualBTC(){
        amount = amountSend.getText().toString().trim();
        addressScan = addressReception.getText().toString().trim();

        if(TextUtils.isEmpty(amount) && TextUtils.isEmpty(addressScan)){
            addressReception.setError("Introduzca la dirección\n de recepción");
            amountSend.setError("Introduzca la cantidad\n que desea enviar");
        } else if(TextUtils.isEmpty(amount)) {
            amountSend.setError("Introduzca la cantidad\n que desea enviar");
        } else if( TextUtils.isEmpty(addressScan)){
            addressReception.setError("Introduzca la dirección\n de recepción");
        } else{
           showAlertManualSend();
        }
    }

    private void showAlertManualSend(){

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Enviar BTC")
                .setMessage("Estas seguro que quieres enviar " + amount + " BTC a la dirección: " + addressScan)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Proccess to send BTC (convert amount in double and send de address)
                        addressReception.getText().clear();
                        amountSend.getText().clear();
                        Toast toast = new Toast(getContext());
                        toast.setView(customToast);
                        toast.show();
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
}
