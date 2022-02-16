package bank.api.acceptance;

import bank.api.models.*;
import bank.api.service.DateUtils;
import bank.api.service.Deposit;
import bank.api.service.IOperation;
import bank.api.service.Withdraw;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class BankAccountAcceptanceTest {

    private DateUtils date ;
    private final ByteArrayOutputStream systemOut = new ByteArrayOutputStream();
    private static final Date NOW_DATE = new GregorianCalendar(2022, Calendar.FEBRUARY,14,15,0,0).getTime();


    @BeforeEach
    private void setUp() {
        System.setOut(new PrintStream(systemOut));
        date = mock(DateUtils.class);
        when(date.getCurrenteDate()).thenReturn(NOW_DATE);
    }


    @Test
    public void statement_whenMakeOneOperationAccountThenStatementDisplayOperationWithHeader() {
        IAccount account = new Account();
        Deposit operation = new Deposit();

        operation.processOperation(account,390.9,NOW_DATE);
        Statement statement = account.generateStatement(NOW_DATE);
        account.printStatement(statement);

        String expectedStatement = "Operation|Date        |Amount      |Balance     \nDEPOSIT | 14/02/2022 15:00:00 | 390.9 | 390.9\n";
        String printedStatement = systemOut.toString();

        assertThat(printedStatement).isEqualTo(expectedStatement);
    }

    @Test
    public void statement_whenMakeOperationsThenStatementDisplayByReversedDate() {

        IAccount account = new Account();
        IOperation deposit = new Deposit();
        IOperation withdraw = new Withdraw();


        deposit.processOperation(account,150,NOW_DATE);
        withdraw.processOperation(account,70,new DateUtils("dd/MM/yyyy HH:mm:ss").format("14/02/2022 13:20:00"));
        deposit.processOperation(account,15,new DateUtils("dd/MM/yyyy HH:mm:ss").format("14/02/2022 14:15:00"));

        Statement statement = account.generateStatement(NOW_DATE);
        account.printStatement(statement);

        String expectedStatement = "Operation|Date        |Amount      |Balance     \n";
        expectedStatement += "DEPOSIT | 14/02/2022 15:00:00 | 150 | 150\n";
        expectedStatement += "DEPOSIT | 14/02/2022 14:15:00 | 15 | 95\n";
        expectedStatement += "WITHDRAW | 14/02/2022 13:20:00 | -70 | 80\n";


        assertThat(systemOut.toString()).isEqualTo(expectedStatement);
    }

    @Test
    public void statement_whenMakeOperationsThenStatementDisplayBeforeDate() {

        IAccount account = new Account();
        IOperation deposit = new Deposit();
        IOperation withdraw = new Withdraw();


        deposit.processOperation(account,150,new DateUtils("dd/MM/yyyy HH:mm:ss").format("14/02/2022 13:20:00"));
        withdraw.processOperation(account,70,new DateUtils("dd/MM/yyyy HH:mm:ss").format("14/02/2022 17:00:00"));
        deposit.processOperation(account,15,new DateUtils("dd/MM/yyyy HH:mm:ss").format("14/02/2022 14:15:00"));

        Statement statement = account.generateStatement(NOW_DATE);
        account.printStatement(statement);

        String expectedStatement = "Operation|Date        |Amount      |Balance     \n";
        expectedStatement += "DEPOSIT | 14/02/2022 14:15:00 | 15 | 95\n";
        expectedStatement += "DEPOSIT | 14/02/2022 13:20:00 | 150 | 150\n";


        assertThat(systemOut.toString()).isEqualTo(expectedStatement);
    }

    @Test
	void whenMake2DepositAnd1Withdraw3OnAccountOperationSizeShould3(){
		Account account = new Account();
        IOperation deposit = new Deposit();
        IOperation withdraw = new Withdraw();

        deposit.processOperation(account,3,NOW_DATE);
        deposit.processOperation(account,4,NOW_DATE);
        withdraw.processOperation(account,5,NOW_DATE);

		assertEquals (3,account.getOperations().size());
		assertEquals (2,account.getBalance());

		assertTrue(account.getOperations().contains(new OperationLine(4,NOW_DATE, OperationType.DEPOSIT)));
	}

}
