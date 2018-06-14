package cynthia.blocklotto;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Cynthia on 13/06/2018.
 */

public class BuyLottery extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_window);

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

        Button buy = findViewById(R.id.buyFinish);
        Button cancel = findViewById(R.id.cancelBuy);

        numPickerControl();

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Hacer el proceso de compra

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast,
                        (ViewGroup) findViewById(R.id.custom_toast_container));

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("Se ha realizado la compra éxitosamente");

                Toast toast = new Toast(getApplicationContext());
                toast.setView(layout);
                toast.show();

                finish();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

    public void numPickerControl(){
        NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);

        final TextView price = findViewById(R.id.totalPrice);
        String amount = price.getText().toString().trim();

        //Quitamos la b de bitcoin del precio
        amount = amount.substring(0, amount.length() - 2);
        final Double priceTotal= Double.parseDouble(amount);

        np.setMaxValue(500);
        np.setMinValue(1);
        np.setWrapSelectorWheel(true);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Acción al seleccionar el numero
                double d= picker.getValue() * priceTotal;
                price.setText("" +  d);


            }
        });
    }
}
