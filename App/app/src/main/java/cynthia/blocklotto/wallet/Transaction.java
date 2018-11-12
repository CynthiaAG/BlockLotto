package cynthia.blocklotto.wallet;

import java.text.DecimalFormat;
import java.util.Locale;

public class Transaction {

    private String concept;
    private String date;
    private String type;
    private double amount;
    private double balanceCurrent;

    public Transaction() {
        super();
    }

    public Transaction(String concept, String date, String type, double amount, double balanceCurrent) {
        super();
        this.concept = concept;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balanceCurrent = balanceCurrent;
    }

    public String getType() {
        return type;
    }

    public String getConcept() {
        return concept;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceCurrent() {
        return balanceCurrent;
    }

    public String getAmountType(){
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("#,##0.0####");
        String result = num.format(amount);

        if(type.equals("income")){
            return "+ " + result+ " BTC";
        }else{
            return "- " + result+ " BTC";
        }
    }

    public String getBalanceCurrentBadge(){
        return balanceCurrent + " BTC";
    }
}
