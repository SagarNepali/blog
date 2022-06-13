package edu.miu.blog.proxyservice.comment.service;

import edu.miu.blog.proxyservice.comment.dto.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CommentProxyServiceImpl implements CommentProxyService {

    private static final String COMMENT_GET_ALL_URI ="http://localhost:8083/api/v1/comments";
    private static final String COMMENT_WITH_ID_URI ="http://localhost:8083/api/v1/comments/{id}";
    private static final String COMMENT_WITH_POSTID_URI ="http://localhost:8083/api/v1/comments/post/{id}";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Comment> getAll(){
        ResponseEntity<List<Comment>> response =
                restTemplate.exchange(COMMENT_GET_ALL_URI, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Comment>>() {});
        return response.getBody();
    }

    @Override
    public List<Comment> getAllCommentByPostId(Long id){
        UriComponents uri = UriComponentsBuilder
                .fromHttpUrl(COMMENT_WITH_POSTID_URI)
                .buildAndExpand(id);

        String requiredUrl = uri.toUriString();

        ResponseEntity<List<Comment>> response =
                restTemplate.exchange(requiredUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Comment>>() {});
        return response.getBody();
    }

    @Override
    public Long add(Comment comment){
        URI uri = restTemplate.postForLocation(COMMENT_GET_ALL_URI, comment);
        if (uri == null) { return null; }
        Matcher m = Pattern.compile(".*/person/(\\d+)").matcher(uri.getPath());
        m.matches();
        return Long.parseLong(m.group(1));
    }

    @Override
    public Comment update(Comment comment,Long id){
        restTemplate.put(COMMENT_WITH_ID_URI, comment, id);
        return null;
    }

    @Override
    public void delete(Long id){
        restTemplate.delete(COMMENT_WITH_ID_URI, id);
    }


}
