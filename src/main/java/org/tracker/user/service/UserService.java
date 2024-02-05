package org.tracker.user.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.tracker.user.domain.UserDTO;
import org.tracker.user.mapper.UserMapper;


@Service
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Long login(String id, String password) {
        UserDTO user = userMapper.findUserByIdAndPassword(id, password);
        logger.debug("user: " + user.toString());
        if(user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user.getNum();
        } else {
            return null;
        }
    }

    public UserDTO register(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(user);
        return user;
    }
}
