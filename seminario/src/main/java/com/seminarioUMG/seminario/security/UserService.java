package com.seminarioUMG.seminario.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seminarioUMG.seminario.model.Rol;
import com.seminarioUMG.seminario.model.User;
import com.seminarioUMG.seminario.model.UserPermission;
import com.seminarioUMG.seminario.services.UserRepository;
import com.seminarioUMG.seminario.services.UserRoleService;



@Service("userDetailsService")
public class UserService implements UserDetailsService  {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleService userRoles;

	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findOneByUsername(username);
		if(user==null){
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
				 true, true, true, true, getGrantedAuthorities(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<UserPermission> roles = userRoles.findRoles(user.getUsername());
		
		
		
		for(UserPermission rol:roles){
			authorities.add(new SimpleGrantedAuthority(rol.getRoles().getDescripcion())); 
		}
		return authorities;
	}

}
