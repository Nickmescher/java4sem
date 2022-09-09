package model;

import java.util.ArrayList;
import java.util.List;

public class CentralBank {

    private List<Bank> banks = new ArrayList<>();

    public Bank addBank(String name, double creditLimit, double limitDoubtful, double debitPercentage, double creditCommission, List<Double> depositPercentage) {
        Bank bank = new Bank(name, creditLimit, limitDoubtful, debitPercentage, creditCommission, depositPercentage);
        banks.add(bank);
        return bank;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

}
