package ecom.service;

import java.util.Optional;

import ecom.entity.Role;
import ecom.entity.User;

public interface UserService {
	 User registerUser(String username, String password, Role role);
	    Optional<User> findByUsername(String username);
}
