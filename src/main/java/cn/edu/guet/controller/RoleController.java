package cn.edu.guet.controller;

import cn.edu.guet.bean.Power;
import cn.edu.guet.bean.PowerResult;
import cn.edu.guet.bll.EmpPowerService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {
    @Autowired
    private EmpPowerService empPowerService;
    //role角色搜索的控制器
    //返回父权限的控制器
    //局部刷新
    /**
     * 局部刷新，注意返回值
     * @param model
     * @return
     */
    @RequestMapping(value = {"/fatherpower"})
    public String getFatherPower(Model model) throws Exception {
        List<String> list=empPowerService.getPostName();
        model.addAttribute("post_name",list);
        return "hrmrole";
    }


    //返回有无该权限的控制器
    @RequestMapping(value = {"/rolepower"})
    public String getRolePower(Model model, @RequestParam("postoption") String post_name)  {
        System.out.println("post_id:"+post_name);
        if(post_name==null){
            List<String> list=empPowerService.getPostName();
            model.addAttribute("post_name",list);
        }else{
            //塞入上一个需要的
            model.addAttribute("selectedpost_name",post_name);
            List<String> list=empPowerService.getPostName();
            model.addAttribute("post_name",list);
            Map<String,LinkedList<Power>> OwnPostMap=empPowerService.getOwnPostPower(post_name);
            model.addAttribute("OwnPostMap",OwnPostMap);
            Map<String,LinkedList<Power>> NotOwnPostMap=empPowerService.getNotOwnPostPower(post_name);
            model.addAttribute("NotOwnPostMap",NotOwnPostMap);
        }

      return "hrmrole";
    }
    //接收传过来的待删除的数据
    @PostMapping(value = {"/deletepowerlist"})
    public String deletepowers(@RequestParam("delete") List<String> powerlist,@RequestParam("postkey") String post_name,Model model){
        empPowerService.deletepower(post_name,powerlist);
        List<String> list=empPowerService.getPostName();
        model.addAttribute("post_name",list);
        return "hrmrole";
    }
    //接收传过来的带添加的数据
    @PostMapping(value = {"/addepowerlist"})
    public String addpowers(@RequestParam("add") List<String> powerlist,@RequestParam("postkey") String post_name,Model model){
       empPowerService.addpower(post_name,powerlist);
        List<String> list=empPowerService.getPostName();
        model.addAttribute("post_name",list);
        return "hrmrole";
    }
}
