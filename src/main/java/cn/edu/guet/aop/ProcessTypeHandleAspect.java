package cn.edu.guet.aop;

import cn.edu.guet.bean.Change;
import cn.edu.guet.bean.EmpLeaveBill;
import cn.edu.guet.bll.ChangeService;
import cn.edu.guet.info.ProcessDefinitionKey;
import cn.edu.guet.mapper.ChangeMapper;
import cn.edu.guet.service.EmpLeaveBillService;
import cn.edu.guet.vo.WorkFlowVo;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static cn.edu.guet.common.CastUtils.cast;

@Aspect
@Component
public class ProcessTypeHandleAspect {
    @Autowired
    EmpLeaveBillService empLeaveBillService;
    @Autowired
    ChangeService changeService;
    @Autowired
    ChangeMapper changeMapper;
    @Autowired
    TaskService taskService;
    @Autowired
    RuntimeService runtimeService;

    private static final String PROCESS_TYPE_HANDLE
            = "execution(* cn.edu.guet.service.impl.WorkFlowServiceImpl.viewAgencyTask(..))";
    @Around(value = PROCESS_TYPE_HANDLE)
    public Object processTypeHandle(ProceedingJoinPoint joinPoint) throws Throwable {
        //前置处理
        WorkFlowVo workFlowVo = cast(joinPoint.getArgs()[0], WorkFlowVo.class);
        Map<String, Object> proceed = (Map<String, Object>)joinPoint.proceed();
        Task task = this.taskService.createTaskQuery()
                .taskId(workFlowVo.getTaskId())
                .singleResult();
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        String businessKey = processInstance.getBusinessKey();
        String taskTypeId = businessKey.split(":")[1];
        //后置处理
        if (workFlowVo.getProcessType().equals(ProcessDefinitionKey.LEAVE_BILL )
                || workFlowVo.getProcessType().equals(ProcessDefinitionKey.EMP_TERMINATE_LEAVE_TABLE)){
            EmpLeaveBill empLeaveBill = this.empLeaveBillService.queryEmpLeaveBillByEmpId(Integer.parseInt(taskTypeId));
            proceed.put("taskId",workFlowVo.getTaskId());
            proceed.put("processInfo",empLeaveBill);
            proceed.put("address","operate/agencyLeaveTask");
        }else if (workFlowVo.getProcessType().equals(ProcessDefinitionKey.EMP_STATION_APPLICATION)){
            //这里看需要填什么
            Change change=changeMapper.getChange(taskTypeId);
            proceed.put("taskId",workFlowVo.getTaskId());
            proceed.put("processInfo",change);
            System.out.println(change);
            proceed.put("address","operate/agencyPostTask");
        }
        return proceed;
    }
}
