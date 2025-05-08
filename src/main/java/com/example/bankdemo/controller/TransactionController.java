package com.example.bankdemo.controller;

import com.example.bankdemo.model.Transaction;
import com.example.bankdemo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;



@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/account/{accountNumber}")
    public List<Transaction> getTransactionsByAccount(@PathVariable String accountNumber) {
        return transactionService.getTransactionsByAccount(accountNumber);
    }

    @GetMapping("/customer/{customerId}")
    public List<Transaction> getTransactionsByCustomer(@PathVariable String customerId) {
        return transactionService.getTransactionsByCustomer(customerId);
    }

    @GetMapping("/type/{description}")
    public List<Transaction> getTransactionsByType(@PathVariable String description) {
        return transactionService.getTransactionsByType(description);
    }

    @GetMapping("/date-range")
    public List<Transaction> getTransactionsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return transactionService.getTransactionsByDateRange(startDate, endDate);
    }

    @GetMapping("/large-transactions")
    public List<Transaction> getLargeTransactions(@RequestParam BigDecimal amount) {
        return transactionService.getLargeTransactions(amount);
    }

    @GetMapping("/summary/type")
    public List<Object[]> getTransactionTypeSummary() {
        return transactionService.getTransactionTypeSummary();
    }

    @GetMapping("/summary/account")
    public List<Object[]> getAccountSummary() {
        return transactionService.getAccountSummary();
    }

    @GetMapping("/summary/customer")
    public List<Object[]> getCustomerSummary() {
        return transactionService.getCustomerSummary();
    }
}