package com.chandu.fleet.service;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chandu.fleet.entity.BankAccount;
import com.chandu.fleet.repository.BankAccountRepository;

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
    public BankAccount getAccountByNumber(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Bank Account not found!"));
    }
}
