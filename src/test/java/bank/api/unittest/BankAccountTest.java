package bank.api.unittest;

import bank.api.models.*;
import bank.api.service.DateUtils;
import bank.api.service.Deposit;
import bank.api.service.Withdraw;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class BankAccountTest {

    private DateUtils date;
    private final ByteArrayOutputStream systemOut = new ByteArrayOutputStream();
    private static final Date NOW_DATE = new GregorianCalendar(2022, Calendar.FEBRUARY, 14, 15, 0, 0).getTime();


    @BeforeEach
    private void setUp() {
        System.setOut(new PrintStream(systemOut));
        date = mock(DateUtils.class);
        when(date.getCurrenteDate()).thenReturn(NOW_DATE);
    }

    @Test
    public void test_format_date(){
        DateUtils dateUtils =new DateUtils();

        String actualDate = dateUtils.format(NOW_DATE);
        String expecteddate ="14/02/2022 15:00:00";

        assertThat(actualDate).isEqualTo(expecteddate);
    }

    @Test
    public void updateOperation_WhenMakeDepositThenWhillUpdateOperation() {
        Account account = mock(Account.class);
        Deposit deposit = new Deposit();

        deposit.processOperation(account, 10, NOW_DATE);

        verify(account).updateOperationHistory(new OperationLine(10, NOW_DATE, OperationType.DEPOSIT));
    }

    @Test
    public void updateOperation_WhenMakeWithdrawThenWhillUpdateOperation() {
        Account account = mock(Account.class);
        Withdraw withdraw = new Withdraw();

        withdraw.processOperation(account, 10, NOW_DATE);

        verify(account).updateOperationHistory(new OperationLine(10, NOW_DATE, OperationType.WITHDRAW));
    }


    @Test
    void getBalance_whenDeposit4WithInitialeBalanceAccountBalanceShouldBe4() {
        Account account = new Account();
        Deposit operation = new Deposit();

        operation.processOperation(account, 4, NOW_DATE);

        assertEquals(4, account.getBalance());
    }

    @Test
    void processOperation_whenMakeDepositNegativeShouldRaiseIllegalArgumentException() {
        Account account = new Account();
        Deposit operation = new Deposit();

        assertThrows(IllegalArgumentException.class, () -> {
            operation.processOperation(account, -3, NOW_DATE);
        });
    }

    @Test
    void processOperation_whenMakeWithdrawNegativeShouldRaiseIllegalArgumentException() {
        Account account = new Account();
        Withdraw operation = new Withdraw();
        assertThrows(IllegalArgumentException.class, () -> {
            operation.processOperation(account, -3, NOW_DATE);
        });
    }


    @Test
    public void formatHeaderTransaction_test() {

        OperationFormatter transactionFormat = new OperationFormatter("dd/MM/yyyy HH:mm:ss");

        String header = transactionFormat.buildHeader();
        String expectedHeader = "Operation|Date        |Amount      |Balance     ";

        Assertions.assertThat(header).isEqualTo(expectedHeader);
    }

    @Test
    public void formatDepositTransaction_test() {
        OperationFormatter transactionFormat = new OperationFormatter("dd/MM/yyyy HH:mm:ss");
        OperationLine transaction = new OperationLine(1000.89, NOW_DATE, OperationType.DEPOSIT, 1000.89);


        String formattedTransaction = transactionFormat.format(transaction);
        String expectedValue = "DEPOSIT | 14/02/2022 15:00:00 | 1000.89 | 1000.89";

        Assertions.assertThat(formattedTransaction).isEqualTo(expectedValue);
    }

    @Test
    public void formatWithdrawTransaction_test() {
        OperationFormatter transactionFormat = new OperationFormatter("dd/MM/yyyy HH:mm:ss");
        OperationLine transaction = new OperationLine(1000.89, NOW_DATE, OperationType.WITHDRAW, 2000);

        String formattedTransaction = transactionFormat.format(transaction);
        String expectedValue = "WITHDRAW | 14/02/2022 15:00:00 | -1000.89 | 2000";

        Assertions.assertThat(formattedTransaction).isEqualTo(expectedValue);
    }

    @Test
    public void statement_whenInitialAccountThenStatementDisplayHeader() {
        Account account = new Account();

        Statement statement = account.generateStatement(NOW_DATE);
        Statement expectedStatement = new Statement(new ArrayList<>());

        assertThat(statement).isEqualTo(expectedStatement);
    }


}
