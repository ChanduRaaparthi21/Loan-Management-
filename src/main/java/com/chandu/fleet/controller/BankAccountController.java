package com.chandu.fleet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chandu.fleet.entity.BankAccount;
import com.chandu.fleet.service.BankAccountService;

@RestController
@RequestMapping("/bank")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    // ✅ Create a new bank account
    @PostMapping("/create-account")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // ✅ Only ADMIN can access
    public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.createBankAccount(bankAccount);
    }


    // ✅ Fetch all bank accounts (Only ADMIN can access)
    @GetMapping("/all-accounts")
    @PreAuthorize("hasRole('ADMIN')")  // ✅ Fixed role issue
    public List<BankAccount> getAllAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    // ✅ Fetch a specific account by account number (Only USER can access)
    @GetMapping("/account/{accountNumber}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")  // ✅ Fixed role issue
    public BankAccount getAccountByNumber(@PathVariable String accountNumber) {
        return bankAccountService.getAccountByNumber(accountNumber);
    }
    
    // ✅ Public access
    @GetMapping("/hi")
    public String Hi() {
        return "Hi";
    }
}
