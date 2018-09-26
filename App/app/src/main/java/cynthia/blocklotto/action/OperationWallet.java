package cynthia.blocklotto.action;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import cynthia.blocklotto.R;

public class OperationWallet extends Activity {

    private DisplayMetrics size;
    private Button send;
    private Button received;

    private void initialize(){
        size = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(size);
        send =  (Button) findViewById(R.id.sendOperation);
        received = (Button) findViewById(R.id.receivedOperation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operation_wallet_window);
        initialize();
        setWindowSize();

        received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Se han recibido bitcoins", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Se han enviado bitcoins", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setWindowSize() {
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            getWindow().setLayout( (int) ((size.widthPixels) * .75), (int) ((size.heightPixels) * .35) );
        }
        else{
            getWindow().setLayout( (int) ((size.widthPixels) * .6), (int) ((size.heightPixels) * .6) );
        }
    }

}
