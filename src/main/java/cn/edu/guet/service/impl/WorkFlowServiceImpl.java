package cn.edu.guet.service.impl;

import cn.edu.guet.bean.act.ActDeploymentEntity;
import cn.edu.guet.bean.act.ActProcessDefinitionEntity;
import cn.edu.guet.bean.act.ActTaskEntity;
import cn.edu.guet.common.HttpUtils;
import cn.edu.guet.common.RestResult;
import cn.edu.guet.dto.UserAndNameDto;
import cn.edu.guet.service.UserAndNameService;
import cn.edu.guet.service.WorkFlowService;
import cn.edu.guet.vo.WorkFlowVo;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

import static cn.edu.guet.common.CastUtils.cast;

@Service
public class WorkFlowServiceImpl implements WorkFlowService {
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;
    @Autowired
    UserAndNameService userAndNameService;

    @Override
    public void addWorkFlow(String deployName, InputStream inputStream) {
        ZipInputStream zis = new ZipInputStream(inputStream);
        repositoryService.createDeployment()
                .name(deployName)
                .addZipInputStream(zis)
                .deploy();
        try {
            zis.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 这是一个比较完善的查询流程部署的方法
     *
     * @param workFlowVo
     * @return
     */
    @Override
    public RestResult<List<ActDeploymentEntity>> queryProcessDeploy(WorkFlowVo workFlowVo) {
        if (workFlowVo.getDeployName() == null) {
            //目的是查询所有的流程信息
            workFlowVo.setDeployName("");
        }
        String name = workFlowVo.getDeployName();
        //查询总条数
        long count = this.repositoryService.createDeploymentQuery()
                .deploymentNameLike("%" + name + "%")
                .count();
        int start = (workFlowVo.getPage() - 1) * workFlowVo.getLimit();
        int limit = workFlowVo.getLimit();
        //分页查询
        List<Deployment> deployments = repositoryService.createDeploymentQuery()
                .deploymentNameLike("%" + name + "%")
                .listPage(start, limit);
        List<ActDeploymentEntity> data = new ArrayList<>();

        for (Deployment deployment : deployments) {
            ActDeploymentEntity entity = new ActDeploymentEntity();
            BeanUtils.copyProperties(deployment, entity);
            data.add(entity);
        }

        RestResult<List<ActDeploymentEntity>> restResult
                = new RestResult<>();
        restResult.setCount(count);
        restResult.setData(data);
        return restResult;
    }

    @Override
    public RestResult<List<ActProcessDefinitionEntity>> queryProcessDefinition(WorkFlowVo workFlowVo) {
        if (workFlowVo.getDeployName() == null) {
            workFlowVo.setDeployName("");
        }
        String name = workFlowVo.getDeployName();
        List<Deployment> deployments = this.repositoryService.createDeploymentQuery()
                .deploymentNameLike("%" + name + "%")
                .list();
        HashSet<String> deploymentIds = deployments.stream()
                .map(Deployment::getId)
                .collect(Collectors.toCollection(HashSet::new));
        long count = 0;
        List<ActProcessDefinitionEntity> data = new ArrayList<>();
        if (deploymentIds.size() > 0) {
            //查询总条数
            count = this.repositoryService.createProcessDefinitionQuery()
                    .deploymentIds(deploymentIds).count();
            //分页
            int start = (workFlowVo.getPage() - 1) * workFlowVo.getLimit();
            int limit = workFlowVo.getLimit();

            List<ProcessDefinition> processDefinitions = this.repositoryService.createProcessDefinitionQuery()
                    .deploymentIds(deploymentIds)
                    .listPage(start, limit);
            processDefinitions.forEach(processDefinition -> {
                ActProcessDefinitionEntity entity = new ActProcessDefinitionEntity();
                BeanUtils.copyProperties(processDefinition, entity);
                data.add(entity);
            });
        }
        RestResult<List<ActProcessDefinitionEntity>> restResult
                = new RestResult<>();
        restResult.setCount(count);
        restResult.setData(data);
        return restResult;
    }

    /**
     * 启动流程
     *
     * @param workFlowVo
     * @return
     */
    @Override
    @Transactional
    public RestResult<String> startProcess(WorkFlowVo workFlowVo, Integer id) {
        RestResult<String> result = new RestResult<>();
        String processDefinitionKey = workFlowVo.getProcessType();
        //先判断类型是否为空,只有这样判断才可以有效输入至数据库
        if (processDefinitionKey != null) {
            String businessKey = processDefinitionKey + ":" + id;
            Map<String, Object> variables = new HashMap<>();
            UserAndNameDto userAndNameDto = HttpUtils.getAttribute("user", UserAndNameDto.class);
            variables.put("username", userAndNameDto.getEmpName());
            variables.put("processType", processDefinitionKey);
            this.runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
            result.setMsg("ok");
        }
        return result;
    }

    @Override
    public RestResult<List<ActTaskEntity>> queryCurrentUserTask(WorkFlowVo workFlowVo) {
        String assignee = HttpUtils.getAttribute("emp_id", String.class);
        UserAndNameDto userAndNameDto = userAndNameService.queryUserAndNameDto(assignee);

        //写得粗糙一点
        if (userAndNameDto.getEmpId().contains("H")) {
            //这段代码应该是写在退出那一块的
            /*List<Task> oldTasks = taskService.createTaskQuery()
                    .taskAssignee(userAndNameDto.getEmpName())
                    .list();
            oldTasks.forEach(e -> {
                this.taskService.setAssignee(e.getId(),null);
            });*/
            List<Task> tasks = taskService.createTaskQuery()
                    .taskCandidateUser(userAndNameDto.getEmpName())
                    .list();
            tasks.forEach(e -> {
                this.taskService.claim(e.getId(), userAndNameDto.getEmpName());
            });
        }
        long count = this.taskService.createTaskQuery()
                .taskAssignee(userAndNameDto.getEmpName())
                .count();
        //分页
        int start = (workFlowVo.getPage() - 1) * workFlowVo.getLimit();
        int limit = workFlowVo.getLimit();

        List<Task> tasks = this.taskService.createTaskQuery()
                .taskAssignee(userAndNameDto.getEmpName())
                .listPage(start, limit);
        List<ActTaskEntity> actTasks = new ArrayList<>();
        tasks.forEach(task -> {
            ActTaskEntity actTaskEntity = new ActTaskEntity();
            BeanUtils.copyProperties(task, actTaskEntity);
            String processType = cast(this.taskService.getVariable(task.getId(), "processType"), String.class);
            actTaskEntity.setProcessType(processType);
            actTasks.add(actTaskEntity);
        });
        RestResult<List<ActTaskEntity>> result = new RestResult<>();
        result.setData(actTasks);
        result.setCount(count);

        return result;
    }

    @Override
    public Boolean deleteWorkFlow(String deploymentId) {
        ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploymentId).singleResult();
        String processDefinitionId = processDefinition.getId();
        this.repositoryService.deleteDeployment(deploymentId, true);
        return true;
    }

    /**
     * 处理连线信息,在aop中判断类型
     *
     * @param workFlowVo
     * @return
     */
    @Override
    public Map<String, Object> viewAgencyTask(WorkFlowVo workFlowVo) {
        Map<String, Object> map = new HashMap<>();
        List<String> conn = new ArrayList<>();
        Task task = this.taskService.createTaskQuery()
                .taskId(workFlowVo.getTaskId())
                .singleResult();
        //流程定义id,注意这里
        String processDefinitionId = task.getProcessDefinitionId();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);

        FlowElement flowElement = bpmnModel.getFlowElement(task.getTaskDefinitionKey());
        FlowNode flowNode = cast(flowElement, FlowNode.class);
        List<SequenceFlow> outgoingFlows = flowNode.getOutgoingFlows();
        for (SequenceFlow outgoingFlow : outgoingFlows) {
            conn.add(outgoingFlow.getName());
        }
        map.put("sequenceFlow", conn);
        return map;
    }

    @Override
    public RestResult<String> completeTaskAndType(WorkFlowVo workFlowVo) {
        RestResult<String> result = new RestResult<>();
        String taskId = workFlowVo.getTaskId();
        String outcome = workFlowVo.getOutcome();
        Task task = this.taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        String processInstanceId = task.getProcessInstanceId();
        UserAndNameDto user = HttpUtils.getAttribute("user", UserAndNameDto.class);
        Authentication.setAuthenticatedUserId(user.getEmpName());
        Map<String, Object> variable = new HashMap<>();
        variable.put("outcome", outcome);
        ProcessInstance processInstanceKey = this.runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        String businessKey = processInstanceKey.getBusinessKey();
        String instanceType = businessKey.split(":")[0];
        String container = instanceType+","+processInstanceId;
        result.setData(container);
        //完成任务
        this.taskService.complete(taskId, variable);
        return result;
    }
}
