package cn.edu.guet.service;

import cn.edu.guet.bean.EmployeesInfo;

import java.util.List;

public interface EmployeesInfoService {
    void DepositInfo(String empId);

    EmployeesInfo queryEmpLeaderByEmpId(String empId);

    List<EmployeesInfo> queryEmpLeaderByHeadLike(String nameLike);
}
