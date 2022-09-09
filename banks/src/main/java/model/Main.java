package model;

import service.BankInterface;
import service.Functionality;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
//        Functionality functionality = new Functionality();
//        Client CurClient = new Client("fdf", "fdsd", "fdfdsf", "523423", true);
//        System.out.println(CurClient.getAddress());
//        Client client1 = functionality.addClient("nick", "powder");
//        Client client2 = functionality.addClient("kek", "tuk tuk", "konec", "444", true);
//        System.out.println(client1.getAddress());
//        System.out.println(client1.getName());
//        client1.setAddress("trrrue");
//        System.out.println(client1.getAddress());
//        List<Double> list = new ArrayList();
//        Scanner scanner = new Scanner(System.in);
//        for(int i = 0; i < 3; i++) {
//            list.add(scanner.nextDouble());
//        }
//        Bank bank1 = new Bank("hi", 5666, 555, 66, 66, list);
//        BankAccount bankAccount1 = functionality.addBankAccount(bank1,client1, "credit", 555);
//        CreditAccount creditAccount = new CreditAccount();
//        creditAccount.transferMoney(bankAccount1, bankAccount1, 555);
//        Account account = new CreditAccount();
//        account.takeMoney(bankAccount1, 555);

        BankInterface interfaceBank = new BankInterface();
        interfaceBank.menu();

        Integer i = 6;
        Integer g = 9;
        System.out.println(i + g);



    }

}
