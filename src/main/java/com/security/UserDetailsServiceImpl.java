package com.security;

import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDetailsRepository;
import com.dao.UserDetailsRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDetailsRepository userDetailsDAO;
	@Autowired
	HttpSession session;
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) {
		com.model.UserDetails user = userDetailsDAO.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		} else {
			session.setAttribute("userid", user.getUser_id());
			session.setAttribute("username", user.getUsername());
		}
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getClaim()));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				grantedAuthorities);
	}
}