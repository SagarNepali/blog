package edu.miu.blog.service;

import edu.miu.blog.proxyservice.comment.dto.Comment;
import edu.miu.blog.proxyservice.comment.service.CommentProxyService;
import edu.miu.blog.proxyservice.post.dto.Post;
import edu.miu.blog.proxyservice.post.service.PostProxyService;
import edu.miu.blog.proxyservice.user.dto.User;
import edu.miu.blog.proxyservice.user.service.UserProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/* Service */
@Service
@Transactional
public class BlogService {

    @Autowired
    PostProxyService postProxyService;

    @Autowired
    CommentProxyService commentProxyService;

    @Autowired
    UserProxyService userProxyService;


    public List<Post> getAllPosts(){
        return postProxyService.getAll();
    }

    public List<Post> getAllPostsByUserId(Long userId){ return postProxyService.getAllPostByUserId(userId);}

    public List<Comment> getAllCommentByPostId(Long postId){ return commentProxyService.getAllCommentByPostId(postId);}

    public void savePost(Post p){
        postProxyService.save(p);
    }

    public void deletePost(Long postId){
        postProxyService.delete(postId);
        commentProxyService.deleteAllCommentByPostId(postId);
    }
    public void deleteAllPostByUserId(Long userId){
        postProxyService.deleteAllPostsByUserId(userId);
        commentProxyService.deleteAllCommentByUserId(userId);
    }

    public Post getPostById(Long postId) throws Exception {
        Post p =  postProxyService.getById(postId);
        List<Comment> commentList;
        if(p != null){
            commentList = commentProxyService.getAllCommentByPostId(postId);
            p.setCommentList(commentList);
        }
        return p;
    }

    public List<Comment> getAllComments(){
        return commentProxyService.getAll();
    }

    public void saveComment(Comment p){
        commentProxyService.add(p);
    }

    public void deleteComment(Long id){
        commentProxyService.delete(id);
    }

    public Comment getCommentById(Long id){
        return  new Comment().builder().message("FIND BY ID NOT PRESENT").build();
//        return commentProxyService.(id);

    }

    public List<User> getAllUsers(){
        return userProxyService.getAll();
    }

    public void saveUser(User p){
        userProxyService.create(p);
    }

    public String deleteUser(Long id){
        deleteAllPostByUserId(id);
        return userProxyService.delete(id);
    }



    public User getUserById(Long id){
        return (User)userProxyService.get(id);
    }

    public void updateUser(User user, Long id){
        userProxyService.update(user, id);
    }

}
