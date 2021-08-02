package cn.edu.guet.service;

import cn.edu.guet.dto.UserAndNameDto;

public interface UserAndNameService {
    UserAndNameDto queryUserAndNameDto(String empId);
}
