package bank.api.models;


import java.util.Date;
import java.util.Objects;

public class OperationLine {
    private final double amount;
    private final Date operationDate;
    private final OperationType operationType;
    private  double balance;

    public OperationLine(final double amount,final Date operationDate, final OperationType operationType) {
        this.amount = amount;
        this.operationDate = operationDate;
        this.operationType = operationType;
    }

    public OperationLine(final double amount,final Date operationDate, final OperationType operationType,double balance) {
        this.amount = amount;
        this.operationDate = operationDate;
        this.operationType = operationType;
        this.balance = balance;
    }

    public double getAmount() {
        return amount;
    }
    public Date getOperationDate() {
        return operationDate;
    }
     public OperationType getOperationType() {
        return operationType;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationLine other = (OperationLine) o;
        return Double.compare(other.getAmount(), getAmount()) == 0  && getOperationType() == other.getOperationType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount,operationDate, operationType);
    }
}
