package cn.edu.guet.bll.impl;


import cn.edu.guet.bean.EmpAccount;
import cn.edu.guet.bean.Result;
import cn.edu.guet.bll.EmpAccountService;
import cn.edu.guet.config.Log;
import cn.edu.guet.mapper.AccountMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
@Service
public class EmpAccountServiceImpl implements EmpAccountService {
    private final static Logger log = org.slf4j.LoggerFactory.getLogger(EmpAccountServiceImpl.class);
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public Result checkLogin(String username, String userpassword) throws Exception {
        System.out.println("登录登录登录");
        EmpAccount emp=accountMapper.checkEmpInfo(username);//查询这个用户，并且返回这个用户的信息
        log.info("登录已经被执行了");
        if(emp!=null){
            if(emp.getEmp_id().equals(username) && emp.getEmp_password().equals(userpassword)){
                return new Result("enable",username);
            }else {
                return new Result("disable",null);
            }
        }else{
            return new Result("disable",null);
        }
    }


}
