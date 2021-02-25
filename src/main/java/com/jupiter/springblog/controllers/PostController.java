package com.jupiter.springblog.controllers;


import com.jupiter.springblog.models.Post;
import com.jupiter.springblog.models.User;
import com.jupiter.springblog.repositories.PostRepository;
import com.jupiter.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postsDao;
    private final EmailService emailService;

    public PostController(PostRepository postsDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts/jpa")
    @ResponseBody
    public List<Post> jpaIndex() {
        return postsDao.findAll();
    }


    @GetMapping("/posts/jpa/{id}")
    @ResponseBody
    public String viewJpaPost(@PathVariable long id) {
        return postsDao.getOne(id).toString();
    }


    @GetMapping("/posts/create")
    @ResponseBody
    public String postForm(){
        return "Create a post here!";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(@ModelAttribute Post post){
        return "Creating a new post...";

        Post savePost = postsDao.save(post);

        String subject = "New Post Created!";
        String body = "Dear" + savedPost.getUser.getUsername()
                + ". Thank you for creating an ad. Your ad id is "
                + savedPost.getId();
        emailService.prepareAndSend(savedPost, subject, body);

        return"redirect:/posts";
    }

}