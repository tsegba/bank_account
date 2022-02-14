package bank.api.service;


import bank.api.models.IAccount;
import bank.api.models.OperationLine;
import bank.api.models.OperationType;

import java.util.Date;

public class Deposit implements IOperation {

    @Override
    public void processOperation(IAccount account, double amount) {
        if(amount < 0 ) throw new IllegalArgumentException("Negative amount not allow");
        double balance = account.getBalance() + amount;
        account.updateOperationHistory(new OperationLine(amount,new DateUtils().getCurrenteDate(), OperationType.DEPOSIT, balance));
    }

    @Override
    public void processOperationByDate(IAccount account, double amount, Date dateOperation) {
        if(amount < 0 ) throw new IllegalArgumentException("Negative amount not allow");
        double balance = account.getBalance() + amount;
        account.updateOperationHistory(new OperationLine(amount,dateOperation, OperationType.DEPOSIT, balance));
    }
}
