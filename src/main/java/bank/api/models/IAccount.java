package bank.api.models;


import java.util.List;

public interface IAccount {
    public double getBalance();
    public void setBalance(double balance);

    public List<OperationHistoryLine> getOperationsHistory();
    public void  updateOperationHistory(OperationHistoryLine operationHisoLine);
}
