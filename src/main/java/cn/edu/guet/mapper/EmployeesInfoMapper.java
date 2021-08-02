package cn.edu.guet.mapper;

import cn.edu.guet.bean.EmployeesInfo;
import cn.edu.guet.dto.DepartmentPartDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity generator.domain.EmployeesInfoTable
 */
@Repository
public interface EmployeesInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(EmployeesInfo record);

    int insertSelective(EmployeesInfo record);

    EmployeesInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EmployeesInfo record);

    int updateByPrimaryKey(EmployeesInfo record);

    DepartmentPartDto getCurrentUserDepartmentDto(@Param("empId") String userId);

    EmployeesInfo queryLeaderByEmpId(String empId);

    List<EmployeesInfo> queryEmpLeaderByHeadLike(@Param("empId") String nameLike);
}




