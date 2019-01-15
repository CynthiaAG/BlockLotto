package cynthia.blocklotto.fragment.operation;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import cynthia.blocklotto.R;

import static android.content.Context.CLIPBOARD_SERVICE;

public class Fragment_receipt extends Fragment {

    private TextView addressCurrent;
    private ImageView receivedQR;
    private Button copyAddress;
    private String addressCurrentText;
    private View view;
    private View customToast;
    private LayoutInflater inflater;
    private ViewGroup container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_receipt, container, false);
        this.inflater = inflater;
        this.container = container;

        addressCurrent = view.findViewById(R.id.addressCurrent);
        addressCurrent.setText(getPreferences());
        receivedQR = view.findViewById(R.id.receivedQR);
        copyAddress = view.findViewById(R.id.copyAddress);

        generateQR();
        controlButton();
        return view;
    }

    private String getPreferences(){
        SharedPreferences preference = getContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String pub = preference.getString("pub","-1");
        addressCurrentText = pub;
        return pub;
    }

    private void controlButton(){
        copyAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyToClipboard();
            }
        });
    }

    private void copyToClipboard(){

        ClipData clip = ClipData.newPlainText("addressCurrentText", addressCurrentText);
        ClipboardManager clipboard = (ClipboardManager)view.getContext().getSystemService(CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(clip);

        LayoutInflater inflater = getLayoutInflater();
        customToast = inflater.inflate(R.layout.custom_toast, (ViewGroup) view.findViewById(R.id.custom_toast_container));
        TextView text = (TextView) customToast.findViewById(R.id.textToast);
        text.setText("Dirección copiada");
        Toast toast = new Toast(getContext());
        toast.setView(customToast);
        toast.show();
    }
    private void generateQR() {

        if (addressCurrentText != null) {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(addressCurrentText, BarcodeFormat.QR_CODE, 600, 600);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                receivedQR.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }

        }
    }
}
