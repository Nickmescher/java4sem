package model;

import exception.notEnoughMoneyException;
import exception.outOfLimitException;
import exception.outOfPeriodException;
import java.util.List;

public class DepositAccount implements Account {
    @Override
    public void addTime(int days, BankAccount bankAccount) {
        bankAccount.addDays(days);
        List<Double> percentDeposit = bankAccount.getBank().getDepositPercentage();
        final double constForDividing = 36500;
        for (int i = 0; i < days; i++) {
            if (bankAccount.getMoney() < 50000)
                bankAccount.addMoneyForRepl(((percentDeposit.get(0) / constForDividing) * days) * bankAccount.getMoney());
            else if (bankAccount.getMoney() < 100000)
                bankAccount.addMoneyForRepl(((percentDeposit.get(1) / constForDividing) * days) * bankAccount.getMoney());
            else
                bankAccount.addMoneyForRepl(((percentDeposit.get(2) / constForDividing) * days) * bankAccount.getMoney());
        }
        bankAccount.addMoneyForRepl(((bankAccount.getBank().getDebitPercentage() / constForDividing) * days) * bankAccount.getMoney());
        if (bankAccount.getDays() > 30) {
            while (bankAccount.getDays() > 30) {
                bankAccount.replenishment(bankAccount.getMoneyForRepl());
                bankAccount.decreaseDays(30);
                bankAccount.decreasePeriod(30);
            }
        }
    }

    @Override
    public void transferMoney(BankAccount from, BankAccount to, double money) {
        boolean endPer = true;
        if (from.getPeriod() > 0) {
            endPer = false;
            throw new outOfPeriodException("You can't transfer money until your period not ended");
        }
        if (from.getClient().getAddress() != null && from.getClient().getPassport() != null && endPer) {
            if (from.getMoney() > money) {
                from.withdrawal(money);
                to.replenishment(money);
            } else {
                throw new notEnoughMoneyException("Not enough money for transfer");
            }
        } else {
            if ((from.getMoney() > money) && ((from.getMoney() - money) > from.getBank().getLimitDoubful()) && endPer) {
                from.withdrawal(money);
                to.replenishment(money);
            } else {
                throw new notEnoughMoneyException("Not enough money for transfer as a doubtful person");
            }
        }
    }

    @Override
    public void takeMoney(BankAccount from, double money) {
        boolean endPer = true;
        if (from.getPeriod() > 0) {
            endPer = false;
            throw new outOfPeriodException("You can't withdraw money until your period not ended");
        }
        if (from.getClient().getAddress() != null && from.getClient().getPassport() != null && endPer) {
            if (from.getMoney() > money) {
                from.withdrawal(money);
            } else {
                throw new notEnoughMoneyException("Not enough money for withdrawal");
            }
        } else {
            if ((from.getMoney() > money) && ((from.getMoney() - money) > from.getBank().getLimitDoubful()) && endPer) {
                from.withdrawal(money);
            } else {
                throw new notEnoughMoneyException("Not enough money for withdrawal as a doubtful person");
            }
        }
    }
 }

