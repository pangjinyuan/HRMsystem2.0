package cn.edu.guet.service.impl;

import cn.edu.guet.dto.UserAndNameDto;
import cn.edu.guet.mapper.UserAndNameMapper;
import cn.edu.guet.service.UserAndNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAndNameServiceImpl implements UserAndNameService {
    @Autowired
    UserAndNameMapper userAndNameMapper;

    @Override
    public UserAndNameDto queryUserAndNameDto(String empId) {
        return this.userAndNameMapper.getUserEntityByEmpId(empId);
    }
}
