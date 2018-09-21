package cynthia.blocklotto.action;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Locale;

import cynthia.blocklotto.R;

/**
 * Created by Cynthia on 13/06/2018.
 */

public class BuyLottery extends Activity {

    private DisplayMetrics size;
    private Button buy;
    private Button cancel;
    private NumberPicker numberPicker;

    private void initialize(){
        size = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(size);
        buy = findViewById(R.id.buyFinishButton);
        cancel = findViewById(R.id.cancelBuyButton);
        numberPicker = (NumberPicker) findViewById(R.id.numberPickerBuy);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_window);
        initialize();
        setWindowSize();
        numPickerControl();
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Buy process
                buyTicket();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setWindowSize() {
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            getWindow().setLayout( (int) ((size.widthPixels) * .8), (int) ((size.heightPixels) * .45) );
        }
        else{
            getWindow().setLayout( (int) ((size.widthPixels) * .6), (int) ((size.heightPixels) * .75) );
        }
    }

    private void buyTicket(){
        //Show confirmation buy toast
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.textToast);
        text.setText("Se ha realizado la compra éxitosamente");

        Toast toast = new Toast(getApplicationContext());
        toast.setView(layout);
        toast.show();
        finish();
    }

    public void numPickerControl(){
        final TextView price = findViewById(R.id.totalPrice);
        String amount = price.getText().toString().trim();

        //Remove the badge
        amount = amount.substring(0, amount.length() - 2);
        final Double priceTotal= Double.parseDouble(amount);

        numberPicker.setMaxValue(500);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(true);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
             public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Price calculation
                double finalPrice= picker.getValue() * priceTotal;
                price.setText(convertString(finalPrice)+ " B");


            }
        });
    }

    //Transform the double to String without scientific notation
    public static String convertString(double value){
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.00000");
        return num.format(value);
    }
    }
