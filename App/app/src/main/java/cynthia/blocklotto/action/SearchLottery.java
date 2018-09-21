package cynthia.blocklotto.action;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import cynthia.blocklotto.R;

/**
 * Created by Cynthia on 15/06/2018.
 */

public class SearchLottery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_window);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        final int width = dm.widthPixels;
        final int height= dm.heightPixels;

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            getWindow().setLayout( (int) (width * .8), (int) (height * .45) );
        }
        else{
            getWindow().setLayout( (int) (width * .6), (int) (height * .75) );
        }

    }
}
