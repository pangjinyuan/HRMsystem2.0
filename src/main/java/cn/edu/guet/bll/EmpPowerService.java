package cn.edu.guet.bll;

import cn.edu.guet.bean.MenuTree;
import cn.edu.guet.bean.Post;
import cn.edu.guet.bean.Power;
import cn.edu.guet.bean.PowerResult;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface EmpPowerService {
    //得到菜单的方法
    public MenuTree accountPower(String emp_id) throws Exception;
    //得到父节点的方法，已经没用
    //public List<String> getFatherPower()throws Exception;
    //得到选择的职位所拥有的权限
    public Map<String, LinkedList<Power>> getOwnPostPower(String post_name);
    //得到选择的职位未拥有的权限
    public Map<String, LinkedList<Power>> getNotOwnPostPower(String post_name);
    //得到所有的职位名称
    public List<String> getPostName();
    //根据职位名称得到职位post_id
    public String getPost_id(String post_name);
    //删除某个人的权限
    public  void  deletepower(String post_name,List<String> powers);
    //增加某个人的权限
    public  void  addpower(String post_name,List<String> powers);
    //查询所有权限
    public List<Power> getAllPower();
    //修改权限
    public void updatePower(Integer id,String url);
    //增加权限
    public void addPower_table(String father_name,String power_name,String url);
    //删除权限
    public void deletePower_table(Integer id);
}
