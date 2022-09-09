package model;

import exception.notCorrectInputException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private String name;
    private double debitPercentage;
    private List <Double> depositPercentage = new ArrayList<>();
    private double creditLimit;
    private double creditCommission;
    private double limitDoubful;
    private double limit;
    private List<BankAccount> accounts = new ArrayList<>();
    private UUID id;

    public Bank(String name,
                double creditLimit,
                double limitDoubful,
                double debitPercentage,
                double creditCommission,
                List<Double> depositPercentage
    ) {
        if (!isValid(name)) {
            throw new notCorrectInputException("Not correct bank name input");
        }

        this.name = name;
        this.creditLimit = creditLimit;
        this.limitDoubful = limit;
        this.creditCommission = creditCommission;
        this.id = UUID.randomUUID();
    }

    private boolean isValid(String name) {
        if (name == null) return false;
        else return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getCreditCommission() {
        return creditCommission;
    }

    public void setCreditCommission(double creditCommission) {
        this.creditCommission = creditCommission;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getLimitDoubful() {
        return limitDoubful;
    }

    public void setLimitDoubful(double limitDoubful) {
        this.limitDoubful = limitDoubful;
    }

    public double getDebitPercentage() {
        return debitPercentage;
    }

    public void setDebitPercentage(double debitPercentage) {
        this.debitPercentage = debitPercentage;
    }


    public List<Double> getDepositPercentage() {
        return depositPercentage;
    }



    public void setDepositPercentage(List<Double> depositPercentage) {
        this.depositPercentage = depositPercentage;
    }

}
