package cn.edu.guet.mapper;

import cn.edu.guet.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    /**
     *查询所有用户信息
     */
    List<User> getAll();
    /**
     * 根据id删除用户
     */
    int delete(String id);

    /**
     *新增用户
     */
    int add(User user);
    /**
     *通过用户id查询
     */
    User getUserById(String id);
    /**
     * 更新用户信息
     * @return
     */
    int update(User user);

//    List<User> findByName(String name);
    List<User> findByName(String name);



}

