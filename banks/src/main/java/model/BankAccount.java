package model;

public class BankAccount {

    public Client client;
    public Bank bank;
    public double creditLimit;
    public double money;
    public String type;
    public double limit;
    public int days;
    public double moneyForRepl;
    public int period;
    public Account account;

    public BankAccount(Client client, Bank bank, String type, double money){
        this.client = client;
        this.creditLimit = bank.getCreditLimit();
        this.bank = bank;
        this.limit = bank.getLimitDoubful();
        this.type = type;
        this.money = money;
        this.days = 0;
        this.moneyForRepl = 0.0;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void withdrawal(double amount){
        this.money -= amount;
    }

    public void replenishment(double amount){
        this.money += amount;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


    public int getDays() {
        return days;
    }

    public void decreaseDays(int days) {
        this.days -= days;
    }

    public void addDays(int days) {
        this.days += days;
    }


    public double getMoneyForRepl() {
        return moneyForRepl;
    }

    public void setMoneyForRepl(double moneyForRepl) {
        this.moneyForRepl = moneyForRepl;
    }

    public void addMoneyForRepl(double moneyForRepl) {
        this.moneyForRepl += moneyForRepl;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void decreasePeriod(int period) {
        this.period -= period;
    }

    public void setAccountType(String type){
        if (type.equals("deposit")){
            this.account = new DepositAccount();
        } else if (type.equals("credit")){
            this.account = new CreditAccount();
        } else {
            this.account = new DebetAccount();
        }
    }

    public String getType() {
        return type;
    }

    public Account getAccountType() {
        if (getType().equals("deposit")){
            DebetAccount debetAccount = new DebetAccount();
            return debetAccount;
        } else if (getType().equals("debet")){
            DebetAccount debetAccount = new DebetAccount();
            return debetAccount;
        } else {
            DebetAccount debetAccount = new DebetAccount();
            return debetAccount;
        }
    }



}
