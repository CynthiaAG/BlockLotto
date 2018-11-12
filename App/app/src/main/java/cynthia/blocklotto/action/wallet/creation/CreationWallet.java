package cynthia.blocklotto.action.wallet.creation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cynthia.blocklotto.R;

public class CreationWallet extends AppCompatActivity {

    private Button create;
    private TextInputEditText pin;
    private String stringPin;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_wallet);
        getSupportActionBar().setTitle("Crear Wallet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        message = findViewById(R.id.messagePIN);
        pin = findViewById(R.id.pin_pass);

        create = findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                stringPin = pin.getText().toString().trim();
                if(TextUtils.isEmpty(stringPin) || stringPin.length()<8) {
                    message.setTextColor(Color.parseColor("#FFE7150A"));
                    message.setError("El PIN debe tener mínimo 8 caracteres.");
                }else {
                    //Process Create Wallet and passing to the createDataFile() the address, pub and id.
                    createDataFile();
                    Intent intent = new Intent(v.getContext(), TwentyFourWords.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                }
            }
        });
    }


    private void createDataFile(){
        SharedPreferences preference = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        //Obtaining the address, pub and id for wallet
        String address = "dirección de prueba";
        String pub = "pub de prueba";
        String id = "id de prueba";

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
