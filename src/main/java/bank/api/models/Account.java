package bank.api.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Account implements IAccount {
    private  double balance;
    private  List<OperationHistoryLine> operations= new ArrayList<>();

    public Account() {
        this.balance = 0;
    }

    public Account(double balance) {
        this.balance = balance;
    }
    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public List<OperationHistoryLine> getOperationsHistory() {
        return this.operations;
    }

    @Override
    public void updateOperationHistory(OperationHistoryLine operationHisoLine) {
        if(operationHisoLine==null)throw new NullPointerException("Operation line can not be null");
        operations.add(operationHisoLine);
    }


}
