package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.service.PostService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page{

    private final PostService postService;
    private Post post;

    public PostPage(PostService postService, UserService userService) {
        this.postService = postService;
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable String id, Model model) {
        try {
            post = postService.findById(Long.parseLong(id));
            model.addAttribute("postA", post);
            model.addAttribute("comment", new Comment());
            return "PostPage";
        } catch (NumberFormatException e){
            return "PostPage";
        }
    }

    @AnyRole({Role.Name.WRITER, Role.Name.ADMIN})
    @PostMapping("/post/{id}")
    public String commentPost(@PathVariable String id, @Valid @ModelAttribute("comment") Comment comment,
                              BindingResult bindingResult,
                              HttpSession httpSession) {
        try {
            post = postService.findById(Long.parseLong(id));
            if (post == null) {
                return "redirect:/";
            }
            if (bindingResult.hasErrors()) {
                return "PostPage";
            }
            comment.setUser(getUser(httpSession));
            postService.writeComment(comment, post);
            putMessage(httpSession, "You published new comment");
            return "PostPage";
        } catch (NumberFormatException e){
            return "PostPage";
        }
    }
}
