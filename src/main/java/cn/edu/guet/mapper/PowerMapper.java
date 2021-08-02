package cn.edu.guet.mapper;


import cn.edu.guet.bean.EmpPower;
import cn.edu.guet.bean.Post;
import cn.edu.guet.bean.Power;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PowerMapper {
   //得到这个登录者的权限
   public List<EmpPower> getEmpPower(String emp_id);
   //得到这个所有的权限
   public List<Power>  getPower();
   //得到这个职位的曲线
   public List<EmpPower> getPostPower(String post_id);
   //得到所有的职位名称
   public List<Post> getPostName();
   //根据职位id和待删除的权限id，删除权限
   public void deletePower(@Param("post_id") String post_id,@Param("power_key") Integer power_key);
   //根据职位id和带增加的权限id，增加某个权限
   //先根据权限id查出该权限的数据
   public Power getPowerByid(Integer power_key);
   public void addPower(@Param("emppower")EmpPower empPower);
   //根据id删除权限
   public  void deleteOldPower(Integer id);
   //增加新权限
   public void addnewPower(@Param("father_name") String father_name,@Param("power_name")String power_name,@Param("url") String url);
   //修改权限
   public void updateOldPower(@Param("id")Integer id,@Param("url")String url);
}
