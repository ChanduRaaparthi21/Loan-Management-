package com.chandu.fleet.controller;

import com.chandu.fleet.entity.FleetLoan;
import com.chandu.fleet.service.FleetLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fleet-loan")
public class FleetLoanController {

    @Autowired
    private FleetLoanService fleetLoanService;

    @PostMapping("/apply")
    @PreAuthorize("hasRole('USER')")
    public FleetLoan applyLoan(@RequestBody FleetLoan loan) {
        return fleetLoanService.applyLoan(loan);
    }

    @GetMapping("/{loanId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Optional<FleetLoan> getLoanById(@PathVariable Long loanId) {
        return fleetLoanService.getLoanById(loanId);
    }

    @GetMapping("/user/{accountNumber}")
    @PreAuthorize("hasRole('USER')")
    public List<FleetLoan> getLoansByAccount(@PathVariable String accountNumber) {
        return fleetLoanService.getLoansByAccount(accountNumber);
    }

    @PutMapping("/approve/{loanId}")
    @PreAuthorize("hasRole('ADMIN')")
    public FleetLoan approveLoan(@PathVariable Long loanId) {
        return fleetLoanService.approveLoan(loanId);
    }

    @PutMapping("/reject/{loanId}")
    @PreAuthorize("hasRole('ADMIN')")
    public FleetLoan rejectLoan(@PathVariable Long loanId) {
        return fleetLoanService.rejectLoan(loanId);
    }
}
