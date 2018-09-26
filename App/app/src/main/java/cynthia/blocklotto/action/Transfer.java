package cynthia.blocklotto.action;

public class Transfer {
    private String origin;
    private String destinity;
    private String amount;

    public Transfer(){}

    public Transfer(String origin, String destinity, String amount){
        this.origin=origin;
        this.destinity=destinity;
        this.amount=amount;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDestinity(String destinity) {
        this.destinity = destinity;
    }

    public String getAmount() {
        return amount;
    }

    public String getDestinity() {
        return destinity;
    }

    public String getOrigin() {
        return origin;
    }

}
