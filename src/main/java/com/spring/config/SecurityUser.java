package com.spring.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.model.User;
import com.spring.model.UserRole;

public class SecurityUser extends User implements UserDetails {

	private static final long serialVersionUID = 1L;

	public SecurityUser(User user){
		if(user != null)
		{
			this.setId(user.getId());
			this.setUsername(user.getUsername());
			this.setPassword(user.getPassword());
			this.setUserRole(user.getUserRole());
		}
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorirties = new ArrayList<>();
		Set<UserRole> userRoles = this.getUserRole();
		
		if(userRoles != null)
		{
			for(UserRole role : userRoles)
			{
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
				authorirties.add(authority);
			}
		}
		return authorirties;
	}

	public String getPassword() {
		return super.getPassword();
	}

	public String getUsername() {
		return super.getUsername();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

}
