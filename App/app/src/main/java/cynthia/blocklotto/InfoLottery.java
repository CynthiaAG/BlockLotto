package cynthia.blocklotto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import cynthia.blocklotto.action.BuyLottery;

public class InfoLottery  extends Activity {

    private DisplayMetrics size;
    private Button buy;
    private Button close;
    private TextView title;
    private TextView textInfo;
    private TextView botInfoLotteryAmount;
    private TextView priceLotteryAmount;
    private TextView dateInfo;
    private LinearLayout amountTicketGroup;
    private LinearLayout priceTotalGroup;
    private TextView amountTicketInfo;
    private TextView priceTotalInfo;
    private String type;
    private String name;
    private String date;
    private String price;
    private int id;
    private String bot;
    private String text;
    private int amount;
    private String priceFinal;


    private void initialize(){
        size = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(size);
        buy = findViewById(R.id.buyInfoButton);
        close = findViewById(R.id.closeInfoLottery);
        title = findViewById(R.id.lotteryTitle);
        textInfo = findViewById(R.id.textInfo);
        botInfoLotteryAmount = findViewById(R.id.botInfoLotteryAmount);
        priceLotteryAmount = findViewById(R.id.priceLotteryAmount);
        dateInfo = findViewById(R.id.dateInfo);
        amountTicketGroup = findViewById(R.id.amountTicketGroup);
        priceTotalGroup = findViewById(R.id.priceTotalGroup);
        amountTicketInfo = findViewById(R.id.amountTicketInfo);
        priceTotalInfo = findViewById(R.id.priceTotalInfo);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_lottery_window);
        initialize();
        setElements();
        setWindowText();
        setWindowSize(type);
        controlButton();
    }

    private void setElements(){
        type = (String) getIntent().getExtras().getSerializable("type");
        id = (int) getIntent().getExtras().getSerializable("id");
        name= (String) getIntent().getExtras().getSerializable("name");
        text = (String) getIntent().getExtras().getSerializable("info");
        bot = (String) getIntent().getExtras().getSerializable("bot");
        date = (String) getIntent().getExtras().getSerializable("date");
        price = (String) getIntent().getExtras().getSerializable("price");

        if(!type.equals("Next")){
            priceFinal = (String) getIntent().getExtras().getSerializable("priceFinal");
            amount = (int) getIntent().getExtras().getSerializable("amount");
        }

    }

    private void setWindowText(){
        title.setText(name);
        textInfo.setText(text);
        botInfoLotteryAmount.setText(bot);
        priceLotteryAmount.setText(price);
        dateInfo.setText(date);

        if(!type.equals("Next")){
            priceTotalInfo.setText(priceFinal);
            amountTicketInfo.setText(amount+"");
        }

    }

    private void setWindowSize(String type) {
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT && (type.equals("Pending") || type.equals("Archived"))){
            getWindow().setLayout( (int) ((size.widthPixels) * .8), (int) ((size.heightPixels) * .45) );
            amountTicketGroup.setVisibility(View.VISIBLE);
            priceTotalGroup.setVisibility(View.VISIBLE);
            buy.setVisibility(View.INVISIBLE);
        }
        else if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT && type.equals("Next")){
            getWindow().setLayout( (int) ((size.widthPixels) * .8), (int) ((size.heightPixels) * .45) );
            amountTicketGroup.setVisibility(View.INVISIBLE);
            priceTotalGroup.setVisibility(View.INVISIBLE);
            buy.setVisibility(View.VISIBLE);
        }
        else if (type.equals("Next")){
            getWindow().setLayout( (int) ((size.widthPixels) * .6), (int) ((size.heightPixels) * .8) );
            amountTicketGroup.setVisibility(View.INVISIBLE);
            priceTotalGroup.setVisibility(View.INVISIBLE);
            buy.setVisibility(View.VISIBLE);
        }
        else{
            getWindow().setLayout( (int) ((size.widthPixels) * .6), (int) ((size.heightPixels) * .83) );
            amountTicketGroup.setVisibility(View.VISIBLE);
            priceTotalGroup.setVisibility(View.VISIBLE);
            buy.setVisibility(View.INVISIBLE);
        }
    }

    private void controlButton(){

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Context c = view.getContext();
                Intent intent = new Intent(c , BuyLottery.class);
                intent.putExtra("id", id);
                intent.putExtra("price", price);
                c.startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });;
    }
}
