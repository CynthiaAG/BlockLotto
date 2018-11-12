package cynthia.blocklotto.action.wallet.recuperation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cynthia.blocklotto.MainActivity;
import cynthia.blocklotto.R;
import cynthia.blocklotto.action.wallet.creation.TwentyFourWords;

public class RecuperationWallet extends AppCompatActivity {

    private Button recuperate;
    private TextView message;
    private EditText pin;
    private String twentyFourString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperation_wallet);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recuperar Wallet");

        message = findViewById(R.id.messagePIN_recuperate);
        pin = findViewById(R.id.pin_passRecuperate);

        recuperate = findViewById(R.id.recuperateButton);

        recuperate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twentyFourString = pin.getText().toString().trim();
                if(TextUtils.isEmpty(twentyFourString) || twentyFourString.length()<8) {
                    message.setTextColor(Color.parseColor("#FFE7150A"));
                    message.setError("El PIN debe tener mínimo 8 caracteres.");
                }else {
                    //Process Recuperate Wallet and passing to the createDataFile() the address, pub and id.
                    createDataFile();
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
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
