package com.cts.loan.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.cts.loan.entity.RolesEntity;
import com.cts.loan.entity.UserEntity;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String username;
	private String userpassword;
	private List<GrantedAuthority> authorities;

	public UserPrincipal(UserEntity user) {
		this.username = user.getUsername();
		this.userpassword = user.getUserpassword();
		RolesEntity role = user.getRoles();
		this.authorities = Stream.of(role.getRoleName()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return userpassword;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
