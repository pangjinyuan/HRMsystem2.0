package cn.edu.guet.controller;


import cn.edu.guet.bean.User;
import cn.edu.guet.bll.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;




    /**
     * 删除用户
     */
    @RequestMapping("/deluser")
    public String deluser(String id){
        userService.delete(id);
        return "redirect:userList";
    }

    //添加
//    @RequestMapping("/adduser")
//    public String adduser(User user){
//        return "add";
//    }
//    @RequestMapping("/add")
//    public String add(User user){
//        userService.add(user);
//        return "redirect:userList";
//    }

    /**
     *修改用户页面
     */
    @RequestMapping("/finduser")
    public String finduser(Model model, String id){
        User user = userService.getUserById(id);
        model.addAttribute("user",user);
        return "update";
    }
    @RequestMapping("/update")
    public String updateuser(User user){
        userService.update(user);
        return "redirect:userList";

    }

//    @PostMapping("/finduser")
//    @ResponseBody
//    public Map<String,Object> finduser(String id,Model model ){
//
//        User user=userService.getUserById(id);
//        Map<String,Object> map=new HashMap<>();
//        map.put("emp_id",user.getEmp_id());
//        map.put("dept_id",user.getDept_id());
//        map.put("dept_name",user.getDept_name());
//
//        map.put("post_id",user.getPost_id());
//        map.put("post_name",user.getPost_name());
//        map.put("emp_name",user.getEmp_name());
//
//        map.put("emp_sex",user.getEmp_sex());
//        map.put("emp_entry_time",user.getEmp_entry_time());
//        map.put("change_type",user.getChange_type());
//        map.put("emp_phone_number",user.getEmp_phone_number());
//        map.put("emp_state",user.getEmp_state());
////        System.out.println(map);
//        return map;
//    }
//    @RequestMapping("/update")
//    public String update(User user, @RequestBody Map<String,Object> map){
//        String emp_id=map.get("emp_id").toString();
//        String dept_id=map.get("dept_id").toString();
//        String dept_name=map.get("dept_name").toString();
//
//        String post_id=map.get("post_id").toString();
//        String post_name=map.get("post_name").toString();
//        String emp_name=map.get("emp_name").toString();
//        String emp_sex=map.get("emp_sex").toString();
//        String emp_entry_time=map.get("emp_entry_time").toString();
//        String change_type=map.get("change_type").toString();
//        String emp_phone_number=map.get("emp_phone_number").toString();
//        String emp_state=map.get("emp_state").toString();
//        user=new User(emp_id,dept_id,dept_name,post_id,post_name,emp_name,emp_sex,emp_entry_time,change_type,emp_phone_number,emp_state);
////        System.out.println(emp_id+" "+dept_id+" "+dept_name);
////        System.out.println(user);
//        userService.update(user);
//        return "redirect:userList";
//    }


    //查询
    @RequestMapping("/getUser")
    public String findByName(Model model, String name){
        List<User> userList = userService.findByName(name);
        User user=new User();
//        PageInfo<User> pageInfo = new PageInfo<>(userList);
//      model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("userList",userList);
        model.addAttribute("user",user);
        return "userList";
    }


    //分页
//    @GetMapping("/userList")
//    public String userList(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
//        PageHelper.startPage(pageNum,10);    // 默认从第一页开始，每页展示五条数据
//        List<User> userList = userService.getAll();
//        PageInfo<User> pageInfo = new PageInfo<>(userList);
//        model.addAttribute("pageInfo",pageInfo);
//        return "userList";
//    }

//        @RequestMapping("/userList")
//    public String userList(Model model){
//        List<User> userList = userService.getAll();
//        model.addAttribute("userList",userList);
//        return "userList";
//    }

    @GetMapping("/userList")
    public String userList(Model model) {
        List<User> userList=userService.getAll();
        User user=new User();
        model.addAttribute("user",user);//引入demand对象
        model.addAttribute("userList",userList);
        return "userList";
    }

    @PostMapping("/add")
    public String addDemands(Model model,@ModelAttribute("user") User user) {
        System.out.println("开始添加");
//        demandsService.addDemands(demand);
        userService.add(user);
        System.out.println("添加成功");
        return "redirect:userList";
    }


}







