package com.chandu.fleet.service;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chandu.fleet.entity.BankAccount;
import com.chandu.fleet.exception.BankAccountNotFound;
import com.chandu.fleet.repository.BankAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BankAccountService {

    private static final String FIXED_IFSC_CODE = "BANK0001234";  // 🔥 Fixed IFSC Code

    @Autowired
    private BankAccountRepository bankAccountRepository;

    // ✅ Create a new bank account
    public BankAccount createBankAccount(BankAccount bankAccount) {
        // Generate a random account number
        bankAccount.setAccountNumber(UUID.randomUUID().toString().substring(0, 10));

        // 🔥 Set fixed IFSC code automatically
        bankAccount.setIfscCode(FIXED_IFSC_CODE);

        return bankAccountRepository.save(bankAccount);
    }

    // ✅ Fetch all bank accounts
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    // ✅ Fetch a bank account by account number
    public ResponseEntity<BankAccount> getAccountByNumber(String accountNumber) {
        BankAccount account= bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new BankAccountNotFound("Bank Account not found! with this account number : "+accountNumber));
        return  ResponseEntity.ok(account);
    }
}
