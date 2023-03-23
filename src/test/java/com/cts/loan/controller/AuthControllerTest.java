package com.cts.loan.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.loan.entity.AuthRequest;
import com.cts.loan.service.AuthService;
import com.cts.loan.service.JwtUtil;
import com.cts.loan.service.UserPrincipal;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = AuthController.class)
public class AuthControllerTest {

	private MockMvc mockMvc;

	@Mock
	private JwtUtil jwtutil;

	@Mock
	private AuthService authService;

	@Mock
	private AuthenticationManager authenticationManager;

	@InjectMocks
	private AuthController authController;

	private AuthRequest authrequest1;
	private UserDetails user1;
	private AutoCloseable closeable;

	@BeforeEach
	public void setUp() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
		authrequest1 = new AuthRequest("Admin1", "pwd4");
		user1 = new UserPrincipal(null);
	}

	@Test
	public void testAuthenticate() throws Exception {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authrequest1.getUsername(), authrequest1.getUserpassword()));
		when(authService.loadUserByUsername("Admin1")).thenReturn(user1);
		when(jwtutil.generateToken(user1)).thenReturn(
				"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBZG1pbjEiLCJyb2xlIjoiW0FkbWluXSIsImlhdCI6MTY3NDgwNTEwOSwiZXhwIjoxNjc0ODA2MDA5fQ.bITkb7Y0iBmA0-nQIiiBuw9pZyRDrWbZF-0b2MO18AQ");
		mockMvc.perform(
				post("/loan/authenticate").contentType(MediaType.APPLICATION_JSON).content(asJsonString(authrequest1)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	public static String asJsonString(final Object obj) {
		try {
			ObjectMapper objmapper = new ObjectMapper();
			return objmapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@AfterEach
	public void releaseMocks() throws Exception {
		closeable.close();
	}
}
