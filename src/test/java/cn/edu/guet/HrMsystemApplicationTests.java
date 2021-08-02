package cn.edu.guet;

import cn.edu.guet.bean.*;
import cn.edu.guet.bll.EmpPowerService;
import cn.edu.guet.mapper.LogMapper;
import cn.edu.guet.mapper.PowerMapper;
import org.attoparser.dom.INestableNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class HrMsystemApplicationTests {
    @Autowired
    private PowerMapper powerMapper;
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private EmpPowerService empPowerService;

    @Test
    void contextLoads() {
    }

    @Test
    void testpower() throws Exception {
        String emp_id = "P1000";
        List<EmpPower> list = powerMapper.getEmpPower(emp_id);
        for (EmpPower empPower : list) {
            System.out.println(empPower.getPower_name());
            System.out.println(empPower.getUrl());
        }

    }

    @Test
    void testSetLog() {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        System.out.println("当前时间为：" + retStrFormatNowDate);
        String date = String.valueOf(new Date().getTime());
        SysLog log = new SysLog("M1000", "测试", retStrFormatNowDate);
        logMapper.setLog(log);
    }

    @Test
    void testchackalllog() {
        List<CheckLogInfo> logs = logMapper.checkLogFofKey("M");
        for (CheckLogInfo sysLog : logs) {
            System.out.print(sysLog.getId() + "/n");
            System.out.print(sysLog.getEmp_id() + "/n");
            System.out.print(sysLog.getEmp_name() + "/n");
            System.out.print(sysLog.getPost_name() + "/n");
            System.out.print(sysLog.getEmp_action() + "/n");
            System.out.println(sysLog.getCreate_time() + "/n");
        }
    }

    @Test
    void testcheckPower() {
        List<EmpPower> powers = powerMapper.getPostPower("A");
        for (EmpPower power : powers) {
            System.out.println(power.getFather_name());
            System.out.println(power.getPower_name());
        }
    }



    @Test
    void testgetPostPower() throws Exception {
        Map<String,LinkedList<Power>>  map = empPowerService.getOwnPostPower("经理");
        for(String s:map.keySet()){
            System.out.println(s);
            for(int i=0;i<map.get(s).size();i++){
                System.out.print("子节点id："+map.get(s).get(i).getId()+",");
                System.out.print("子节点："+map.get(s).get(i).getPower_name()+",");
            }
            System.out.println();
        }

    }
    @Test
    void testgetpostname(){
       Power power= powerMapper.getPowerByid(14);
       EmpPower empPower=new EmpPower();
        System.out.println("待插入"+power.getPower_name()+":"+ power.getFather_name());
       empPower.setPost_id("B");
       empPower.setPower_name(power.getPower_name());
       empPower.setFather_name(power.getFather_name());
       empPower.setUrl(power.getUrl());
       powerMapper.addPower(empPower);
        System.out.println("插入成功");
    }
    @Test
    void testaddpower(){
        List<String> powers=new LinkedList<>();
        powers.add("1");
        powers.add("2");
        powers.add("3");
        powers.add("4");
        empPowerService.addpower("测试",powers);
        System.out.println("test插入成功");
    }
    @Test
    void testdeletepower(){
        List<String> powers=new LinkedList<>();
        powers.add("1");
        powers.add("2");
        powers.add("3");
        powers.add("4");
        empPowerService.deletepower("测试",powers);
        System.out.println("test删除成功");
    }
    @Test
    void testPOwer(){
        powerMapper.deleteOldPower(21);
        System.out.println("成功");
    }
}