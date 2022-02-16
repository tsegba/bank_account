package bank.api.models;


import java.util.Date;
import java.util.List;

public interface IAccount {

    public double getBalance();

    public List<OperationLine> getOperations();
    public void  updateOperationHistory(OperationLine operationLine);
    public  void printStatement(Statement statement);
    public  Statement generateStatement(Date statementDate);

}
