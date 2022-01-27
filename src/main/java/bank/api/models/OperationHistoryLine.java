package bank.api.models;


import java.util.Date;
import java.util.Objects;

public class OperationHistoryLine {
    private double amount;
    private Date operationDate;
    private OperationType operationType;

    public OperationHistoryLine(double amount, Date operationDate, OperationType operationType) {
        this.amount = amount;
        this.operationDate = operationDate;
        this.operationType = operationType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationHistoryLine that = (OperationHistoryLine) o;
        return Double.compare(that.amount, amount) == 0 && operationType == that.operationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount,operationDate, operationType);
    }
}
