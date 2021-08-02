package cn.edu.guet.controller;

import cn.edu.guet.bean.EmpAccount;
import cn.edu.guet.bean.MenuTree;
import cn.edu.guet.bean.Power;
import cn.edu.guet.bll.EmpPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PowerController {
    @Autowired
    private EmpPowerService powerService;
    @RequestMapping(value = "Main.do")
    @ResponseBody
    public MenuTree accountPower(EmpAccount emp) throws Exception {

        return powerService.accountPower(emp.getEmp_id());
    }
    @RequestMapping(value = {"/hrmpower.do"})
    public String power(Model model){
        model.addAttribute("powerlist",powerService.getAllPower());
        return "hrmpower";
    }
    @PostMapping(value = {"/addnewpower"})
    public String addpower(Model model, @RequestParam("father_name")String father_name,@RequestParam("power_name")String power_name,@RequestParam("url")String url){
        System.out.println("father_name:"+father_name);
        System.out.println("power_name:"+power_name);
        System.out.println("url:"+url);
        powerService.addPower_table(father_name,power_name,url);
        model.addAttribute("powerlist",powerService.getAllPower());
        return "hrmpower";
    }
    @PostMapping(value = {"/updateoldpower"})
    public String updatepower(Model model,@RequestParam("power_key")String id,@RequestParam("url")String url){
        System.out.println(id);
        System.out.println(url);
        powerService.updatePower(Integer.valueOf(id),url);
        model.addAttribute("powerlist",powerService.getAllPower());
        return "hrmpower";
    }
    @PostMapping(value = {"/deleteoldpower"})
    public String deleteoldPower(Model model,@RequestParam("delete_id")String id){
        System.out.println(id);
        powerService.deletePower_table(Integer.valueOf(id));
        model.addAttribute("powerlist",powerService.getAllPower());
        return "hrmpower";
    }
}


