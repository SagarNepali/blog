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

import javax.validation.Valid;
import java.util.List;
/* REST Controller*/
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
    public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
        blogService.saveUser(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createPost(@RequestBody @Valid Post post) {
        blogService.savePost(post);
        return new ResponseEntity<>("Post created", HttpStatus.CREATED);
    }

    @PostMapping("/users/{userId}/posts/{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createComment(@RequestBody @Valid Comment comment, @PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        comment.setUserId(userId);
        comment.setPostId(postId);
        blogService.saveComment(comment);
        return new ResponseEntity<>("Comment created", HttpStatus.CREATED);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Long id){
        return new ResponseEntity<>(blogService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user){
        User userInDatabase = blogService.getUserById(id);
        if(userInDatabase == null){
            return  new ResponseEntity<>("User doens't exist", HttpStatus.NOT_FOUND);
        }
        blogService.updateUser(user, id);
        return new ResponseEntity<>(blogService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        User response = blogService.getUserById(id);
        if(response == null){
            return new ResponseEntity<>("User does not exist with this id", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogService.deleteUser(id), HttpStatus.OK);
    }

//
    @GetMapping("/posts/{postId}")
    public Post getPostById(@PathVariable("postId") Long postId) throws Exception {
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


    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") Long userId){
        blogService.deleteUser(userId);
    }

    @DeleteMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long postId){
        blogService.deletePost(postId);
    }

}
