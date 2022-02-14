package bank.api.models;


import java.util.List;

public interface IAccount {

    public double getBalance();

    public List<OperationLine> getOperationsHistory();
    public void  updateOperationHistory(OperationLine operationHisoLine);
    public  void printStatement();

}
