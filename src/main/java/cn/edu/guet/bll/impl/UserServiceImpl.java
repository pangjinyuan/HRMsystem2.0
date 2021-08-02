package cn.edu.guet.bll.impl;


import cn.edu.guet.bean.User;
import cn.edu.guet.bll.UserService;
import cn.edu.guet.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper mapper;


    @Override
    public List<User> getAll() {
        return mapper.getAll();
    }

    @Override
    public int delete(String id) {
        return mapper.delete(id);
    }

    @Override
    public int add(User user) {
        return mapper.add(user);
    }

    @Override
    public User getUserById(String id) {
        return mapper.getUserById(id);
    }

    @Override
    public int update(User user) {
        return mapper.update(user);
    }

    @Override
    public List<User> findByName(String name) {
        return mapper.findByName(name);
    }


}


