package com.cts.loan.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cts.loan.entity.LoanEntity;
import com.cts.loan.entity.UserEntity;
import com.cts.loan.exception.LoanNotFoundException;
import com.cts.loan.service.LoanService;

@RequestMapping("/loans")
@RestController
public class LoanController { 

	@Autowired
	private LoanService loanService;

	@GetMapping("/") // only Admin
	public ResponseEntity<?> getLoans(@RequestHeader("Authorization") String token) throws LoanNotFoundException {
		try {
			return new ResponseEntity<>(loanService.getAllLoan(), HttpStatus.OK);
		} catch (LoanNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/search") // both
	public ResponseEntity<?> searchLoans(@RequestHeader("Authorization") String token,
			@RequestParam(defaultValue = "") String loanNo, @RequestParam(defaultValue = "") String firstName,
			@RequestParam(defaultValue = "") String lastName) {
		try {
			return new ResponseEntity<>(loanService.searchLoans(loanNo, firstName, lastName),
					HttpStatus.OK);
		} catch (LoanNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteLoan/{loanNo}") // only Admin
	public ResponseEntity<?> deleteLoan(@RequestHeader("Authorization") String token,
			@PathVariable("loanNo") String loanNo) {
		try {
			return new ResponseEntity<>(loanService.deleteLoan(loanNo), HttpStatus.OK);
		} catch (LoanNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateLoan/{loanNo}") // only Admin
	public ResponseEntity<?> updateLoan(@RequestHeader("Authorization") String token, @RequestBody LoanEntity loan,
			@PathVariable("loanNo") String loanNo) {
		try {
			return new ResponseEntity<>(loanService.updateLoan(loan, loanNo), HttpStatus.OK);
		} catch (LoanNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getLoans/{username}") // user's loan
	public ResponseEntity<?> getLoansByUsername(@RequestHeader("Authorization") String token,
			@PathVariable("username") String username, @RequestParam(defaultValue = "") String loanNo) {
		try {
			UserEntity user = loanService.getUser(username);
			return new ResponseEntity<List<LoanEntity>>(loanService.getLoansByUser(user, loanNo), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/createLoan") // only Admin
	public ResponseEntity<?> createLoan(@RequestHeader("Authorization") String token,
			@Valid @RequestBody LoanEntity loan) {
		try {
			return new ResponseEntity<>(loanService.createLoan(loan), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/getLoan/{loanNo}") // user's loan
	public ResponseEntity<?> getLoanById(@RequestHeader("Authorization") String token,
			@PathVariable("loanNo") String loanNo) {
		try {
			return new ResponseEntity<LoanEntity>(loanService.getLoanById(loanNo), HttpStatus.OK);
		} catch (LoanNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
