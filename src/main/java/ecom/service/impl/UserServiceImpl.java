package ecom.service.impl;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ecom.entity.Role;
import ecom.entity.User;
import ecom.exception.ResourceNotFoundException;
import ecom.repository.UserRepository;
import ecom.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 🔹 Register a new user
    @Override
    public User registerUser(String username, String password, Role role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new ResourceNotFoundException("❌ Username already exists");
        }

        User user = new User(
                username,
                passwordEncoder.encode(password),
                role
        );

        return userRepository.save(user);
    }

    // 🔹 Find by username
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
