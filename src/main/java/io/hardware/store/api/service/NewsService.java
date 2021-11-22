package io.hardware.store.api.service;


import io.hardware.store.api.exception.PermissionViolationException;
import io.hardware.store.api.model.News;
import io.hardware.store.api.model.user.UserRoleType;
import io.hardware.store.api.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class NewsService {
    Logger logger = Logger.getLogger(NewsService.class.getName());

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserService userService;

    public News addNews(Long userId, News news) {
        boolean  hasRequiredPermission = userService.checkUserRole(userId, UserRoleType.ADMIN);
        if (hasRequiredPermission) {
            logger.info("Adding news.");
            return newsRepository.save(news);
        } else {
            throw new PermissionViolationException(userId);
        }
    }
}