package in.info.bharat.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.info.bharat.model.Manager;
import in.info.bharat.repository.UserRepository;

/**
 * Bharat patel
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Manager user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public Manager save(Manager manager) {
		Manager newUser = new Manager();
		newUser.setUsername(manager.getUsername());
		newUser.setPassword(bcryptEncoder.encode(manager.getPassword()));
		newUser.setFirstname(manager.getFirstname());
		newUser.setLastname(manager.getLastname());
		newUser.setCompany(manager.getCompany());
		newUser.setEmail(manager.getEmail());
		return userDao.save(newUser);
	}

}