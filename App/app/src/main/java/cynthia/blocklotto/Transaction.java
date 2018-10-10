package cynthia.blocklotto;

public class Transaction {

    private String concept;
    private String date;
    private String amount;

    public Transaction() {
        super();
    }

    public Transaction(String concept, String date, String amount) {
        super();
        this.concept = concept;
        this.date = date;
        this.amount = amount;
    }

    public String getConcept() {
        return concept;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
