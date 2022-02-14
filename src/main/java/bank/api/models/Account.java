package bank.api.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class Account implements IAccount {
    private double balance ;
    private  List<OperationLine> operations= new ArrayList<>();
    private OperationFormatter formatter;

    @Override
    public List<OperationLine> getOperationsHistory() {
        return this.operations;
    }

    @Override
    public void updateOperationHistory(final OperationLine operationHisoLine) {
        if(operationHisoLine==null) throw new NullPointerException("Operation line can not be null");
        this.balance = operationHisoLine.getBalance();
        operations.add(operationHisoLine);
    }

    @Override
    public void printStatement() {

        formatter = new OperationFormatter("dd/MM/yyyy HH:mm:ss");
        System.out.println(formatter.buildHeader());
        this.operations.stream()
                .sorted(Comparator.comparing(OperationLine::getOperationDate).reversed())
                .map(formatter::format)
                .forEach(System.out::println);
    }

    public double getBalance() {
        return balance;
    }
}
