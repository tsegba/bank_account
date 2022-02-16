package bank.api.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Account implements IAccount {
    private double balance ;
    private  List<OperationLine> operations= new ArrayList<>();
    private OperationFormatter formatter;

    @Override
    public List<OperationLine> getOperations() {
        return this.operations;
    }

    @Override
    public void updateOperationHistory(final OperationLine operationLine) {
        if(operationLine==null) throw new NullPointerException("Operation line can not be null");
        this.balance = operationLine.getBalance();
        operations.add(operationLine);

    }

    @Override
    public void printStatement(Statement statement ) {
        formatter = new OperationFormatter("dd/MM/yyyy HH:mm:ss");
        System.out.println(formatter.buildHeader());
        statement.getOperationLines().stream()
                .map(formatter::format)
                .forEach(System.out::println);
    }

    @Override
    public Statement generateStatement(Date statementDate) {
        List<OperationLine> operationLines = new ArrayList<>();
        operationLines = this.getOperations().stream().filter(elt-> elt.getOperationDate().compareTo(statementDate) <= 0 ).collect(Collectors.toList());
        return new Statement(operationLines);
    }

    public double getBalance() {
        return balance;
    }
}
