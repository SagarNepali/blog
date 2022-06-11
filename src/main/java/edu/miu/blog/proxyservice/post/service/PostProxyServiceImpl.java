package edu.miu.blog.proxyservice.post.service;

import edu.miu.blog.proxyservice.post.dto.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostProxyServiceImpl implements PostProxyService{

    private static final String POST_GET_ALL_URI="http://localhost:8080/api/v1/posts";
    private static final String POST_WITH_ID_URI="http://localhost:8080/api/v1/posts/{id}";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public Post getById(Long id) {
        return null;
    }

    @Override
    public Post update(Long id, Post p) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Post save(Post p) {
        return null;
    }
}
