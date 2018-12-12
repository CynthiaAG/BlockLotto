package cynthia.blocklotto.action.wallet.creation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cynthia.blocklotto.R;
import cynthia.blocklotto.conection.Conection;
import cynthia.blocklotto.conection.ConectionResponse;

public class CreationWallet extends AppCompatActivity{

    private Button create;
    private TextInputEditText pin;
    private String stringPin;
    private TextView message;
    private String [] data;

    private View customToast;
    private LayoutInflater inflater;
    private TextView text;


    private void initialize(){
        setContentView(R.layout.activity_creation_wallet);
        getSupportActionBar().setTitle("Crear Wallet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        data = null;

        inflater = getLayoutInflater();
        customToast = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));
        text = (TextView) customToast.findViewById(R.id.textToast);
        text.setText("No se ha podido crear el wallet. Revise su conexión a internet.");

        message = findViewById(R.id.messagePIN);
        pin = findViewById(R.id.pin_pass);

        create = findViewById(R.id.create);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        create.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                stringPin = pin.getText().toString().trim();
                if(TextUtils.isEmpty(stringPin) || stringPin.length()<8) {
                    message.setTextColor(Color.parseColor("#FFE7150A"));
                    message.setError("El PIN debe tener mínimo 8 caracteres.");
                }else {
                    create.setEnabled(false);
                    controlCreationWallet(v);

                }
            }
        });
    }

    private void controlCreationWallet(View v){
        Conection con = new Conection();
        con.createWallet(stringPin, getBaseContext());

        //0-id, 1-pub, 2-address, 3-24words
        String [] data = con.getResultCreateWallet();
        if(data==null){
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(customToast);
            toast.show();
            create.setEnabled(true);
        }else {
            System.out.println("HElloooow");
            createDataFile(data[0], data[1], data[2]);
            Intent intent = new Intent(v.getContext(), TwentyFourWords.class);
            intent.putExtra("24-words", data[3]);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }

    private void createDataFile(String id, String pub, String address){
        SharedPreferences preference = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        //Obtaining the address, pub and id for wallet

        SharedPreferences.Editor editor = preference.edit();
        editor.putString("address", address);
        //exist putInt(), etc.
        editor.putString("pub", pub);
        editor.putString("id", id);
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        super.onBackPressed();
        return false;
    }
}
