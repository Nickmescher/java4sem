package model;

import exception.outOfLimitException;

public class CreditAccount implements Account{


    @Override
    public void addTime(int days, BankAccount bankAccount) {
        bankAccount.addDays(days);
        final double constForDividing = 36500;
        bankAccount.addMoneyForRepl(((bankAccount.getBank().getDebitPercentage()/constForDividing)*days)* bankAccount.getMoney());
        if (bankAccount.getDays() > 30){
            while (bankAccount.getDays() > 30){
                bankAccount.withdrawal(bankAccount.getMoneyForRepl());
                bankAccount.decreaseDays(30);
            }
        }
    }

    @Override
    public void transferMoney(BankAccount from, BankAccount to, double money) {
        boolean transf = false;
        if (from.getClient().getAddress() != null && from.getClient().getPassport() != null) {
            if (from.getMoney() - money > from.getBank().getCreditLimit()) {
                from.withdrawal(money);
                to.replenishment(money);
                transf = true;
            } else {
                throw new outOfLimitException("You are trying to transfer money out of limit");
            }
        } else {
            if ((from.getMoney() - money > from.getBank().getCreditLimit()) && ((from.getMoney() - money) > from.getBank().getLimitDoubful())) {
                from.withdrawal(money);
                to.replenishment(money);
                transf = true;
            } else {
                throw new outOfLimitException("You are doubtful person trying to transfer money out of limit");
            }
        }
        if (transf && from.getMoney() < 0){
            from.withdrawal(from.getBank().getCreditCommission());
        }

    }

    @Override
    public void takeMoney(BankAccount from, double money) {
        boolean transf = false;
        if (from.getClient().getAddress() != null && from.getClient().getPassport() != null) {
            if (from.getMoney() - money > from.getBank().getCreditLimit()) {
                from.withdrawal(money);
                transf = true;
            } else {
                throw new outOfLimitException("You are trying to withdrawal out of limit");
            }
        } else {
            if ((from.getMoney() - money > from.getBank().getCreditLimit()) && ((from.getMoney() - money) > from.getBank().getLimitDoubful())) {
                from.withdrawal(money);
                transf = true;
            } else {
                throw new outOfLimitException("You are doubtful person trying to withdrawal out of limit");
            }
        }
        if (transf && from.getMoney() < 0){
            from.withdrawal(from.getBank().getCreditCommission());
        }

    }
}