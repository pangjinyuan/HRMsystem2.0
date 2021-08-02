package cn.edu.guet.controller;

import cn.edu.guet.common.HttpUtils;
import cn.edu.guet.common.IPage;
import cn.edu.guet.common.RestResult;
import cn.edu.guet.service.EmpLeaveBillService;
import cn.edu.guet.vo.EmpLeaveBillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/leavebill")
public class LeaveBillController {

    @Autowired
    EmpLeaveBillService empLeaveBillService;

    /**
     * 转发到请假单页面
     * @return
     */
    @GetMapping("/viewLeaveBill")
    public String viewLeaveBill(){
        return "viewModel/LeaveBill";
    }

    /**
     * 读取并进行分页操作
     * @param iPage
     * @return
     */
    @GetMapping("/viewLeaveBills")
    @ResponseBody
    public RestResult<List<EmpLeaveBillVo>> viewLeaveBills(IPage iPage){
        String empId = HttpUtils.getAttribute("emp_id", String.class);
        List<EmpLeaveBillVo> empLeaveBillVos = empLeaveBillService.queryEmpLeaveBillsByEmpId(empId,iPage);
        Long count = empLeaveBillService.getDataCount();
        RestResult<List<EmpLeaveBillVo>> restResult = new RestResult<>();
        restResult.setCode(0);
        restResult.setMsg("");
        restResult.setCount(count);
        restResult.setData(empLeaveBillVos);
        return restResult;
    }

    /**
     * 转发到添加页面
     * @return
     */
    @GetMapping("/goToAddLeaveBill")
    public String goToAddLeaveBill(){
        return "operate/add";
    }

    /**
     * 添加请假单
     * @param empLeaveBillVo
     * @return
     */
    @PostMapping("/addLeaveBill")
    @ResponseBody
    public RestResult<String> addLeaveBill(@RequestBody EmpLeaveBillVo empLeaveBillVo){
        RestResult<String> objectRestResult = new RestResult<>();
        this.empLeaveBillService.addLeaveBill(empLeaveBillVo);
        objectRestResult.setData("ok");
        return objectRestResult;
    }

    /**
     * 编辑请假单并回显
     * @param empLeaveBillVo
     * @param model
     * @return
     */
    @GetMapping("/eidtLeaveBill")
    public String eidtLeaveBill(EmpLeaveBillVo empLeaveBillVo, Model model){
        Long empLeaveKey = empLeaveBillVo.getEmpLeaveKey();
        empLeaveBillVo = empLeaveBillService.getEmpLeaveByKey(empLeaveKey);
        model.addAttribute("empLeaveBillVo",empLeaveBillVo);
        return "operate/edit";
    }
}
