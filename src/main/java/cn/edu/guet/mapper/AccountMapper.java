package cn.edu.guet.mapper;


import cn.edu.guet.bean.EmpAccount;

public interface AccountMapper {
    public EmpAccount checkEmpInfo(String emp_id)throws Exception;
}
