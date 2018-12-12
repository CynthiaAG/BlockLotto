package cynthia.blocklotto.action.wallet.recuperation;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cynthia.blocklotto.R;
import cynthia.blocklotto.Start;

import static android.view.KeyEvent.KEYCODE_BACK;
import static java.security.AccessController.getContext;

public class TwentyFourWords extends AppCompatActivity {

    private TextInputEditText twentyFourWords;
    private Button continueRestore;
    private String stringTwentyFourWords;
    private TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twentyfourwords_recuperation);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        getSupportActionBar().setTitle("24 palabras de seguridad");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        twentyFourWords = findViewById(R.id.twentyFourWordsIntroduced);
        textInputLayout = findViewById(R.id.twentyFourWordsTextInput);
        continueRestore = findViewById(R.id.buttonOkRecuperation);

        continueRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringTwentyFourWords = twentyFourWords.getText().toString().trim();
                if ((TextUtils.isEmpty(stringTwentyFourWords)) || (! stringTwentyFourWords.contains(" ")) || (! controlSpacesWords())) {
                    textInputLayout.setError("Debe introducir las veinticuatro palabras de seguridad.");
                }else{
                    Intent intent = new Intent(view.getContext(),RecuperationWallet.class);
                    intent.putExtra("24-wordsRecuperate",  stringTwentyFourWords.replaceAll("\\s+", " "));
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                }

            }
        });
    }

    private boolean controlSpacesWords(){
        if(stringTwentyFourWords != null){
            String res = stringTwentyFourWords.replaceAll("\\s+", " ");
            String [] result= res.split(" ");
            if(result.length == 24){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        onBackPressed();
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getBaseContext(), Start.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
