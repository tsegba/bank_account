package bank.api.service;

import bank.api.models.IAccount;

public interface IOperation {

    public void processOperation (IAccount account, double amount);
}
