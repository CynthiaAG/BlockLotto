package cynthia.blocklotto.action.wallet.recuperation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cynthia.blocklotto.start.MainActivity;
import cynthia.blocklotto.R;
import cynthia.blocklotto.conection.Conection;

public class RecuperationWallet extends AppCompatActivity {

    private Button recuperate;
    private TextView message;
    private EditText pin;
    private String pass;
    private View view;


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
                pass = pin.getText().toString().trim();
                if(TextUtils.isEmpty(pass) || pass.length()<8) {
                    message.setTextColor(Color.parseColor("#FFE7150A"));
                    message.setError("El PIN debe tener mínimo 8 caracteres.");
                }else {
                    //Process Recuperate Wallet and passing to the createDataFile() the address, pub and id.
                    recuperate.setEnabled(false);
                    String twentyFourWordsString = (String) getIntent().getExtras().getSerializable("24-wordsRecuperate");
                    view = v;
                    controlRecuperationWallet(pass, convertTwentyFourWordsToJSON(twentyFourWordsString));

                }
            }
        });
    }

    private String convertTwentyFourWordsToJSON(String twentyWords){
        String[] res= twentyWords.split(" ");
        String result="";
        for (int i = 0; i < res.length; i++) {
            result = result + "\"" + res[i] + "\"" + "," + " ";
        }
        result= "[" + result.substring(0, result.length()-2) + "]";
        return result;
    }

    private void controlRecuperationWallet(String pass, String twentyFourWords){
        Conection con = new Conection();
        con.recuperateWallet(pass, twentyFourWords, getBaseContext());

        //0 id, 1 pub, 2 address
        String [] data = con.getResultRecuperateWallet();
        if(data != null){
            createDataFile(data[0], data[1], data[2]);
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
        }else{
            LayoutInflater inflater = getLayoutInflater();
            View customToast= inflater.inflate(R.layout.custom_toast,
                    (ViewGroup) findViewById(R.id.custom_toast_container));
            TextView text = (TextView) customToast.findViewById(R.id.textToast);
            text.setText("Las 24 palabras de seguridad no coinciden con ningún wallet. Inténtelo de nuevo.");
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(customToast);
            toast.show();

            Intent intent = new Intent(view.getContext(), cynthia.blocklotto.action.wallet.recuperation.TwentyFourWords.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
        }
    }

    private void createDataFile(String id, String pub, String address){
        SharedPreferences preference = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

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
