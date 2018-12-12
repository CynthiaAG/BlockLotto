package cynthia.blocklotto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.gms.ads.MobileAds;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkDataFile();
            }
        }, 3970);


    }

    private void checkDataFile(){
        SharedPreferences preference = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String address = preference.getString("address", "-1");
        String pub = preference.getString("pub","-1");
        String id = preference.getString("id","-1");
        if((!address.equals("-1")) && (!pub.equals("-1")) && (!id.equals("-1"))){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
        }else{
            Intent intent = new Intent(getApplicationContext(), Start.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
        }
    }
}
