package com.apinote.service;


import com.apinote.model.User;
import com.apinote.model.dto.UserDTO;

import java.util.List;

public interface AdminService{
    List<User> listUsers();
    User promoveToAdmin(Long userId);
    User demoteToUser(Long userId);
}
