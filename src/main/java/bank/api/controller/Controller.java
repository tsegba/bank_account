package bank.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bank.api.models.Account;
import bank.api.models.OperationHistoryLine;
import bank.api.service.Deposit;
import bank.api.service.Withdraw;



@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api")
public class Controller {

    Account account;
    Withdraw withdraw ;
    Deposit deposit ;

    public Controller(Account account) {
        this.account = account;
        withdraw = new Withdraw();
        deposit = new Deposit();
    }
    @RequestMapping(value = "/getBalance", method = RequestMethod.GET, produces = { "application/json" })
    public double getBalance() throws Exception {
        double response = 0;
        response = account.getBalance();
        return response;
    }
    @RequestMapping(value = "/saveDeposit", method = RequestMethod.POST, consumes = {
            "application/json" }, produces = { "application/json" })
    public String saveDeposit(@RequestParam("amount") double amount) throws Exception {
        try{
            deposit.processOperation(account,amount);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "Deposit make successfully";
    }

    @RequestMapping(value = "/doWithdraw", method = RequestMethod.POST, consumes = {
            "application/json" }, produces = { "application/json" })
    public String doWithdraw( @RequestParam("amount") double amount) throws Exception {
        try{
            withdraw.processOperation(account,amount);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "Withdraw make successfully";
    }

    @RequestMapping(value = "/getOperationHistory", method = RequestMethod.GET, produces = { "application/json" })
    public List<OperationHistoryLine> getOperationHistory() throws Exception {
        List<OperationHistoryLine> response = new ArrayList<>();
        try{
            response = account.getOperationsHistory();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}