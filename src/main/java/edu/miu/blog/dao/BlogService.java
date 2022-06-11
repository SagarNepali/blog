package edu.miu.blog.dao;

import edu.miu.blog.proxyservice.post.dto.Post;
import edu.miu.blog.proxyservice.post.service.PostProxyService;
import edu.miu.blog.proxyservice.user.service.UserProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final UserProxyService userService;
    private final PostProxyService postProxyService;

    public List<Post> getAllPost(){
        return postProxyService.getAll();
    }

    public Post getPostByUserId(Long userId){
        return postProxyService.getById(userId);
    }

}
