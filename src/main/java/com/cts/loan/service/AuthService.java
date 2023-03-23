package com.cts.loan.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.loan.entity.UserEntity;
import com.cts.loan.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> custuser = userRepository.findByUsername(username);
		return custuser.map(UserPrincipal::new).orElseThrow(() -> new UsernameNotFoundException("Bad credentials"));
	}
}
