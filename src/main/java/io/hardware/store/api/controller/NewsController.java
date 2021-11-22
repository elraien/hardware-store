package io.hardware.store.api.controller;

import io.hardware.store.api.model.News;
import io.hardware.store.api.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(method = RequestMethod.POST, value = "/news/{userId}")
    News addNews(@PathVariable Long userId, @RequestBody News news) {
        return newsService.addNews(userId, news);
    }
}
