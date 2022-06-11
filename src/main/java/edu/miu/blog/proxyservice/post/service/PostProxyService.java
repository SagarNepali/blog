package edu.miu.blog.proxyservice.post.service;

import edu.miu.blog.proxyservice.post.dto.Post;

import java.util.List;

public interface PostProxyService {

    List<Post> getAll();

    Post getById(Long id);

    Post update(Long id, Post p);

    void delete(Long id);

    Post save(Post p);
}
