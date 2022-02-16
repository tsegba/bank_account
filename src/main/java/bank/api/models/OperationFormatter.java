package bank.api.models;

import bank.api.service.DateUtils;

import java.text.DecimalFormat;
import java.util.Date;

public class OperationFormatter {
    private static final String FIELD_SEPARATOR = " | ";
    private final String datePattern;


    public OperationFormatter(String datePattern) {
        this.datePattern = datePattern;
    }

    public String format(final OperationLine operationLine) {

        String ammountDisplay = operationLine.getOperationType().equals(OperationType.DEPOSIT) ? formatMoney(operationLine.getAmount())  :  "-"+ formatMoney(operationLine.getAmount());

        return operationLine.getOperationType() + FIELD_SEPARATOR
                +formatDate(operationLine.getOperationDate()) + FIELD_SEPARATOR
                + ammountDisplay + FIELD_SEPARATOR
                + formatMoney(operationLine.getBalance());
    }

    private String formatDate(Date date) {
        return new DateUtils(datePattern).format(date);
    }

    private String formatMoney(final double amount) {
        return new DecimalFormat("####,####.##").format(amount).replaceAll(",",".");
    }

    public String buildHeader() {
        String headerType = String.format("%-8s","Operation") ;
        String headerDate = "|" + String.format("%-12s","Date");
        String headerAmount ="|" + String.format("%-12s","Amount") ;
        String headerBalance ="|" + String.format("%-12s","Balance") ;
        return headerType+headerDate + headerAmount + headerBalance;
    }

}

