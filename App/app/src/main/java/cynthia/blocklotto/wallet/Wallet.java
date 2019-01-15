package cynthia.blocklotto.wallet;

public class Wallet {
    private String id;
    private String pass;
    private String pub;
    private String address;
    private int amount;
    private String[] seedWords;
    private boolean unlock;
    private boolean deleted;

    public Wallet(String id, String pass, String pub, String address, int amount, String [] seedWords, boolean unlock, boolean deleted) {
        this.id = id;
        this.pass = pass;
        this.pub = pub;
        this.address = address;
        this.amount = amount;
        this.seedWords = seedWords;
        this.unlock = unlock;
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String[] getSeedWords() {
        return seedWords;
    }

    public void setSeedWords(String[] seedWords) {
        this.seedWords = seedWords;
    }

    public boolean isUnlock() {
        return unlock;
    }

    public void setUnlock(boolean unlock) {
        this.unlock = unlock;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}