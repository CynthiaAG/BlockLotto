package cynthia.blocklotto.fragment.operation;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.DecimalFormat;
import java.util.Locale;

import cynthia.blocklotto.R;

public class Fragment_request extends Fragment {

    private View view;
    private EditText amountRequest;
    private Button generateQRButton;
    private ImageView imageQR;
    private String addressCurrentText;
    private String request;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_request, container, false);
        amountRequest = view.findViewById(R.id.amountRequest);
        generateQRButton = view.findViewById(R.id.generateQR);
        imageQR = view.findViewById(R.id.requestQR);
        imageQR.setVisibility(View.INVISIBLE);

        addressCurrentText = getPreferences();

        generateQRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formatAmount();
                generateQR();
            }
        });
        return view;
    }

    private String getPreferences(){
        SharedPreferences preference = getContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String pub = preference.getString("pub","-1");
        return pub;
    }

    private void formatAmount(){
        Double amount = Double.parseDouble(amountRequest.getText().toString());
        if( (amount != null) && (!amount.equals("0")) && (!amount.equals("0.0"))) {
            Locale.setDefault(Locale.US);
            DecimalFormat num = new DecimalFormat("###0.0#####");
            String res = num.format(amount);
            amountRequest.setText(res);
        }
    }

    private void generateQR() {
        if(TextUtils.isEmpty(amountRequest.getText()) || (amountRequest.getText().toString().equals("0")) ||(amountRequest.getText().toString().equals("0.0")) ){
            amountRequest.setError("Introduzca la cantidad\nque desea solicitar.");
        }else {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                request = addressCurrentText + "," + amountRequest.getText();
                BitMatrix bitMatrix = multiFormatWriter.encode(request, BarcodeFormat.QR_CODE, 600, 600);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                imageQR.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
            imageQR.setVisibility(View.VISIBLE);

        }
    }
}
