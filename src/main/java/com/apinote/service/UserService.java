package com.apinote.service;


import com.apinote.model.User;
import com.apinote.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    User save(UserDTO userDTO);
    void delete(Long id);
    User listById(Long id);
    User update(Long id, UserDTO entity);
}
