package ecom.service;

import ecom.dto.AuthRequest;
import ecom.dto.AuthResponse;
import ecom.dto.RegisterRequest;

public interface AuthService {
    AuthResponse login(AuthRequest request);
    String register(RegisterRequest request);
}

