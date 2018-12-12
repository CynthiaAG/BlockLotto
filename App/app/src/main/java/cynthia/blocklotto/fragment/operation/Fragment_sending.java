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

import java.text.DecimalFormat;
import java.util.Locale;

import cynthia.blocklotto.R;
import cynthia.blocklotto.ResultFromJson;
import cynthia.blocklotto.conection.Conection;
import cynthia.blocklotto.conection.ConectionResponse;

public class Fragment_sending extends Fragment implements ConectionResponse {

    private View view;

    private Button sendButtonFinal;
    private EditText amountSend;
    private EditText addressReception;
    private String addressScan;
    private String amount;

    private View customToast;
    private View customToastError;
    private LayoutInflater inflater;
    private LayoutInflater inflaterError;
    private TextView text;
    private TextView textError;


    private View customToastInternet;
    private LayoutInflater inflaterInternet;
    private TextView textInternet;

    private String stringScan;
    private final double satoshi = 0.00000001;

    public void initialize(){
        sendButtonFinal = view.findViewById(R.id.sendButtonFinal);
        addressReception = view.findViewById(R.id.addressReception);
        amountSend = view.findViewById(R.id.amountSend);

        inflaterInternet = getLayoutInflater();
        customToastInternet = inflaterInternet.inflate(R.layout.custom_toast, (ViewGroup) view.findViewById(R.id.custom_toast_container));
        textInternet = (TextView) customToastInternet.findViewById(R.id.textToast);
        textInternet.setText("Revise su conexión a internet.");

        inflater = getLayoutInflater();
        customToast = inflater.inflate(R.layout.custom_toast, (ViewGroup) view.findViewById(R.id.custom_toast_container));
        text = (TextView) customToast.findViewById(R.id.textToast);
        text.setText("BTC enviados");

        inflaterError = getLayoutInflater();
        customToastError = inflater.inflate(R.layout.custom_toast, (ViewGroup) view.findViewById(R.id.custom_toast_container));
        TextView textError = (TextView) customToastError.findViewById(R.id.textToast);
        textError.setText("No se han podido enviar BTC");

        stringScan = "";

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sending, container, false);
        initialize();
        controlButton();
        return view;
    }

    public void controlScan(){
        try {
            stringScan = getArguments().getString("QR_SCAN");
            if(stringScan!=null && (!stringScan.equals("")) && (!stringScan.isEmpty())) {
              //  stringScan = getArguments().getString("QR_SCAN");
                for (int i = 0; i < stringScan.length(); i++) {
                    if(i != stringScan.length()-1) {
                        if (stringScan.charAt(i) == ',') {
                            addressScan = stringScan.substring(0, i);
                            amount = stringScan.substring(i + 1);
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
        if((!amount.isEmpty())){
            formatAmount();
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(getContext(), R.style.MyDialogTheme);
            builder.setTitle("Enviar BTC")
                    .setMessage("¿Estas seguro que quieres enviar " + amount + " BTC a la dirección: " + addressScan + "?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                           sendBTC();
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
        } else if((TextUtils.isEmpty(amount)) || (amount.equals("0")) || (amount.equals("0.0"))) {
            amountSend.setError("Introduzca la cantidad\n que desea enviar");
        } else if( TextUtils.isEmpty(addressScan)){
            addressReception.setError("Introduzca la dirección\n de recepción");
        } else{
           showAlertManualSend();
        }
    }

    private void showAlertManualSend(){
        formatAmount();
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext(), R.style.MyDialogTheme);
        builder.setTitle("Enviar BTC")
                .setMessage("Estas seguro que quieres enviar " + amount + " BTC a la dirección: " + addressScan)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sendBTC();
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

    private void formatAmount(){
        Double amountD = Double.parseDouble(amount);
        if( (amountD != null) && (!amountD.equals("0")) && (!amountD.equals("0.0"))) {
            Locale.setDefault(Locale.US);
            DecimalFormat num = new DecimalFormat("###0.0#####");
            amount = num.format(amountD);
            amountSend.setText(amount);
        }
    }

    private void sendBTC(){
        Conection con = new Conection();
        con.conectionResponse=this;

        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("###0.0#####");
        double amountD = Double.parseDouble(amount);
        String aux = num.format(amountD / satoshi);
        amountD = Double.parseDouble(aux);
        con.sendBTC(getContext(), addressScan, amountD);
    }

    @Override
    public void processFinish(String output) {
        ResultFromJson resultFromJson = new ResultFromJson();
        if(output == null){
            Toast toast = new Toast(getContext());
            toast.setView(customToastInternet);
            toast.show();
        }else {
            String aux = resultFromJson.sendBTCResult(output);
            if (aux == null || aux.equals("") || aux.equals("\"Error\"")) {
                Toast toast = new Toast(getContext());
                toast.setView(customToastError);
                toast.show();
            } else {
                addressReception.getText().clear();
                amountSend.getText().clear();
                Toast toast = new Toast(getContext());
                toast.setView(customToast);
                toast.show();
            }
        }
    }
}
