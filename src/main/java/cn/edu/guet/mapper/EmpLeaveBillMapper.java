package cn.edu.guet.mapper;


import cn.edu.guet.bean.EmpLeaveBill;
import cn.edu.guet.common.IPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity cn.edu.guet.bean.EmpLeaveTable
 */
@Repository
public interface EmpLeaveBillMapper {

    int deleteByPrimaryKey(Long id);

    int insert(EmpLeaveBill record);

    int insertSelective(EmpLeaveBill record);

    EmpLeaveBill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmpLeaveBill record);

    int updateByPrimaryKey(EmpLeaveBill record);

    List<EmpLeaveBill> queryEmpLeaveBillsByEmpId(@Param("empId") String empId, @Param("iPage") IPage iPage);

    Long getDataCount();
}




