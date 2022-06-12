package edu.miu.blog.proxyservice.user.service;

import edu.miu.blog.proxyservice.user.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserProxyServiceImpl implements UserProxyService {

    @Autowired
    private RestTemplate restTemplate;
    private final String userUrl = "http://localhost:8082/api/users/{id}";
    private final String pplUrl = "http://localhost:8082/api/users";

    @Override
    public List<User> getAll() {
        ResponseEntity<List<User>> response = restTemplate
                .exchange(pplUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<User>>() {});
        return response.getBody();
    }

    @Override
    public User get(Long id) {
        return restTemplate.getForObject(userUrl, User.class, id);
    }

    @Override
    public User create(User user) {
        return restTemplate.postForObject(pplUrl, user, User.class);
    }

    @Override
    public void delete(Long id) {
        restTemplate.delete(userUrl, id);
    }

    @Override
    public void update(User user) {
        restTemplate.put(userUrl, user, user.getId());
    }

}
