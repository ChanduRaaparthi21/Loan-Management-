package com.chandu.fleet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chandu.fleet.entity.BankAccount;
import com.chandu.fleet.entity.FleetLoan;
import com.chandu.fleet.repository.BankAccountRepository;
import com.chandu.fleet.repository.FleetLoanRepository;

@Service
public class FleetLoanService {

    @Autowired
    private FleetLoanRepository fleetLoanRepository;
    
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public FleetLoan applyLoan(FleetLoan loan) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findByAccountNumber(loan.getAccountNumber());

        if (bankAccount.isPresent()) {
            loan.setStatus(FleetLoan.LoanStatus.PENDING);
            return fleetLoanRepository.save(loan);
        } else {
            throw new RuntimeException("You don't have a bank account with this bank.");
        }
    }

    public List<FleetLoan> getLoansByAccount(String accountNumber) {
        return fleetLoanRepository.findByAccountNumber(accountNumber);
    }

    public Optional<FleetLoan> getLoanById(Long loanId) {
        return fleetLoanRepository.findById(loanId);
    }

    public FleetLoan approveLoan(Long loanId) {
        FleetLoan loan = fleetLoanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus(FleetLoan.LoanStatus.APPROVED);
        return fleetLoanRepository.save(loan);
    }

    public FleetLoan rejectLoan(Long loanId) {
        FleetLoan loan = fleetLoanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus(FleetLoan.LoanStatus.REJECTED);
        return fleetLoanRepository.save(loan);
    }
}
