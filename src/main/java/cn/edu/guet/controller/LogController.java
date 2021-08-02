package cn.edu.guet.controller;

import cn.edu.guet.bll.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogController {
    @Autowired
    private ISysLogService iSysLogService;
    @RequestMapping(value = {"LogCheckAll.do"})
    public String setLogInfo(Model model){
        model.addAttribute("list",iSysLogService.checkAllLog());
        return "hrmlog";
    }
    @PostMapping(value = {"/checkLogFofKey.do"})
    public String setLogInfoForKey(@RequestParam("key") String key, Model model){
        System.out.println(key);
        model.addAttribute("list",iSysLogService.checkLogFofKey(key));
        return "hrmlog";
    }
}
