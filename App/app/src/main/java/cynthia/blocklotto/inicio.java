package cynthia.blocklotto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class inicio extends AppCompatActivity{
    Button createWallet;
    Button recuperarWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        createWallet = findViewById(R.id.crear_wallet);
        recuperarWallet= findViewById(R.id.recuperar_wallet);

        createWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),createWallet.class);
                startActivity(intent);
            }
        });

        recuperarWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),recuperarWallet.class);
                startActivity(intent);
            }
        });




    }
}
