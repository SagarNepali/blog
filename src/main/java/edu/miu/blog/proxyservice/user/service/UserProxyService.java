package edu.miu.blog.proxyservice.user.service;

import edu.miu.blog.proxyservice.user.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserProxyService {
    public abstract User get(Long id);
    public abstract List<User> getAll();
    public abstract User create(User user);
    public abstract String delete(Long id);
    public abstract User update(User user, Long id);
}
