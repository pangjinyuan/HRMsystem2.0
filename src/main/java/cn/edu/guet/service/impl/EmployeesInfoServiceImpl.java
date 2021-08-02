package cn.edu.guet.service.impl;

import cn.edu.guet.bean.EmployeesInfo;
import cn.edu.guet.common.HttpUtils;
import cn.edu.guet.mapper.EmployeesInfoMapper;
import cn.edu.guet.service.EmployeesInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesInfoServiceImpl implements EmployeesInfoService {
    @Autowired
    EmployeesInfoMapper employeesInfoMapper;

    @Override
    public void DepositInfo(String empId) {
        EmployeesInfo employeesInfo = this.employeesInfoMapper.queryLeaderByEmpId(empId);
        //获得内容信息
        HttpUtils.setAttribute("leader", employeesInfo.getLeader());
    }

    @Override
    public EmployeesInfo queryEmpLeaderByEmpId(String empId) {
        return this.employeesInfoMapper.queryLeaderByEmpId(empId);
    }

    @Override
    public List<EmployeesInfo> queryEmpLeaderByHeadLike(String nameLike) {
        return this.employeesInfoMapper.queryEmpLeaderByHeadLike(nameLike);
    }
}
