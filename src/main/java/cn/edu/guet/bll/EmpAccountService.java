package cn.edu.guet.bll;


import cn.edu.guet.bean.Result;

public interface EmpAccountService {
    public Result checkLogin(String username, String userpassword) throws Exception;
}
