import model.Bank;
import model.BankAccount;
import model.CentralBank;
import org.junit.Before;
import org.junit.Test;
import service.BankInterface;
import service.Functionality;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankTest {
    private BankInterface bankInterface = new BankInterface();

    @Test
    public void AddBankAndClient() {
        Functionality functionality = new Functionality();
        CentralBank centralBank = new CentralBank();
        BankInterface bankInterface = new BankInterface();
        String clientName = "Name";
        String clientSurname = "Surname";
        double clientMoney = 100000;
        String clientAddress = "Address";
        int clientPassport = 1234567891;
        String bankName = "Tinkoff";
        double bankMoney = 1000000;
        double maxLimitForDoubtfulClients = 100000;
        List <Double> list = new ArrayList(Arrays.asList(45,56,45));
        Bank bank = centralBank.addBank("aaa", 555.0, 656.0, 55.0,55.0,list);


    }
}
