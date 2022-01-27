package bank.api.service;


import java.util.Date;

import bank.api.models.IAccount;
import bank.api.models.OperationHistoryLine;
import bank.api.models.OperationType;

public class Withdraw implements IOperation {
    @Override
    public void processOperation(IAccount account, double amount)  {
        if(amount < 0 ) throw new IllegalArgumentException("Negative amount not allow");
        if(amount > account.getBalance() ) throw new IllegalArgumentException("Amount not allow,More than the balance due");
        account.setBalance(account.getBalance() - amount);
        account.updateOperationHistory(new OperationHistoryLine(amount,new Date(), OperationType.WITHDRAW));
    }
}