package com.apinote.model.dto;

import com.apinote.model.UserRole;

public record UserDTO(String name, String email, String login, UserRole role, String password, String confirmPassword) {

}
