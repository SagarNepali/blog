package edu.miu.blog.proxyservice.comment.service;

import edu.miu.blog.proxyservice.comment.dto.Comment;


import java.util.List;

public interface  CommentProxyService {

    List<Comment> getAll();

    Long add(Comment comment);

    Comment update(Comment p,Long id);

    void delete(Long id);

    List<Comment> getAllCommentByPostId(Long id);

    //Comment save(Comment p);

}
