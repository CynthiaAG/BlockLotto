package cynthia.blocklotto.action;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import cynthia.blocklotto.MainActivity;
import cynthia.blocklotto.R;

public class RecuperateWallet extends AppCompatActivity {

    Button recuperate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperate_wallet);

        getSupportActionBar().setTitle("Recuperar Wallet");

        recuperate= findViewById(R.id.recuperate);

        recuperate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
