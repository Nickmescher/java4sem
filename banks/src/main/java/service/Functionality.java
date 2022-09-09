package service;
import exception.notCorrectInputException;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Functionality {
    private String name;
    private String surname;
    private String address;
    private String passport;
    private double money;
    private boolean agreeForNotifications;
    private List<Client> clients = new ArrayList<>();
    private List<BankAccount> bankAccounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private List<String> notifications = new ArrayList<>();
    private UUID id;

    public Client addClient(String name, String surnamne){
        Client client = new Client(name, surnamne, null, null, false);
        clients.add(client);
        return client;
    }

    public Client addClient(String name, String surname, String address, String passport, boolean agreeForNotifications){
        Client client = new Client(name, surname, address, passport, agreeForNotifications);
        clients.add(client);
        return client;
    }

    public BankAccount addBankAccount(Bank bank, Client client, String type, double money){
        BankAccount bankAccount = new BankAccount(client, bank, type, money);
        if (type.equals("credit")) {
            bankAccount.setAccountType(type);
        }
        else if (type.equals("deposit")) {
            bankAccount.setAccountType(type);
            System.out.println("Please input period");
            Scanner scanner = new Scanner(System.in);
            bankAccount.setPeriod(scanner.nextInt());
        }
        else if (type.equals("debit")) {
            bankAccount.setAccountType(type);
        } else {

            throw new notCorrectInputException("Not correct bank type input");
        }
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    public void addDays(int days) {
        for (BankAccount bA : bankAccounts){
            bA.getAccountType().addTime(days, bA);
        }
    }

    public void withdrawal(BankAccount bankAccount, double money){
        bankAccount.getAccountType().takeMoney(bankAccount, money);

    }

    public void transfer(BankAccount bAFrom, BankAccount bATo, double money){

        bAFrom.getAccountType().transferMoney(bAFrom, bATo, money);
        Transaction transaction = new Transaction(bAFrom, bATo, money);
        transactions.add(transaction);
    }

    public void cancelTransaction(Transaction transaction){
        if (transaction.isNotCancelling()){
            transaction.getbAFrom().replenishment(transaction.getMoney());
            transaction.getbATo().withdrawal(transaction.getMoney());
            transaction.setNotCancelling();
        }
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }


    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }


    public List<Transaction> getTransactions() {
        return transactions;
    }


}
