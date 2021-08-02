package cn.edu.guet.mapper;

import cn.edu.guet.bean.Change;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangeMapper {
    /**
     *查询所有用户信息
     */
    List<Change> getAll();
    /**
     * 根据id删除用户
     */
    int delete(String id);

    /**
     *新增用户
     */
    int add(Change change);
    /**
     *通过用户id查询
     */
    Change getUserById(String id);
    /**
     * 更新用户信息
     * @return
     */
    int update(Change change);

//    List<User> findByName(String name);
    List<Change> findByName(String name);



}

