package bank.api.service;


import java.util.Date;

import bank.api.models.IAccount;
import bank.api.models.OperationLine;
import bank.api.models.OperationType;

public class Withdraw implements IOperation {
    @Override
    public void processOperation(IAccount account, double amount)  {
        if(amount < 0 ) throw new IllegalArgumentException("Negative amount not allow");
        double balance = account.getBalance() - amount;
         account.updateOperationHistory(new OperationLine(amount,new DateUtils().getCurrenteDate(), OperationType.WITHDRAW,balance));
    }

    @Override
    public void processOperationByDate(IAccount account, double amount, Date dateOperation) {
        if(amount < 0 ) throw new IllegalArgumentException("Negative amount not allow");
        double balance = account.getBalance() - amount;
        account.updateOperationHistory(new OperationLine(amount,dateOperation, OperationType.WITHDRAW,balance));

    }
}
