package cn.edu.guet.bll.impl;


import cn.edu.guet.bean.Change;
import cn.edu.guet.bll.ChangeService;
import cn.edu.guet.config.Log;
import cn.edu.guet.mapper.ChangeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ChangeServiceImpl implements ChangeService {
    @Resource
    ChangeMapper mapper;


    @Override
    public List<Change> getAll() {
        return mapper.getAll();
    }

    @Override
    public int delete(String id) {
        return mapper.delete(id);
    }

    @Override
    public int add(Change change) {
        return mapper.add(change);
    }

    @Override
    public Change getUserById(String id) {
        return mapper.getUserById(id);
    }

    @Override
    @Log("员工修改信息")
    public int update(Change change) {
        return mapper.update(change);
    }

    @Override
    public List<Change> findByName(String name) {
        return mapper.findByName(name);
    }
    public int updateByPostKey(Change change){
        return this.mapper.updateByPostKey(change);
    }




}


