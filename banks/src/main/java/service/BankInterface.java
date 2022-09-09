package service;
import model.CentralBank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankInterface {

    Scanner scanner = new Scanner(System.in);
    Functionality functionality = new Functionality();
    CentralBank centralBank = new CentralBank();

    public void menu() {
        System.out.println("Hello, please choose action below and enter its number:");
        System.out.println("1. Add Client");
        System.out.println("2. Add Bank");
        System.out.println("3. Add Bank Account");
        System.out.println("4. Change client's passport");
        System.out.println("5. Change client's address");
        System.out.println("6. Add days");
        System.out.println("7. Replenishment to account");
        System.out.println("8. Withdrawal from account");
        System.out.println("9. Transfer money");
        System.out.println("10. Agree for notifications");
        System.out.println("11. Cancel transaction");
        switch (scanner.nextInt()) {
            case 1:
                addClient();
                break;
            case 2:
                addBank();
                break;
            case 3:
                addBankAccount();
                break;
            case 4:
                setPassport();
                break;
            case 5:
                setAddress();
                break;
            case 6:
                addDays();
                break;
            case 7:
                replenishment();
                break;
            case 8:
                withdrawal();
                break;
            case 9:
                transfer();
                break;
            case 10:
                notification();
                break;
            case 11:
                cancelTransaction();
                break;
            default:
                System.out.println("Incorrect input");
                menu();
        }
    }

    public void addClient() {
        String passport, address;
        System.out.println("Please input name");
        String name = scanner.next();
        System.out.println("Please input surname");
        String surname = scanner.next();
        System.out.println("Would you like to add passport and adress? y/n");
        String ans = scanner.next();
        if (ans.equals("y")) {
            System.out.println("Please input passport");
            passport = scanner.next();
            System.out.println("Please input address");
            address = scanner.next();
        } else {
            passport = null;
            address = null;
        }
        System.out.println("Would you like to get notifications? y/n");
        ans = scanner.next();
        boolean agreeForNotification = false;
        if (ans.equals("y")) {
            agreeForNotification = true;
        }
        functionality.addClient(name, surname, address, passport, agreeForNotification);
        menu();

    }

    public void addBank() {
        String name;
        double creditLimit;
        double limitDoubful;
        double debitPercentage;
        double creditCommission;
        List<Double> depositPercentage = new ArrayList<>();
        System.out.println("Please input name of Bank");
        name = scanner.next();
        System.out.println("Please input credit limit for Bank");
        creditLimit = scanner.nextDouble();
        System.out.println("Please input limit for doubtful persons in Bank");
        limitDoubful = scanner.nextDouble();
        System.out.println("Please input credit comission for Bank");
        creditCommission = scanner.nextDouble();
        System.out.println("Please input debit percentage for Bank");
        debitPercentage = scanner.nextDouble();
        System.out.println("Please input 3 deposit percentage for Bank");
        for (int i = 0; i < 3; i++) {
            depositPercentage.add(scanner.nextDouble());
        }
        centralBank.addBank(name, creditLimit, limitDoubful, debitPercentage, creditCommission, depositPercentage);
        menu();

    }

    public void addBankAccount() {
        System.out.println("Please choose bank and input its number below");
        for (int i = 0; i < centralBank.getBanks().size(); i++) {
            System.out.println(i + "  " + centralBank.getBanks().get(i).getName());
        }
        int number = scanner.nextInt();
        System.out.println("Please choose clients and input its number below");
        for (int i = 0; i < functionality.getClients().size(); i++) {
            System.out.println(i + " " + functionality.getClients().get(i).getName());
        }
        int number2 = scanner.nextInt();
        System.out.println("Please input type of bank account (credit, debit, deposit) ");
        String type = scanner.next();
        System.out.println("Please input money for bank account");
        double money = scanner.nextDouble();
        functionality.addBankAccount(centralBank.getBanks().get(number), functionality.getClients().get(number2), type, money);
        menu();

    }

    public void setPassport() {
        System.out.println("Please choose a client whose passport you gonna change");
        for (int i = 0; i < functionality.getClients().size(); i++) {
            System.out.println(i + " " + functionality.getClients().get(i).getName());
        }
        int number = scanner.nextInt();
        System.out.println("Please input passport number");
        String passport = scanner.nextLine();
        functionality.getClients().get(number).setPassport(passport);
        menu();

    }

    public void setAddress() {
        System.out.println("Please choose a client whose address you gonna change");
        for (int i = 0; i < functionality.getClients().size(); i++) {
            System.out.println(i + " " + functionality.getClients().get(i).getName());
        }
        int number = scanner.nextInt();
        System.out.println("Please input adress");
        String address = scanner.nextLine();
        functionality.getClients().get(number).setAddress(address);
        menu();

    }

    public void addDays() {
        System.out.println("Please input amount of days that you want to add");
        int days = scanner.nextInt();
        functionality.addDays(days);
        menu();
    }

    public void replenishment() {
        System.out.println("Please choose a bank account to replenishment");
        for (int i = 0; i < functionality.getClients().size(); i++) {
            System.out.println(i + "Client: " + functionality.getBankAccounts().get(i).getClient().getName() + " " + functionality.getBankAccounts().get(i).getClient().getSurname() + " Bank: " + functionality.getBankAccounts().get(i).getBank().getName());
            int number = scanner.nextInt();
            System.out.println("Please insert money you want to replenishment");
            double money = scanner.nextDouble();
            functionality.getBankAccounts().get(number).replenishment(money);
            menu();
        }

    }

    public void withdrawal() {
        System.out.println("Please choose a bank account to replenishment");
        for (int i = 0; i < functionality.getClients().size(); i++) {
            System.out.println(i + "Client: " + functionality.getBankAccounts().get(i).getClient().getName() + " " + functionality.getBankAccounts().get(i).getClient().getSurname() + " Bank: " + functionality.getBankAccounts().get(i).getBank().getName());
        }
        int number = scanner.nextInt();
        System.out.println("Please insert money you want to withdrawal");
        double money = scanner.nextDouble();
        functionality.getBankAccounts().get(number).withdrawal(money);
        menu();
    }

    public void transfer(){
        System.out.println("Please choose a bank account from what transfer will be done");
        for (int i = 0; i < functionality.getClients().size(); i++) {
            System.out.println(i + "Client: " + functionality.getBankAccounts().get(i).getClient().getName() + " " + functionality.getBankAccounts().get(i).getClient().getSurname() + " Bank: " + functionality.getBankAccounts().get(i).getBank().getName());
        }
        int client1 = scanner.nextInt();
        System.out.println("Please choose a bank account to what transfer will be done");
        for (int i = 0; i < functionality.getClients().size(); i++) {
            System.out.println(i + "Client: " + functionality.getBankAccounts().get(i).getClient().getName() + " " + functionality.getBankAccounts().get(i).getClient().getSurname() + " Bank: " + functionality.getBankAccounts().get(i).getBank().getName());
        }
        int client2 = scanner.nextInt();
        System.out.println("Please insert money you want to transfer");
        double money = scanner.nextDouble();

        menu();
    }

    public void notification(){
        System.out.println("Please choose a client you want make agree to notification");
        for (int i = 0; i < functionality.getClients().size(); i++) {
            System.out.println(i + " " + functionality.getClients().get(i).getName());
        }
        int clientNum = scanner.nextInt();
        functionality.getClients().get(clientNum).setAgreeForNotifications(true);
        menu();
    }

    public void cancelTransaction() {
        System.out.println("Please choose a transaction you want to cancel");
        for (int i = 0; i < functionality.getTransactions().size(); i++) {
            System.out.println(i + " From " + functionality.getTransactions().get(i).getbAFrom().getClient().getName() +
                    " To " + functionality.getTransactions().get(i).getbATo().getClient().getName() + " Amount of money " +
                    functionality.getTransactions().get(i).getMoney());
        }
        int transNum = scanner.nextInt();
        functionality.cancelTransaction(functionality.getTransactions().get(transNum));
        menu();
    }

}
