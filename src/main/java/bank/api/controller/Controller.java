package bank.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bank.api.service.DateUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bank.api.models.Account;
import bank.api.models.OperationLine;
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
    public String saveDeposit(@RequestParam("amount") double amount,String dateOperation) throws Exception {
        try{
            deposit.processOperation(account,amount,new DateUtils().format(dateOperation));
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
    public String doWithdraw( @RequestParam("amount") double amount,String dateOperation) throws Exception {
        try{
            withdraw.processOperation(account,amount,new DateUtils().format(dateOperation));
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
    public List<OperationLine> getOperationHistory() throws Exception {
        List<OperationLine> response = new ArrayList<>();
        try{
            response = account.getOperations();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
