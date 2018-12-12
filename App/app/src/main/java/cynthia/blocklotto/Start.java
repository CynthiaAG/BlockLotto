package cynthia.blocklotto;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cynthia.blocklotto.action.wallet.creation.CreationWallet;
import cynthia.blocklotto.action.wallet.recuperation.TwentyFourWords;
import cynthia.blocklotto.conection.Conection;

public class Start extends AppCompatActivity{
    private Button createWallet;
    private Button recuperarWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        createWallet = findViewById(R.id.crear_wallet);
        recuperarWallet= findViewById(R.id.recuperar_wallet);

        createWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreationWallet.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });

        recuperarWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),TwentyFourWords.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
    }
}
