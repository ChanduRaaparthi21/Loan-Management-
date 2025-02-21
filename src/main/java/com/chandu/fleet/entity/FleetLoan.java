package com.chandu.fleet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fleet_loans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FleetLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountNumber;  // Must exist in BankAccount

    @Column(nullable = false)
    private Double loanAmount;

    @Column(nullable = false)
    private Integer loanTerm; // Loan duration in months

    @Column(nullable = false)
    private Double interestRate;

    @Column(nullable = false)
    private String vehicleDetails; // E.g., "Fleet of 10 trucks"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status; // PENDING, APPROVED, REJECTED

    public enum LoanStatus {
        PENDING, APPROVED, REJECTED
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(Integer loanTerm) {
		this.loanTerm = loanTerm;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public String getVehicleDetails() {
		return vehicleDetails;
	}

	public void setVehicleDetails(String vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}

	public LoanStatus getStatus() {
		return status;
	}

	public void setStatus(LoanStatus status) {
		this.status = status;
	}

	public FleetLoan(Long id, String accountNumber, Double loanAmount, Integer loanTerm, Double interestRate,
			String vehicleDetails, LoanStatus status) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.loanAmount = loanAmount;
		this.loanTerm = loanTerm;
		this.interestRate = interestRate;
		this.vehicleDetails = vehicleDetails;
		this.status = status;
	}

	public FleetLoan() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    
}
