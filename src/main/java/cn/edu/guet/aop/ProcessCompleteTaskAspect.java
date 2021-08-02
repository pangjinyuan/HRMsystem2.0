package cn.edu.guet.aop;

import cn.edu.guet.bean.Change;
import cn.edu.guet.bean.EmpLeaveBill;
import cn.edu.guet.bll.ChangeService;
import cn.edu.guet.common.RestResult;
import cn.edu.guet.info.ProcessDefinitionKey;
import cn.edu.guet.mapper.EmpLeaveBillMapper;
import cn.edu.guet.vo.WorkFlowVo;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static cn.edu.guet.common.CastUtils.cast;

@Aspect
@Component
public class ProcessCompleteTaskAspect {

    @Autowired
    RuntimeService runtimeService;
    @Autowired
    EmpLeaveBillMapper empLeaveBillMapper;
    @Autowired
    ChangeService changeService;
    @Autowired
    TaskService taskService;

    private static final String COMPLETE_TASK_ABOUT_INFO
            = "execution(* cn.edu.guet.service.impl.WorkFlowServiceImpl.completeTaskAndType(..))";

    @Around(value = COMPLETE_TASK_ABOUT_INFO)
    public Object completeTask(ProceedingJoinPoint joinPoint) throws Throwable {

        WorkFlowVo workFlowVo = cast(joinPoint.getArgs()[0], WorkFlowVo.class);
        RestResult<String> proceed = (RestResult<String>) joinPoint.proceed();
        String container = proceed.getData();
        String[] containers = container.split(",");
        String instanceType = containers[0];
        String processInstanceId = containers[1];
        //获取key的前缀判断是否为不同类型
        //请假单的id
        if (instanceType.equals(ProcessDefinitionKey.LEAVE_BILL)) {
            ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();
            if (processInstance == null) {
                EmpLeaveBill empLeaveBill = new EmpLeaveBill();
                Integer leaveBillId = workFlowVo.getLeaveBillId();
                empLeaveBill.setEmpLeaveKey(leaveBillId.longValue());
                String outcome = workFlowVo.getOutcome();
                if (outcome.equals("放弃")) {
                    empLeaveBill.setEmpState("3");
                } else {
                    empLeaveBill.setEmpState("2");
                }
                //修改状态
                this.empLeaveBillMapper.updateByPrimaryKeySelective(empLeaveBill);
            }
        } else if (instanceType.equals(ProcessDefinitionKey.EMP_TERMINATE_LEAVE_TABLE)) {
            ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();
            if (processInstance == null) {
                EmpLeaveBill empLeaveBill = new EmpLeaveBill();
                Integer leaveBillId = workFlowVo.getLeaveBillId();
                empLeaveBill.setEmpLeaveKey(leaveBillId.longValue());
                String outcome = workFlowVo.getOutcome();
                if (outcome.equals("放弃")) {
                    empLeaveBill.setCancellationState("3");
                } else {
                    empLeaveBill.setCancellationState("2");
                }
                //修改状态
                this.empLeaveBillMapper.updateByPrimaryKeySelective(empLeaveBill);
            }
        } else if (instanceType.equals(ProcessDefinitionKey.EMP_STATION_APPLICATION)) {
            ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();
            if (null == processInstance) {
                Change change = new Change();
                int postChangeKey = workFlowVo.getPostChangeKey();
                change.setPost_change_key(String.valueOf(postChangeKey));
                String outcome = workFlowVo.getOutcome();
                System.out.println(change);
                if (outcome.equals("放弃")) {
                    change.emp_state = "3";
                } else {
                    change.emp_state = "2";
                }
                //修改状态
                this.changeService.updateByPostKey(change);
            }
        }

        return proceed;
    }
}
