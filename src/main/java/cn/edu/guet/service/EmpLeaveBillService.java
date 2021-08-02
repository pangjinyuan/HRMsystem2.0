package cn.edu.guet.service;

import cn.edu.guet.bean.EmpLeaveBill;
import cn.edu.guet.common.IPage;
import cn.edu.guet.vo.EmpLeaveBillVo;

import java.util.List;

public interface EmpLeaveBillService {
    /**
     * 查询请假单并集合成vo
     * @param empId
     * @return
     */
    List<EmpLeaveBillVo> queryEmpLeaveBillsByEmpId(String empId, IPage iPage);

    Long getDataCount();

    EmpLeaveBillVo getEmpLeaveByKey(Long empLeaveKey);

    /**
     * 添加请假单
     * @param empLeaveBillVo
     */
    void addLeaveBill(EmpLeaveBillVo empLeaveBillVo);

    EmpLeaveBill queryEmpLeaveBillByEmpId(Integer id);

    int saveEmpLeaveBill(EmpLeaveBill empLeaveBill);
}
