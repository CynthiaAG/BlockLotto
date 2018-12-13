package cynthia.blocklotto.action.lottery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import cynthia.blocklotto.R;
import cynthia.blocklotto.action.lottery.BuyLottery;

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
    private LinearLayout participationsGroup;
    private TextView participations;
    private String type;
    private String name;
    private String date;
    private String price;
    private double priceDouble;
    private int id;
    private String award;
    private String text;
    private int amount;
    private String priceFinal;
    private int totalParticipations;
    private int currentParticipations;


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
        participations = findViewById(R.id.totalParticipations);
        participationsGroup = findViewById(R.id.participationsGroup);
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
        award = (String) getIntent().getExtras().getSerializable("award");
        date = (String) getIntent().getExtras().getSerializable("date");
        price = (String) getIntent().getExtras().getSerializable("priceBadge");
        totalParticipations = (int) getIntent().getExtras().getSerializable("totalParticipations");
        currentParticipations = (int) getIntent().getExtras().getSerializable("currentParticipations");

        if(!type.equals("Next")){
            priceFinal = (String) getIntent().getExtras().getSerializable("priceFinal");
            amount = (int) getIntent().getExtras().getSerializable("amount");
        }else{
            priceDouble = (double) getIntent().getExtras().getSerializable("price");
            if(totalParticipations == currentParticipations){
                buy.setEnabled(false);
                buy.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.text_color_quinary)));
            }
        }

    }

    private void setWindowText(){
        title.setText(name);
        textInfo.setText(text);
        botInfoLotteryAmount.setText(award);
        priceLotteryAmount.setText(price);
        dateInfo.setText(date);

        if(!type.equals("Next")){
            if(type.equals("Archived")){
                participations.setText(currentParticipations+"");
            }
            priceTotalInfo.setText(priceFinal);
            amountTicketInfo.setText(amount+"");
        }

    }

    private void setWindowSize(String type) {
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT && (type.equals("Pending"))){
            getWindow().setLayout( (int) ((size.widthPixels) * .8), (int) ((size.heightPixels) * .45) );
            establishViews(View.INVISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE);
        }else if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT && (type.equals("Archived"))){
            getWindow().setLayout( (int) ((size.widthPixels) * .8), (int) ((size.heightPixels) * .5) );
            establishViews(View.VISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE);
        }
        else if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT && type.equals("Next")){
            getWindow().setLayout( (int) ((size.widthPixels) * .8), (int) ((size.heightPixels) * .45) );
            establishViews(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
        }
        else if (type.equals("Next")){
            getWindow().setLayout( (int) ((size.widthPixels) * .6), (int) ((size.heightPixels) * .8) );
            establishViews(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
        }
        else if (type.equals("Archived")){
            getWindow().setLayout( (int) ((size.widthPixels) * .6), (int) ((size.heightPixels) * .88) );
            establishViews(View.VISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE);
        }
        else{
            getWindow().setLayout( (int) ((size.widthPixels) * .6), (int) ((size.heightPixels) * .83) );
            establishViews(View.INVISIBLE, View.VISIBLE, View.VISIBLE, View.INVISIBLE);
        }
    }

    private void establishViews(int i1, int i2, int i3, int i4){
        participationsGroup.setVisibility(i1);
        amountTicketGroup.setVisibility(i2);
        priceTotalGroup.setVisibility(i3);
        buy.setVisibility(i4);
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
                intent.putExtra("price", priceDouble);
                c.startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });;
    }
}
