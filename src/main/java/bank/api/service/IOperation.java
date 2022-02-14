package bank.api.service;

import bank.api.models.IAccount;

import java.util.Date;

public interface IOperation {

    public void processOperation (IAccount account, double amount);
    public void processOperationByDate (IAccount account, double amount, Date dateOperation);
}
