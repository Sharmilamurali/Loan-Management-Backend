package com.cts.loan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.loan.entity.LoanEntity;
import com.cts.loan.entity.UserEntity;
import com.cts.loan.exception.LoanNotFoundException;
import com.cts.loan.exception.UserNotFoundException;
import com.cts.loan.repository.LoanRepository;
import com.cts.loan.repository.UserRepository;

@Service

public class LoanService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoanRepository loanRepository;

	public List<LoanEntity> getAllLoan() throws LoanNotFoundException {
		try {
			List<LoanEntity> loans = loanRepository.findAll();
			return loans;
		} catch (Exception e) {
			throw new LoanNotFoundException("No Entries Found");
		}
	}

	public List<LoanEntity> searchLoans(String loanNo, String firstName, String lastName)
			throws LoanNotFoundException {
		try {
			List<LoanEntity> loans = loanRepository
					.findByLoanNoContainingIgnoreCaseAndFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
							loanNo, firstName, lastName);
			return loans;
		} catch (Exception e) {
			throw new LoanNotFoundException("Loan Not Found");
		}
	}

	public LoanEntity updateLoan(LoanEntity loan, String loanNo) throws LoanNotFoundException {

		Optional<LoanEntity> opt = loanRepository.findById(loanNo);
		if (opt.isPresent()) {
			return loanRepository.save(loan);
		} else {
			throw new LoanNotFoundException("Loan Not found");
		}
	}

	public LoanEntity createLoan(LoanEntity loan) throws LoanNotFoundException {
		try {
			LoanEntity save = loanRepository.save(loan);
			return save;
		} catch (Exception e) {
			throw new LoanNotFoundException("Loan Not Added");
		}
	}

	public String deleteLoan(String loanNo) throws LoanNotFoundException {
		Optional<LoanEntity> opt = loanRepository.findById(loanNo);
		if (opt.isPresent()) {
			loanRepository.deleteById(loanNo);
			return "Deleted";
		} else {
			throw new LoanNotFoundException("No such loan exists");
		}
	}

	public UserEntity getUser(String username) throws UserNotFoundException {
		Optional<UserEntity> opt = userRepository.findById(username);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new UserNotFoundException("User Not Found ");
		}
	}

	public List<LoanEntity> getLoansByUser(String username, String loanNo) throws LoanNotFoundException {
		try {
			UserEntity user = getUser(username);
			if (loanNo.isEmpty()) {
				return loanRepository.findByUser(user);
			} else {
				return loanRepository.findByUserAndLoanNoContainingIgnoreCase(user, loanNo);
			}
		} catch (Exception e) {
			throw new LoanNotFoundException("Loan Not Found");
		}
	}

	public LoanEntity getLoanById(String loanNo) throws LoanNotFoundException {
		Optional<LoanEntity> opt = loanRepository.findById(loanNo);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new LoanNotFoundException("NOT FOUND");
		}
	}
}
