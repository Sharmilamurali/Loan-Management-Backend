package com.cts.loan.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.cts.loan.service.AuthService;
import com.cts.loan.service.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private AuthService authService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		String authorizationHeader = httpServletRequest.getHeader("Authorization");
		String token1 = null;
		String username = null;
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			token1 = authorizationHeader.substring(7);
			try {
				username = jwtutil.extractUsername(token1);
			} catch (Exception e) {
				httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expire token");
			}
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = authService.loadUserByUsername(username);
			if (jwtutil.validateToken(token1, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expire token");
			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
