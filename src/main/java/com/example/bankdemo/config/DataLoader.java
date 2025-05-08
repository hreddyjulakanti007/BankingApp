package com.example.bankdemo.config;

import com.example.bankdemo.model.Transaction;
import com.example.bankdemo.repository.TransactionRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(TransactionRepository repository) {
        return args -> {
            try {
                InputStream resource = new ClassPathResource("transactions.csv").getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(resource));

                CSVParser csvParser = new CSVParser(reader,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

                for (CSVRecord record : csvParser) {
                    Transaction transaction = new Transaction();
                    transaction.setAccountNumber(record.get("ACCOUNT_NUMBER"));
                    transaction.setTrxAmount(new BigDecimal(record.get("TRX_AMOUNT")));
                    transaction.setDescription(record.get("DESCRIPTION"));
                    transaction.setTrxDate(LocalDate.parse(record.get("TRX_DATE")));
                    transaction.setTrxTime(LocalTime.parse(record.get("TRX_TIME")));
                    transaction.setCustomerId(record.get("CUSTOMER_ID"));

                    repository.save(transaction);
                }

                csvParser.close();
            } catch (Exception e) {
                System.err.println("Error loading data: " + e.getMessage());
            }
        };
    }
}