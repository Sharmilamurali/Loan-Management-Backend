package com.cts.loan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Component
@Table(name = "LOANDATA")
public class LoanEntity {

	public LoanEntity(String loanNo, String loanType, String loanTerm, String loanAmount,
			@NotBlank(message = "Name can't be blank") String firstName,
			@NotBlank(message = "Name can't be blank") String lastName,
			@NotBlank(message = "Property Address can't be blank") String propertyAddress) {
		super();
		this.loanNo = loanNo;
		this.loanType = loanType;
		this.loanTerm = loanTerm;
		this.loanAmount = loanAmount;
		this.firstName = firstName;
		this.lastName = lastName;
		this.propertyAddress = propertyAddress;
	}

	@Id
	@Column(name = "Loan_No")
	private String loanNo;

	@Column(name = "Loan_Type")
	private String loanType;

	@Column(name = "Loan_Term")
	private String loanTerm;

	@Column(name = "Loan_Amount")
	private String loanAmount;

	@NotBlank(message = "Name can't be blank")
	@Column(name = "First_Name")
	private String firstName;

	@NotBlank(message = "Name can't be blank")
	@Column(name = "Last_Name")
	private String lastName;

	@NotBlank(message = "Property Address can't be blank")
	@Column(name = "Property_Address")
	private String propertyAddress;

	@ManyToOne()
	@JsonBackReference
	@JoinColumn(name = "user_name")
	private UserEntity user;
}
