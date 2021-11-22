package io.hardware.store.api.service;

import io.hardware.store.api.exception.ItemNotFoundException;
import io.hardware.store.api.model.user.UserRoleType;
import io.hardware.store.api.model.user.User;
import io.hardware.store.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {
    Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long id) {
        logger.info("Searching for User with UserId '" + id);
        var user = userRepository.findById(id).orElse(null);

        if (user != null) {
            return user;
        } else {
            throw new ItemNotFoundException(id);
        }
    }

    public boolean checkUserRole(Long id, UserRoleType roleType) {
        var user = findUserById(id);
        return roleType.equals(user.getRole());
    }
}
