package com.cts.loan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.loan.entity.LoanEntity;
import com.cts.loan.entity.UserEntity;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, String> {
	List<LoanEntity> findByLoanNoContainingIgnoreCaseAndFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
			String loanNo, String firstName, String lastName);

	List<LoanEntity> findByUser(UserEntity user);

	List<LoanEntity> findByUserAndLoanNoContainingIgnoreCase(UserEntity user, String loanNo);
}
