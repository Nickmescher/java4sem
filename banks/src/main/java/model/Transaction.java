package model;

public class Transaction {

    private BankAccount bAFrom;
    private BankAccount bATo;
    private double money;
    private boolean notCancelling;

    public Transaction(BankAccount bAFrom, BankAccount bATo, double money){
        this.bAFrom = bAFrom;
        this.bATo = bATo;
        this.notCancelling = true;

    }


    public BankAccount getbAFrom() {
        return bAFrom;
    }

    public void setbAFrom(BankAccount bAFrom) {
        this.bAFrom = bAFrom;
    }

    public BankAccount getbATo() {
        return bATo;
    }

    public void setbATo(BankAccount bATo) {
        this.bATo = bATo;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public boolean isNotCancelling() {
        return notCancelling;
    }

    public void setNotCancelling() {
        this.notCancelling = false;
    }


}
