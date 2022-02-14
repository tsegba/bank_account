//package bank.api;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.util.Date;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import bank.api.models.Account;
//import bank.api.models.IAccount;
//import bank.api.models.OperationHistoryLine;
//import bank.api.models.OperationType;
//import bank.api.service.Deposit;
//import bank.api.service.IOperation;
//import bank.api.service.Withdraw;
//
//@SpringBootTest
//class BanksAccountApplicationTests {
//
//	private final ByteArrayOutputStream systemOut = new ByteArrayOutputStream();
//	private final PrintStream originalOut = System.out;
//	private IAccount account;
//	private IOperation operation ;
//
//	@Mock
//	private Date date = new Date();
//
//	void setUp() {
//		System.setOut(new PrintStream(systemOut));
//	}
//
//	//US 1
//	@Test
//	void whenInitAccountBalanceShouldBe0(){
//		account = new Account();
//		assertEquals (0 ,account.getBalance());
//	}
//	@Test
//	void whenInitialiseAccountWith10BalanceShouldBe10(){
//		account = new Account(10);
//		assertEquals (10 ,account.getBalance());
//	}
//	@Test
//	void whenMakeDeposit3BalanceShouldBe3(){
//		account = new Account();
//		operation = new Deposit();
//
//		operation.processOperation(account,3);
//
//		assertEquals (3 ,account.getBalance());
//	}
//
//	@Test
//	void whenIntializeAccountWith2AndMakeDeposit3BalanceShouldBe5(){
//		account =	new Account(2);
//		operation = new Deposit();
//
//		operation.processOperation(account,3);
//
//		assertEquals (5 ,account.getBalance());
//	}
//
//	@Test
//	void whenMakeDepositNegativeShouldRaiseIllegalArgumentException(){
//		account = new Account();
//		operation = new Deposit();
//
//		assertThrows(IllegalArgumentException.class, () -> { operation.processOperation(account,-3); });
//	}
//
//	//US 2
//
//	@Test
//	void whenWithdraw4WithInitialeBalance10AccountBalanceShouldBe6(){
//		account= new Account(10);
//
//		operation = new Withdraw();
//		operation.processOperation(account,4);
//
//		assertEquals (6 ,account.getBalance());
//	}
//
//	@Test
//	void whenWithdraw10WithInitialeBalance10AccountBalanceShouldBe0(){
//		account= new Account(10);
//
//		operation = new Withdraw();
//		operation.processOperation(account,10);
//
//		assertEquals (0 ,account.getBalance());
//	}
//
//	@Test
//	void whenWithdraw10WithInitialeBalance5ShouldRaiseIllegalArgumentException(){
//		account = new Account();
//		operation = new Withdraw();
//
//		assertThrows(IllegalArgumentException.class, () -> {operation.processOperation(account,10);});
//	}
//
//	@Test
//	void whenMakeWithdrawNegativeShouldRaiseIllegalArgumentException(){
//		account = new Account();
//		operation = new Withdraw();
//
//		assertThrows(IllegalArgumentException.class, () -> { operation.processOperation(account,-3); });
//	}
//
//	//US 3
//	@Test
//	void whenInitilizeAccountOperationSizeShould0(){
//		 account = new Account();
//		 assertEquals (0,account.getOperationsHistory().size());
//	}
//
//	@Test
//	void whenHistoryLineIsNullShouldNullPointerException(){
//		assertThrows(NullPointerException.class, () -> { operation.processOperation(null,3); });
//	}
//
//	@Test
//	void whenInitilizeAccountWithBalance5_OperationSizeShould0(){
//		 account = new Account();
//		assertEquals (0,account.getOperationsHistory().size());
//		assertFalse(account.getOperationsHistory().contains(new OperationHistoryLine(0,date, OperationType.DEPOSIT)));
//	}
//
//	@Test
//	void whenDeposit3OnAccountOperationSizeShould1(){
//		Account account = new Account();
//		operation = new Deposit();
//
//		operation.processOperation(account,3);
//		assertTrue(account.getOperationsHistory().contains(new OperationHistoryLine(3,date, OperationType.DEPOSIT)));
//		assertEquals (1,account.getOperationsHistory().size());
//	}
//	@Test
//	void whenMake2Deposit3OnAccountOperationSizeShould2(){
//		Account account = new Account();
//		operation = new Deposit();
//
//		operation.processOperation(account,3);
//		operation.processOperation(account,4);
//
//		assertEquals (2,account.getOperationsHistory().size());
//		assertTrue(account.getOperationsHistory().contains(new OperationHistoryLine(4,date, OperationType.DEPOSIT)));
//		assertTrue(account.getOperationsHistory().contains(new OperationHistoryLine(3,date, OperationType.DEPOSIT)));
//	}
//
//	@Test
//	void whenMake2DepositAnd1Withdraw3OnAccountOperationSizeShould3(){
//		Account account = new Account();
//		operation = new Deposit();
//		operation.processOperation(account,3);
//		operation.processOperation(account,4);
//		operation = new Withdraw();
//		operation.processOperation(account,5);
//
//		assertEquals (3,account.getOperationsHistory().size());
//		assertTrue(account.getOperationsHistory().contains(new OperationHistoryLine(5,date, OperationType.WITHDRAW)));
//	}
//
//}
