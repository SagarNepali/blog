package edu.miu.blog.proxyservice.post.service;

import edu.miu.blog.proxyservice.post.dto.Post;

import java.util.List;

public interface PostProxyService {

    List<Post> getAll();

    Post getById(Long id);

    List<Post> getAllPostByUserId(Long userId);

    void update(Long id, Post p);

    void delete(Long id);

    void deleteAllPostsByUserId(Long userId);

    void save(Post p);
}
