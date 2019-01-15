package cynthia.blocklotto.action.lottery;

public class Purchase {
    private String idWallet;
    private int codRaffle;
    private int number;
    private String message;
    private String [] tickets;

    public Purchase(String idWallet, int codRaffle, int number, String message, String[] tickets) {
        this.idWallet = idWallet;
        this.codRaffle = codRaffle;
        this.number = number;
        this.message = message;
        this.tickets = tickets;
    }

    public String getIdWallet() {
        return idWallet;
    }

    public void setIdWallet(String idWallet) {
        this.idWallet = idWallet;
    }

    public int getCodRaffle() {
        return codRaffle;
    }

    public void setCodRaffle(int codRaffle) {
        this.codRaffle = codRaffle;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getTickets() {
        return tickets;
    }

    public void setTickets(String[] tickets) {
        this.tickets = tickets;
    }
}
