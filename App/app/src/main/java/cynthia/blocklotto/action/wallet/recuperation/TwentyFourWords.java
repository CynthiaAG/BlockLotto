package cynthia.blocklotto.action.wallet.recuperation;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cynthia.blocklotto.R;

import static java.security.AccessController.getContext;

public class TwentyFourWords extends AppCompatActivity {

    private EditText twentyFourWords;
    private Button continueRestore;
    private String stringTwentyFourWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twentyfourwords_recuperation);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        getSupportActionBar().setTitle("24 palabras de seguridad");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        twentyFourWords = findViewById(R.id.twentyFourWordsIntroduced);
        continueRestore = findViewById(R.id.buttonOkRecuperation);

        continueRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringTwentyFourWords = twentyFourWords.getText().toString();
                if (TextUtils.isEmpty(stringTwentyFourWords)) {
                    twentyFourWords.setError("Debe introducir las veinticuatro palabras de seguridad.");
                }else{
                    //Check that 24 words exist for one wallet.
                    checkTwentyFourWords(view);
                }

            }
        });
    }

    private void checkTwentyFourWords(View view){
        if(stringTwentyFourWords.length()< 8 && (! stringTwentyFourWords.contains(" "))) {
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Error").setIcon(R.drawable.ic_error)
                    .setMessage("Las veinticuatro palabras introducidas no coinciden con ningún wallet. Compruebe que las ha escrito en el orden correcto y con un espacio entre cada palabra.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    }).show();
        }else{
            Intent intent = new Intent(view.getContext(),RecuperationWallet.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        super.onBackPressed();
        return false;
    }
}
