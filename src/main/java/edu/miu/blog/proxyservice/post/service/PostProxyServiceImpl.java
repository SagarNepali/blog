package edu.miu.blog.proxyservice.post.service;

import edu.miu.blog.proxyservice.post.dto.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PostProxyServiceImpl implements PostProxyService{

    private static final String POST_GET_ALL_URI="http://localhost:8081/api/v1/posts";
    private static final String POST_WITH_ID_URI="http://localhost:8081/api/v1/posts/{id}";
    private static final String POST_GET_ALL_POST_BY_USER_ID_URI="http://localhost:8081/api/v1/posts/users/{userId}";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Post> getAll() {

        ResponseEntity<List<Post>> response  = restTemplate.exchange(POST_GET_ALL_URI, HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>(){});
        return response.getBody();
    }

    @Override
    public List<Post> getAllPostByUserId(Long userId){
        Map<String, Long> params = new HashMap<>();
        params.put("userId",userId);
        ResponseEntity<List<Post>> response  = restTemplate.exchange(POST_GET_ALL_POST_BY_USER_ID_URI, HttpMethod.GET, null, new ParameterizedTypeReference<List<Post>>(){},params);
        return response.getBody();
    }

    @Override
    public Post getById(Long id) throws Exception {
        ResponseEntity<Post> p = restTemplate.getForObject(POST_WITH_ID_URI,ResponseEntity.class,id) ;
        if(p.getStatusCode()!= HttpStatus.OK){
            throw new Exception("Post not found with id: "+id);
        }
        return p.getBody();
    }

    @Override
    public void update(Long id, Post p) {
        restTemplate.put(POST_WITH_ID_URI,p,p.getId());
    }

    @Override
    public void delete(Long id) {
        restTemplate.delete(POST_WITH_ID_URI,id);
    }

    @Override
    public void deleteAllPostsByUserId(Long userId){
        restTemplate.delete(POST_GET_ALL_POST_BY_USER_ID_URI,userId);
    }

    @Override
    public void save(Post p) {
        restTemplate.postForObject(POST_GET_ALL_URI,p, Post.class);
    }
}
