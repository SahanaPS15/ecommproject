package ecom.dto;

import ecom.entity.Role;

public record UserResponse(
        Long id,
        String username,
        Role role
        
) {}
