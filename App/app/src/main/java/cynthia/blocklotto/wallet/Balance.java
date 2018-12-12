package cynthia.blocklotto.wallet;

import java.text.DecimalFormat;
import java.util.Locale;

public class Balance {
    private String total_balance;
    private String confirmed_balance;

    private String unconfirmed_balance;

    public Balance(String totalBalance, String confirmed_balance, String unconfirmed_balance) {
        this.total_balance = totalBalance;
        this.confirmed_balance = confirmed_balance;
        this.unconfirmed_balance = unconfirmed_balance;
    }

    public String getTotal_balance() {
        if(total_balance!=null){
            double aux = Double.parseDouble(total_balance);
            Locale.setDefault(Locale.US);
            DecimalFormat num = new DecimalFormat("#,##0.0#####");
            return num.format(aux);
        }else{
            return null;
        }
    }

    public void setTotalBalance(String totalBalance) {
        this.total_balance = totalBalance;
    }

    public String getConfirmed_balance() {
        return confirmed_balance;
    }

    public void setConfirmed_balance(String confirmed_balance) {
        this.confirmed_balance = confirmed_balance;
    }

    public String getUnconfirmed_balance() {
        return unconfirmed_balance;
    }

    public void setUnconfirmed_balance(String unconfirmed_balance) {
        this.unconfirmed_balance = unconfirmed_balance;
    }
}
