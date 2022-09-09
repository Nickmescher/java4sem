package model;

import exception.notCorrectInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client {
    private String name;
    private String surname;
    private String address;
    private String passport;
    private boolean agreeForNotifications;
    private List<BankAccount> accounts = new ArrayList<>();
    private List<String> notifications = new ArrayList<>();
    private UUID id;

    public Client(String name, String surname, String address, String passport, boolean agreeForNotifications) {
        if (!isValid(name)) {
            throw new notCorrectInputException("Not Correct Input");
        }

        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passport = passport;
        this.agreeForNotifications = agreeForNotifications;
        this.id = UUID.randomUUID();
    }

    private boolean isValid(String name) {
        if (name == null) return false;
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public boolean isAgreeForNotifications() {
        return agreeForNotifications;
    }

    public void setAgreeForNotifications(boolean agreeForNotifications) {
        this.agreeForNotifications = agreeForNotifications;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    public List<String> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<String> notifications) {
        this.notifications = notifications;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


}

