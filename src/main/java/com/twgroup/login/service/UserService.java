package com.twgroup.login.service;

import com.twgroup.login.model.RoleType;
import com.twgroup.login.model.User;
import com.twgroup.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void joinUser(User user) {
        String rawPassword=user.getPassword();
        String encPassword=encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

//    @Transactional(readOnly = true)
//    public User login(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
//    }
}
