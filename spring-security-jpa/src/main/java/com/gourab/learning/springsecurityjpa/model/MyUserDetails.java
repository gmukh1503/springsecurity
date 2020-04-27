package com.gourab.learning.springsecurityjpa.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private boolean active;
	private List<GrantedAuthority> grantedAuthorityList;

	public MyUserDetails() {
		
	}
	
	public MyUserDetails(User user) {
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.active = user.isActive();
		this.grantedAuthorityList = Arrays.asList(user.getRoles().split(",")).stream()
								.map(SimpleGrantedAuthority::new)
								.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorityList;
	}

	@Override
	public String getPassword() {
		
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.active;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.active;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.active;
	}

	@Override
	public boolean isEnabled() {
		return this.active;
	}

}
