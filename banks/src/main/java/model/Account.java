package model;

import java.util.UUID;

public interface Account {
    void addTime(int days, BankAccount bankAccount);

    void transferMoney(BankAccount from, BankAccount to, double money);

    void takeMoney(BankAccount from, double money);


}
