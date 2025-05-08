package com.example.bankdemo.service;

import com.example.bankdemo.model.Transaction;
import com.example.bankdemo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByAccount(String accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }

    public List<Transaction> getTransactionsByCustomer(String customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }

    public List<Transaction> getTransactionsByType(String description) {
        return transactionRepository.findByDescriptionContainingIgnoreCase(description);
    }

    public List<Transaction> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findByTrxDateBetween(startDate, endDate);
    }

    public List<Transaction> getLargeTransactions(BigDecimal amount) {
        return transactionRepository.findTransactionsWithAmountGreaterThan(amount);
    }

    public List<Object[]> getTransactionTypeSummary() {
        return transactionRepository.getTotalAmountByTransactionType();
    }

    public List<Object[]> getAccountSummary() {
        return transactionRepository.getTotalAmountByAccount();
    }

    public List<Object[]> getCustomerSummary() {
        return transactionRepository.getTotalAmountByCustomer();
    }
}