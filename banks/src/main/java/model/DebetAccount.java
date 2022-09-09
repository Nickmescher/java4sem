package model;

import exception.notEnoughMoneyException;

public class DebetAccount implements Account{
    @Override
    public void addTime(int days, BankAccount bankAccount) {
        bankAccount.addDays(days);
        final double constForDividing = 36500;
        bankAccount.addMoneyForRepl(((bankAccount.getBank().getDebitPercentage()/constForDividing)*days)* bankAccount.getMoney());
        if (bankAccount.getDays() > 30){
            while (bankAccount.getDays() > 30){
                bankAccount.replenishment(bankAccount.getMoneyForRepl());
                bankAccount.decreaseDays(30);
            }
        }
    }

    @Override
    public void transferMoney(BankAccount from, BankAccount to, double money) {
        if (from.getClient().getAddress() != null && from.getClient().getPassport() != null) {
            if (from.getMoney() > money) {
                from.withdrawal(money);
                to.replenishment(money);
            } else {
                throw new notEnoughMoneyException("Not enough money for transfer");
            }
        } else {
            if ((from.getMoney() > money) && ((from.getMoney() - money) > from.getBank().getLimitDoubful())) {
                from.withdrawal(money);
                to.replenishment(money);
            } else {
                throw new notEnoughMoneyException("Not enough money for transfer as a doubtful person");
            }
        }
    }

    @Override
    public void takeMoney(BankAccount from, double money) {
        if (from.getClient().getAddress() != null && from.getClient().getPassport() != null) {
            if (from.getMoney() >= money) {
                from.withdrawal(money);
            } else {
                throw new notEnoughMoneyException("Not enough money for withdrawal");
            }
        } else {
            if ((from.getMoney() > money) && ((from.getMoney() - money) > from.getBank().getLimitDoubful())) {
                from.withdrawal(money);
            } else {
                throw new notEnoughMoneyException("Not enough money for withdrawal");
            }
        }
    }

}
