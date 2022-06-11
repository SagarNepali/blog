package edu.miu.blog.proxyservice.user.service;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserProxyService<T> {
    public abstract T get(Long id);
    public abstract List<T> getAll();
    public abstract T create(T t);
    public abstract void delete(Long id);
    public abstract void update(T t);
}