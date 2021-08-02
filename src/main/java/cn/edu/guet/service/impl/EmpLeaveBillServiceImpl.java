package cn.edu.guet.service.impl;

import cn.edu.guet.bean.EmpLeaveBill;
import cn.edu.guet.common.HttpUtils;
import cn.edu.guet.common.IPage;
import cn.edu.guet.dto.DepartmentPartDto;
import cn.edu.guet.mapper.EmpLeaveBillMapper;
import cn.edu.guet.mapper.EmployeesInfoMapper;
import cn.edu.guet.service.EmpLeaveBillService;
import cn.edu.guet.vo.EmpLeaveBillVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpLeaveBillServiceImpl implements EmpLeaveBillService {
    @Autowired
    EmpLeaveBillMapper empLeaveBillMapper;
    @Autowired
    EmployeesInfoMapper employeesInfoMapper;
    @Override
    public List<EmpLeaveBillVo> queryEmpLeaveBillsByEmpId(String empId, IPage iPage) {
        List<EmpLeaveBill> empLeaveBill = empLeaveBillMapper.queryEmpLeaveBillsByEmpId(empId,iPage.config());
        List<EmpLeaveBillVo> empLeaveBillVos = new ArrayList<>();
        for (EmpLeaveBill leaveBill : empLeaveBill) {
            EmpLeaveBillVo empLeaveBillVo = new EmpLeaveBillVo();
            BeanUtils.copyProperties(leaveBill, empLeaveBillVo);
            empLeaveBillVos.add(empLeaveBillVo);
        }
        return empLeaveBillVos;
    }


    @Override
    public Long getDataCount() {
        return empLeaveBillMapper.getDataCount();
    }

    @Override
    public EmpLeaveBillVo getEmpLeaveByKey(Long empLeaveKey) {
        EmpLeaveBill empLeaveBill = empLeaveBillMapper.selectByPrimaryKey(Math.toIntExact(empLeaveKey));
        EmpLeaveBillVo empLeaveBillVo = new EmpLeaveBillVo();
        BeanUtils.copyProperties(empLeaveBill,empLeaveBillVo);
        return empLeaveBillVo;
    }

    @Override
    @Transactional
    public void addLeaveBill(EmpLeaveBillVo empLeaveBillVo) {
        String userId = HttpUtils.getAttribute("emp_id", String.class);
        //获取领导和部门的基本信息
        DepartmentPartDto currentUserDepartmentDto = employeesInfoMapper.getCurrentUserDepartmentDto(userId);
        EmpLeaveBill empLeaveBill = new EmpLeaveBill();
        BeanUtils.copyProperties(empLeaveBillVo,empLeaveBill);

        empLeaveBill.setLeaveHandler(currentUserDepartmentDto.getDeptBossId());
        empLeaveBill.setDeptId(currentUserDepartmentDto.getDeptId());
        empLeaveBill.setEmpId(userId);
        empLeaveBill.setEmpState("0");
        //添加到数据库
        empLeaveBillMapper.insert(empLeaveBill);
    }

    @Override
    public EmpLeaveBill queryEmpLeaveBillByEmpId(Integer id) {
        return this.empLeaveBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveEmpLeaveBill(EmpLeaveBill empLeaveBill) {
        return this.empLeaveBillMapper.updateByPrimaryKeySelective(empLeaveBill);
    }
}
