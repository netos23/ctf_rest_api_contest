package ru.fbtw.ctf_task.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.fbtw.ctf_task.domain.Role;
import ru.fbtw.ctf_task.domain.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {


	private final Map<String, User> userRepo;
	private final PasswordEncoder passwordEncoder;

	public UserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		userRepo = new HashMap<>();
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.get(username);
	}

	public void addUser(User user) {
		user.setActive(true);
		user.setRoles(Collections.singleton(Role.USER));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.put(user.getUsername(), user);
	}

	public User findByLoginAndPassword(String login, String password) {
		User user = loadUserByUsername(login);
		if (user != null) {
			if (passwordEncoder.matches(password, user.getPassword())) {
				return user;
			}
		}
		return null;
	}
}
