package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.PostCredentials;
import ru.itmo.wp.service.JwtService;
import ru.itmo.wp.service.PostService;
import ru.itmo.wp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final JwtService jwtService;

    public PostController(PostService postService, UserService userService, JwtService jwtService) {
        this.postService = postService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("posts")
    public List<Post> findPosts() {
        return postService.findAll();
    }

    @PostMapping("newPost")
    public String create(@RequestBody @Valid PostCredentials postCredentials, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        Post post = new Post();
        post.setTitle(postCredentials.getTitle());
        post.setText(postCredentials.getText());
        User user = jwtService.find(postCredentials.getJwt());
        post.setUser(user);
        postService.save(user, post);
        return "";
    }
}
