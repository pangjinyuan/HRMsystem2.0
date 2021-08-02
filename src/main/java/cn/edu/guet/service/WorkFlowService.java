package cn.edu.guet.service;

import cn.edu.guet.bean.act.ActDeploymentEntity;
import cn.edu.guet.bean.act.ActProcessDefinitionEntity;
import cn.edu.guet.bean.act.ActTaskEntity;
import cn.edu.guet.common.RestResult;
import cn.edu.guet.vo.WorkFlowVo;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;


@Service
public interface WorkFlowService {
    /**
     * 获取网页的信息并处理zip文件
     */
    void addWorkFlow(String deployName, InputStream inputStream);

    RestResult<List<ActDeploymentEntity>> queryProcessDeploy(WorkFlowVo workFlowVo);

    RestResult<List<ActProcessDefinitionEntity>> queryProcessDefinition(WorkFlowVo workFlowVo);

    RestResult<String> startProcess(WorkFlowVo workFlowVo, Integer id);

    RestResult<List<ActTaskEntity>> queryCurrentUserTask(WorkFlowVo workFlowVo);

    Boolean deleteWorkFlow(String deploymentId);

    Map<String, Object> viewAgencyTask(WorkFlowVo workFlowVo);

    RestResult<String> completeTaskAndType(WorkFlowVo workFlowVo);
}
