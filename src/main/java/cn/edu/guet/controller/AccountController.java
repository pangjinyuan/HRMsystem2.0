package cn.edu.guet.controller;



import cn.edu.guet.bean.EmpAccount;
import cn.edu.guet.bean.Result;
import cn.edu.guet.bll.EmpAccountService;
import cn.edu.guet.common.HttpUtils;
import cn.edu.guet.dto.UserAndNameDto;
import cn.edu.guet.service.UserAndNameService;
import cn.edu.guet.utiltool.Sessionkey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AccountController {
    @Autowired
    private EmpAccountService accountService;
    @Autowired
    UserAndNameService userAndNameService;

    //@RequestMapping("/login.do")
    //public String login() {
   //     return "login";
   // }
    @RequestMapping("/checkLogin.do")
    @ResponseBody
    public Result checkLogin(EmpAccount emp, HttpServletRequest request, HttpSession session) throws Exception {
        //传入参数 输出结果
        Result result=accountService.checkLogin(emp.getEmp_id(),emp.getEmp_password());
        if(result.getRes().equals("enable")){
            String emp_id=emp.getEmp_id();
            session.setAttribute(Sessionkey.SESSION_KEY,emp_id);//存一个字符串
            UserAndNameDto userAndNameDto = this.userAndNameService.queryUserAndNameDto(result.getEmp_id());
            //方便查找用户,直接存入session
            HttpUtils.setAttribute("user", userAndNameDto);
            return result;
        }else{
            return result;
        }

    }



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        /* 删除 session 值 */
        session.removeAttribute(Sessionkey.SESSION_KEY);
        return "login.html";
    }


}