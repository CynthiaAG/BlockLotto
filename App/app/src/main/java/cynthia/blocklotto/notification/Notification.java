package cynthia.blocklotto.notification;

import java.text.DecimalFormat;
import java.util.Locale;

import cynthia.blocklotto.R;

public class Notification {

    private int id;
    private int icon;
    private String concept;
    private String date;
    private double amount;
    private String type;

    public Notification(int id, String concept, String date, double amount, String type){
        this.id = id;
        this.concept = concept;
        this.date = date;
        this.amount = amount;
        setType(type);
    }

    public int getId(){ return id; }

    public int getIcon() {
        return icon;
    }

    public String getDate() {
        return date;
    }

    public String getConcept() {
        return concept;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        //temporary
        if(type.equals("read")){
            icon= R.drawable.ic_notification_open;
        }else{
            icon = R.drawable.ic_notification_close;
        }
    }

    public String getAmountBadge(){
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.0####");
        String result = num.format(amount);
        return result + " BTC";
    }
}
