package cn.edu.guet.mapper;

import cn.edu.guet.dto.UserAndNameDto;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAndNameMapper {
    UserAndNameDto getUserEntityByEmpId(String empId);
}
