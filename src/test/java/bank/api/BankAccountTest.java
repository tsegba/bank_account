package bank.api;

import bank.api.models.*;
import bank.api.service.DateUtils;
import bank.api.service.Deposit;
import bank.api.service.IOperation;
import bank.api.service.Withdraw;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class BankAccountTest {

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
    public void updateOperation_WhenMakeDepositThenWhillUpdateOperation(){
        Account account = mock(Account.class);
        Deposit deposit = new Deposit();

        deposit.processOperation(account,10);

        verify(account).updateOperationHistory(new OperationLine(10,NOW_DATE, OperationType.DEPOSIT));
    }

    @Test
    public void updateOperation_WhenMakeWithdrawThenWhillUpdateOperation(){
        Account account = mock(Account.class);
        Withdraw withdraw = new Withdraw();

        withdraw.processOperation(account,10);

        verify(account).updateOperationHistory(new OperationLine(10,NOW_DATE, OperationType.WITHDRAW));
    }



	@Test
	void getBalance_whenDeposit4WithInitialeBalanceAccountBalanceShouldBe4(){
        Account account= new Account();
        Deposit operation = new Deposit();

		operation.processOperation(account,4);

		assertEquals (4 ,account.getBalance());
	}

	@Test
	void processOperation_whenMakeDepositNegativeShouldRaiseIllegalArgumentException(){
        Account account = new Account();
        Deposit operation = new Deposit();

		assertThrows(IllegalArgumentException.class, () -> { operation.processOperation(account,-3); });
	}

	@Test
	void processOperation_whenMakeWithdrawNegativeShouldRaiseIllegalArgumentException(){
		Account  account = new Account();
	    Withdraw	operation = new Withdraw();
		assertThrows(IllegalArgumentException.class, () -> { operation.processOperation(account,-3); });
	}





    @Test
    public void formatHeaderTransaction_test() {

        OperationFormatter transactionFormat = new OperationFormatter("dd/MM/yyyy HH:mm:ss");

        String expectedHeader = "Operation|Date        |Amount      |Balance     ";

        Assertions.assertThat(transactionFormat.buildHeader()).isEqualTo(expectedHeader);
    }

    @Test
    public void formatDepositTransaction_test() {
        OperationFormatter transactionFormat = new OperationFormatter("dd/MM/yyyy HH:mm:ss");

        OperationLine transaction = new OperationLine(1000.89, NOW_DATE, OperationType.DEPOSIT,1000.89);

        String formattedTransaction = transactionFormat.format(transaction);
        Assertions.assertThat(formattedTransaction).isEqualTo("DEPOSIT | 14/02/2022 15:00:00 | 1000.89 | 1000.89");
    }

    @Test
    public void formatWithdrawTransaction_test() {
        OperationFormatter transactionFormat = new OperationFormatter("dd/MM/yyyy HH:mm:ss");

        OperationLine transaction = new OperationLine(1000.89, NOW_DATE, OperationType.WITHDRAW,2000);

        String formattedTransaction = transactionFormat.format(transaction);
        Assertions.assertThat(formattedTransaction).isEqualTo("WITHDRAW | 14/02/2022 15:00:00 |  | 1000.89 | 2000");
    }



    @Test
    public void statement_whenInitialAccountThenStatementDisplayHeader() {
        Account account = new Account();
        String expectedStatement = "Operation|Date        |Amount      |Balance     \n";

        account.printStatement();

        assertThat(systemOut.toString()).isEqualTo(expectedStatement);
    }


    @Test
    public void statement_whenMakeOneOperationAccountThenStatementDisplayOperationWithHeader() {
        String expectedStatement = "Operation|Date        |Amount      |Balance     \n";
        IAccount account = new Account();

        Deposit operation = new Deposit();

        operation.processOperationByDate(account,390.9,NOW_DATE);
        account.printStatement();

        assertThat(systemOut.toString()).isEqualTo(expectedStatement+"DEPOSIT | 14/02/2022 15:00:00 | 390.9 | 390.9\n");
    }

    @Test
    public void statement_whenMakeTwoOperationsThenStatementDisplayByReversedDate() {
        String expectedStatement = "Operation|Date        |Amount      |Balance     \n";
         expectedStatement += "WITHDRAW | 14/02/2022 17:50:00 |  | 70 | 80\n";
        expectedStatement += "DEPOSIT | 14/02/2022 15:00:00 | 150 | 150\n";

        IAccount account = new Account();
        IOperation operation = new Deposit();

        operation.processOperationByDate(account,150,NOW_DATE);

        operation = new Withdraw();
        operation.processOperationByDate(account,70,new DateUtils("dd/MM/yyyy HH:mm:ss").format("14/02/2022 17:50:00"));

        account.printStatement();

        assertThat(systemOut.toString()).isEqualTo(expectedStatement);
    }


    @Test
	void whenMake2DepositAnd1Withdraw3OnAccountOperationSizeShould3(){
		Account account = new Account();
        IOperation operation = new Deposit();
        operation.processOperation(account,3);
        operation.processOperation(account,4);

        operation = new Withdraw();
		operation.processOperation(account,5);

		assertEquals (3,account.getOperationsHistory().size());
		assertEquals (2,account.getBalance());
		assertTrue(account.getOperationsHistory().contains(new OperationLine(4,NOW_DATE, OperationType.DEPOSIT)));
	}

}
