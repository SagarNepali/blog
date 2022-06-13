package edu.miu.blog.controller;

import edu.miu.blog.proxyservice.comment.dto.Comment;
import edu.miu.blog.proxyservice.comment.service.CommentProxyService;
import edu.miu.blog.proxyservice.post.dto.Post;
import edu.miu.blog.proxyservice.post.service.PostProxyService;
import edu.miu.blog.proxyservice.user.dto.User;
import edu.miu.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogRestController {

    @Autowired
    BlogService blogService;

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return blogService.getAllPosts();
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return blogService.getAllUsers();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        blogService.saveUser(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        blogService.savePost(post);
        return new ResponseEntity<>("Post created", HttpStatus.CREATED);
    }

    @PostMapping("/users/{userId}/posts/{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createComment(@RequestBody Comment comment, @PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        comment.setUserId(userId);
        comment.setPostId(postId);
        blogService.saveComment(comment);
        return new ResponseEntity<>("Post created", HttpStatus.CREATED);
    }


    @GetMapping("/posts/{postId}")
    public Post getPostById(@PathVariable("postId") Long postId){
        return blogService.getPostById(postId);
    }

    @GetMapping("/posts/users/{userId}")
    public List<Post> getAllPostByUserId(@PathVariable("userId") Long userId){
        return blogService.getAllPostsByUserId(userId);
    }

    //show all comment of a Post
    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getAllCommentByPostId(@PathVariable("postId") Long postId){
        return blogService.getAllCommentByPostId(postId);
    }





}
