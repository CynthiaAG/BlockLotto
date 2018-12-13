package cynthia.blocklotto.action.lottery;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import cynthia.blocklotto.conection.ResultFromJson;
import cynthia.blocklotto.conection.Conection;
import cynthia.blocklotto.conection.ConectionResponse;

/**
 * Created by Cynthia on 13/06/2018.
 */

public class BuyLottery extends Activity implements ConectionResponse {

    private DisplayMetrics size;
    private Button buy;
    private Button cancel;
    private NumberPicker numberPicker;

    private int amountTicket;
    private String priceFinal;
    private TextView priceTextView;
    //Lottery
    private int id;
    private double price;

    private View customToastInternet;
    private LayoutInflater inflaterInternet;
    private TextView textInternet;
    private View customToast;
    private LayoutInflater inflater;
    private TextView text;
    private View customToastError;
    private LayoutInflater inflaterError;
    private TextView textError;


    private void initialize(){
        size = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(size);
        buy = findViewById(R.id.buyFinishButton);
        cancel = findViewById(R.id.cancelBuyButton);
        numberPicker = (NumberPicker) findViewById(R.id.numberPickerBuy);
        priceTextView = findViewById(R.id.totalPrice);

        id = (int) getIntent().getExtras().getSerializable("id");
        price = (double) getIntent().getExtras().getSerializable("price");
        priceFinal = convertString(price) + " BTC";

        amountTicket = 1;
        priceTextView.setText(priceFinal);

        initializeToast();
    }

    private void initializeToast(){
        inflaterInternet = getLayoutInflater();
        customToastInternet = inflaterInternet.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));
        textInternet = (TextView) customToastInternet.findViewById(R.id.textToast);
        textInternet.setText("Revise su conexión a internet.");

        inflater = getLayoutInflater();
        customToast = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        text = (TextView) customToast.findViewById(R.id.textToast);
        text.setText("Se ha realizado la compra éxitosamente.");

        inflaterError = getLayoutInflater();
        customToastError = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        textError = (TextView) customToastError.findViewById(R.id.textToast);
        textError.setText("No se ha podido realizar la compra.");
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
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this , R.style.MyDialogTheme);
        builder.setTitle("Comprar boleto")
                .setMessage("¿Estas seguro que quieres comprar " + amountTicket + " boleto(s)?, serán " + priceFinal)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        buyRaffle(id, amountTicket);
                        finish();

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(R.drawable.ic_warning)
                .show();
    }

    private void buyRaffle(int id, int amountTicket){
        Conection con = new Conection();
        con.conectionResponse=this;
        con.buyLottery(id, amountTicket, getBaseContext());
    }

    public void numPickerControl(){
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(true);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
             public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Price calculation
                amountTicket = picker.getValue();
                double finalPrice= amountTicket * price;
                priceFinal = convertString(finalPrice) + " BTC";
                priceTextView.setText(priceFinal);
            }
        });
    }

    //Transform the double to String without scientific notation
    public static String convertString(double value){
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.0#####");
        return num.format(value);
    }

    @Override
    public void processFinish(String output) {
        ResultFromJson resultFromJson = new ResultFromJson();

        if(output ==null){
            Toast toast = new Toast(getBaseContext());
            toast.setView(customToastInternet);
            toast.show();
        }else {
            String result = resultFromJson.getBuyRaffleResult(output);
            if (result != null && result.equals("Transaction completed")) {
                Toast toast = new Toast(getApplicationContext());
                toast.setView(customToast);
                toast.show();

            } else {
                Toast toast = new Toast(getApplicationContext());
                toast.setView(customToastError);
                toast.show();
            }
        }

    }
}
