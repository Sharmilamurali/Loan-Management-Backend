package com.cts.loan.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.cts.loan.entity.LoanEntity;
import com.cts.loan.entity.UserEntity;
import com.cts.loan.exception.LoanNotFoundException;
import com.cts.loan.exception.UserNotFoundException;
import com.cts.loan.repository.LoanRepository;
import com.cts.loan.repository.UserRepository;

@SpringBootTest
public class LoanServiceTest {

	@InjectMocks
	LoanService loanService;

	@Mock
	LoanRepository loanRepository;

	@Mock
	UserRepository userRepository;

	private List<LoanEntity> loanlist = new ArrayList<>();
	private Optional<LoanEntity> optionalloan;
	private LoanEntity loan1;
	private LoanEntity loan2;
	private UserEntity user1;
	private Optional<UserEntity> optionaluser;
	private AutoCloseable closeable;

	@BeforeEach
	public void setUp() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
		loan1 = new LoanEntity("200", "Education Loan", "6yrs", "7 lakh", "Sha", "Laa", "Chennai");
		loan2 = new LoanEntity("201", "Business Loan", "4yrs", "3 lakh", "Sha", "Laa", "Vellore");
		loanlist.add(loan1);
		loanlist.add(loan2);
		optionalloan = Optional.of(loan1);
		user1 = new UserEntity("1000", "pwd123", "User123", null, null);
		optionaluser = Optional.of(user1);

	}

	@Test
	public void testGetAllLoan() throws LoanNotFoundException {
		when(loanRepository.findAll()).thenReturn(loanlist);
		assertEquals(loanlist, loanService.getAllLoan());
	}

	@Test
	public void testsearchLoans() throws LoanNotFoundException {
		when(loanRepository
				.findByLoanNoContainingIgnoreCaseAndFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase("", "",
						""))
				.thenReturn(loanlist);
		List<LoanEntity> loan = loanService.searchLoans("", "", "");
		assertEquals(loanlist, loan);
	}

	@Test
	public void testUpdateLoan() throws LoanNotFoundException {
		when(loanRepository.findById("200")).thenReturn(optionalloan);
		when(loanRepository.save(loan1)).thenReturn(loan1);
		LoanEntity loan = loanService.updateLoan(loan1, "200");
		assertEquals(loan1, loan);
	}

	@Test
	public void testCreateLoan() throws LoanNotFoundException {
		when(loanRepository.save(loan1)).thenReturn(loan1);
		LoanEntity loan = loanService.createLoan(loan1);
		assertEquals(loan1, loan);
	}

	@Test
	public void testGetUser() throws UserNotFoundException {
		when(userRepository.findById("User123")).thenReturn(optionaluser);
		UserEntity user = loanService.getUser("User123");
		assertNotNull(user);
	}

	@Test
	public void testGetLoansByUserForIfCondition() throws LoanNotFoundException {
		when(loanRepository.findByUser(user1)).thenReturn(loanlist);
		List<LoanEntity> loan = loanService.getLoansByUser("User123", "");
		assertEquals(loanlist, loan);
	}

	@Test
	public void testGetLoansByUserForElseCondition() throws LoanNotFoundException {
		when(loanRepository.findByUserAndLoanNoContainingIgnoreCase(user1, "20")).thenReturn(loanlist);
		List<LoanEntity> loan = loanService.getLoansByUser("User123", "20");
		assertEquals(loanlist, loan);
	}

	@Test
	public void testDeleteLoan() throws LoanNotFoundException {
		when(loanRepository.findById("200")).thenReturn(optionalloan);
		String msg = loanService.deleteLoan("200");
		assertEquals("Deleted", msg);
	}

	@Test
	public void testGetLoanById() throws LoanNotFoundException {
		when(loanRepository.findById("200")).thenReturn(optionalloan);
		LoanEntity loan = loanService.getLoanById("200");
		assertNotNull(loan);
	}

	@AfterEach
	public void releaseMocks() throws Exception {
		closeable.close();
	}
}
