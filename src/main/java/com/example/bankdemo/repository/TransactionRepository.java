package com.example.bankdemo.repository;

import com.example.bankdemo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountNumber(String accountNumber);

    List<Transaction> findByCustomerId(String customerId);

    List<Transaction> findByDescriptionContainingIgnoreCase(String description);

    List<Transaction> findByTrxDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT t FROM Transaction t WHERE t.trxAmount > :amount")
    List<Transaction> findTransactionsWithAmountGreaterThan(BigDecimal amount);

    @Query("SELECT t.description, SUM(t.trxAmount) FROM Transaction t GROUP BY t.description")
    List<Object[]> getTotalAmountByTransactionType();

    @Query("SELECT t.accountNumber, SUM(t.trxAmount) FROM Transaction t GROUP BY t.accountNumber")
    List<Object[]> getTotalAmountByAccount();

    @Query("SELECT t.customerId, SUM(t.trxAmount) FROM Transaction t GROUP BY t.customerId")
    List<Object[]> getTotalAmountByCustomer();
}