package edu.miu.blog.controller;

import edu.miu.blog.proxyservice.comment.dto.Comment;
import edu.miu.blog.proxyservice.comment.service.CommentProxyService;
import edu.miu.blog.proxyservice.post.dto.Post;
import edu.miu.blog.proxyservice.post.service.PostProxyService;
import edu.miu.blog.proxyservice.user.dto.User;
import edu.miu.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogRestController {

    @Autowired
    BlogService blogService;

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return blogService.getAllPosts();
    }

    @GetMapping("/comments")
    public List<Comment> getAllComments(){
        return blogService.getAllComments();
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return blogService.getAllUsers();
    }

}
