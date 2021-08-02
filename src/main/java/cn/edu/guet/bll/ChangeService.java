package cn.edu.guet.bll;




import cn.edu.guet.bean.Change;

import java.util.List;

public interface ChangeService {

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
     */
    int update(Change change);

    List<Change> findByName(String name);

    int updateByPostKey(Change change);


}

