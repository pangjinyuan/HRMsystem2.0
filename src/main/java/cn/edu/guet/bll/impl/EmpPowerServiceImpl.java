package cn.edu.guet.bll.impl;


import cn.edu.guet.bean.*;
import cn.edu.guet.bll.EmpPowerService;
import cn.edu.guet.config.Log;
import cn.edu.guet.mapper.PowerMapper;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class EmpPowerServiceImpl implements EmpPowerService {
   @Autowired
   private PowerMapper powerMapper;
    @Override
    @Log("用户登录")
    public MenuTree accountPower(String emp_id) throws Exception {
        List<EmpPower> empPowers=powerMapper.getEmpPower(emp_id);//查询这个用户，并且返回这个用户的权力
        Map<String, List<EmpPower>> menuMap=new LinkedHashMap<>();
        for(EmpPower i:empPowers){
            if(!menuMap.containsKey(i.getFather_name())){
                List<EmpPower> QueryPowerList=new ArrayList<>();
                for(EmpPower j:empPowers){
                    if(j.getFather_name().equals(i.getFather_name())){
                        QueryPowerList.add(j);
                    }
                }
                menuMap.put(i.getFather_name(),QueryPowerList);
            }
        }

        MenuTree menuTree=new MenuTree();
        menuTree.setMenutree(menuMap);
        return menuTree;
    }

    /*
    //role界面得到表格里的父权限栏
    @Override
    public List<String> getFatherPower() throws Exception {
        List<Power> powers=powerMapper.getPower();
        Set<String> set=new LinkedHashSet<>();
        List<String> fatherPower = new ArrayList<>();
        for(Power power:powers){
            set.add(power.getFather_name());
        }
       // System.out.println(set.size());
        for(String str:set){
            fatherPower.add(str);
        }
        return fatherPower;
    }

     */
    //role界面搜索某个职位,得到这个职位已经拥有的权力
    @Override
    public Map<String,LinkedList<Power>> getOwnPostPower(String post_name) {
        //所有权力的权力表
        List<Power> powerList=powerMapper.getPower();
        //该职位现有的权力表
        List<EmpPower> postPowerList=powerMapper.getPostPower(getPost_id(post_name));
        //零时作判断用的结果集
        Map<Power,Integer> map=new LinkedHashMap<>();
        for(Power power:powerList){
            map.put(power,0);
        }
        //我们最终要返回的
        Map<String,LinkedList<Power>> reslultmap=new LinkedHashMap<>();
        for(EmpPower empPower:postPowerList){
            for(Power power:powerList){
                if(empPower.getPower_name().equals(power.getPower_name())){
                    map.put(power,1);
                }
            }
        }

        //先创建父节点，字符串
        for(Power power:map.keySet()){
            reslultmap.put(power.getFather_name(),new LinkedList<Power>());
        }
        //创建好子节点集合
        for(Power power:map.keySet()){
            if(map.get(power)==1){
                reslultmap.get(power.getFather_name()).add(power);
            }
        }
       //返回结果集
        return reslultmap;
    }

    @Override
    public Map<String, LinkedList<Power>> getNotOwnPostPower(String post_name) {
        //所有权力的权力表
        List<Power> powerList=powerMapper.getPower();
        //该职位现有的权力表
        List<EmpPower> postPowerList=powerMapper.getPostPower(getPost_id(post_name));
        //零时作判断用的结果集
        Map<Power,Integer> map=new LinkedHashMap<>();
        //先给初始值
        for(Power power:powerList){
            map.put(power,0);
        }
        //我们最终要返回的
        Map<String,LinkedList<Power>> reslultmap=new LinkedHashMap<>();
        for(EmpPower empPower:postPowerList){
            for(Power power:powerList){
                if(empPower.getPower_name().equals(power.getPower_name())){
                    map.put(power,1);
                }
            }
        }

        //先创建父节点，字符串
        for(Power power:map.keySet()){
            reslultmap.put(power.getFather_name(),new LinkedList<Power>());
        }
        //创建好子节点集合
        for(Power power:map.keySet()){
            if(map.get(power)==0){
                reslultmap.get(power.getFather_name()).add(power);
            }
        }
        //返回结果集
        return reslultmap;
    }

    @Override
    public List<String> getPostName() {
        List<Post> postList=powerMapper.getPostName();
        List<String> postnames=new LinkedList<>();
        for(Post post:postList){
            postnames.add(post.getPost_name());
        }
        return postnames;
    }

    @Override
    public String getPost_id(String post_name) {
        List<Post> postList=powerMapper.getPostName();
        List<String> postnames=new LinkedList<>();
        String emp_id=null;
        for(Post post:postList){
           if(post.getPost_name().equals(post_name)){
               emp_id=post.getPost_id();
           }
        }
        return emp_id;
    }

    @Override
    public void deletepower(String post_name, List<String> powers) {
        String emp_id=getPost_id(post_name);
        for(String power:powers){
            powerMapper.deletePower(emp_id,Integer.valueOf(power));
            System.out.println("删除id;"+power);
        }

    }

    @Override
    public void addpower(String post_name, List<String> powers) {
        String emp_id=getPost_id(post_name);
        for(String s:powers){
            Power power= powerMapper.getPowerByid(Integer.valueOf(s));
            EmpPower empPower=new EmpPower();
            System.out.println("待插入"+power.getPower_name()+":"+ power.getFather_name());
            empPower.setPost_id(emp_id);
            empPower.setPower_name(power.getPower_name());
            empPower.setFather_name(power.getFather_name());
            empPower.setUrl(power.getUrl());
            powerMapper.addPower(empPower);
            System.out.println("插入成功");
        }
    }

    @Override
    public List<Power> getAllPower() {
        return  powerMapper.getPower();
    }
    //更新权力
    @Override
    public void updatePower(Integer id, String url) {
        powerMapper.updateOldPower(id,url);
    }
    //新增加权力
    @Override
    public void addPower_table(String father_name, String power_name, String url) {
        powerMapper.addnewPower(father_name,power_name,url);
    }
    //删除旧权力
    @Override
    public void deletePower_table(Integer id) {
        powerMapper.deleteOldPower(id);
    }

}
