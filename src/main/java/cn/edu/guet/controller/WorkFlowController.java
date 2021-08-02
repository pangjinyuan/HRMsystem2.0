package cn.edu.guet.controller;

import cn.edu.guet.bean.act.ActDeploymentEntity;
import cn.edu.guet.bean.act.ActProcessDefinitionEntity;
import cn.edu.guet.bean.act.ActTaskEntity;
import cn.edu.guet.common.RestResult;
import cn.edu.guet.service.WorkFlowService;
import cn.edu.guet.vo.WorkFlowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static cn.edu.guet.common.CastUtils.cast;

@Controller
@RequestMapping("/workflow")
public class WorkFlowController {

    @Autowired
    WorkFlowService workFlowService;

    /**
     * 跳转页面
     *
     * @return
     */
    @GetMapping("/viewDeployInfo")
    public String viewDeployInfo() {
        return "viewModel/processDeployment";
    }

    /**
     * 查看页面以及内容上传
     *
     * @return
     */
    @GetMapping("/addProcessDeploy")
    public String addProcessDeploy() {
        return "operate/addProcessDeploy";
    }

    /**
     * 增加流程,用在对弹窗的使用
     *
     * @param workFlowVo
     * @param mf
     * @return
     */
    @PostMapping("/addDeployData")
    @ResponseBody
    public RestResult<String> addDeployData(WorkFlowVo workFlowVo, @RequestParam("mf") MultipartFile mf) {
        try {
            this.workFlowService.addWorkFlow(workFlowVo.getDeployName(), mf.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestResult<String> restResult = new RestResult<>();
        restResult.setData("ok");
        return restResult;
    }

    @PostMapping("delDeployData")
    @ResponseBody
    public RestResult<String> delDeployData(WorkFlowVo workFlowVo) {
        Boolean flag = this.workFlowService.deleteWorkFlow(workFlowVo.getDeploymentId());
        RestResult<String> result = new RestResult<>();
        if (flag) {
            result.setData("ok");
        } else {
            result.setData("no");
        }
        return result;
    }

    /**
     * 查看流程部署信息
     *
     * @param workFlowVo
     * @return
     */
    @GetMapping("/viewDeploymentInfo")
    @ResponseBody
    public RestResult<List<ActDeploymentEntity>> viewDeployInfo(WorkFlowVo workFlowVo) {
        RestResult<List<ActDeploymentEntity>> deploys =
                this.workFlowService.queryProcessDeploy(workFlowVo);
        return deploys;
    }

    /**
     * 查看流程定义信息
     *
     * @param workFlowVo
     * @return
     */
    @GetMapping("/viewDefinitionInfo")
    @ResponseBody
    public RestResult<List<ActProcessDefinitionEntity>> viewDefinitionInfo(WorkFlowVo workFlowVo) {
        return this.workFlowService.queryProcessDefinition(workFlowVo);
    }

    /**
     * 请假流程启动
     *
     * @param workFlowVo
     * @return
     */
    @PostMapping("/startLeaveProcess")
    @ResponseBody
    public RestResult<String> startProcess(WorkFlowVo workFlowVo) {
        System.out.println(workFlowVo);
        return this.workFlowService.startProcess(workFlowVo, 0);
    }

    /**
     * 跳转到代办
     *
     * @return
     */
    @GetMapping("/viewAgencyTask")
    public String viewAgencyTask() {
        return "viewModel/agencyTask";
    }

    /**
     * 发送代办信息
     *
     * @return
     */
    @GetMapping("/selfAgencyTask")
    @ResponseBody
    public RestResult<List<ActTaskEntity>> selfAgencyTask(WorkFlowVo workFlowVo) {
        return this.workFlowService.queryCurrentUserTask(workFlowVo);
    }

    /**
     * 显示流程信息
     *
     * @param workFlowVo
     * @return
     */
    @GetMapping("/viewAllAgencyTask")
    public String viewAgencyTask(WorkFlowVo workFlowVo, Model model) {
        model.addAttribute("taskId", workFlowVo.getTaskId());
        System.out.println(workFlowVo);
        Map<String, Object> models = this.workFlowService.viewAgencyTask(workFlowVo);
        model.addAttribute("models", models);
        return cast(models.get("address"), String.class);
    }

    @PostMapping("/completeTask")
    @ResponseBody
    public RestResult<String> completeTask(@RequestBody WorkFlowVo workFlowVo) {
        RestResult<String> result = this.workFlowService.completeTaskAndType(workFlowVo);
        if (result != null) {
            result.setData("ok");
        }
        return result;
    }

    @PostMapping("submitLeaveCancellation")
    @ResponseBody
    public RestResult<String> submitLeaveCancellation(WorkFlowVo workFlowVo) {
        RestResult<String> result = this.workFlowService.startProcess(workFlowVo, 0);
        return result;
    }
}
