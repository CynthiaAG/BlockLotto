package cynthia.blocklotto.action.wallet.creation;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import cynthia.blocklotto.MainActivity;
import cynthia.blocklotto.R;

public class TwentyFourWords extends AppCompatActivity {

    private Button okContinue;
    private Button help;
    private TextView helpText;
    private TextView twentyFourWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_twentyfourwords_creation);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        getSupportActionBar().setTitle("24 palabras de seguridad");

        okContinue = findViewById(R.id.buttonOk);
        help = findViewById(R.id.help24wordsButton);
        helpText = findViewById(R.id.help24words);
        twentyFourWords = findViewById(R.id.twentyFourWords);
        //Establish 24 words in twentyFourWords

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(helpText.getVisibility() == View.VISIBLE){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                    helpText.setVisibility(View.INVISIBLE);
                    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                    helpText.startAnimation(anim);

                }else if(helpText.getVisibility() == View.INVISIBLE){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                    helpText.startAnimation(anim);
                    helpText.setVisibility(View.VISIBLE);
                }
            }
        });
        okContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }


}
