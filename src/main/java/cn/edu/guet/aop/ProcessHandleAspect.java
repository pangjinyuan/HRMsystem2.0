package cn.edu.guet.aop;

import cn.edu.guet.bean.Change;
import cn.edu.guet.bean.EmpLeaveBill;
import cn.edu.guet.bll.ChangeService;
import cn.edu.guet.common.RestResult;
import cn.edu.guet.info.ProcessDefinitionKey;
import cn.edu.guet.mapper.ChangeMapper;
import cn.edu.guet.service.EmpLeaveBillService;
import cn.edu.guet.vo.WorkFlowVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static cn.edu.guet.common.CastUtils.cast;

@Aspect
@Component
/**
 * 记住要添加编程式的事务处理
 */
public class ProcessHandleAspect {

    @Autowired
    EmpLeaveBillService empLeaveBillService;
    @Autowired
    ChangeService changeService;
    @Autowired
    ChangeMapper changeMapper;

    private static final String PROCESS_HANDLE_JUDGE
            = "execution(* cn.edu.guet.service.impl.WorkFlowServiceImpl.startProcess(..))";
    private static Properties properties = null;
    static {
        InputStream is = ProcessHandleAspect.class.getClassLoader()
                .getResourceAsStream("processType.properties");
        try {
            properties = new Properties();
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Around(value = PROCESS_HANDLE_JUDGE)
    public Object processStart(ProceedingJoinPoint joinPoint) throws Throwable {
        //第一个参数
        WorkFlowVo workFlowVo = cast(joinPoint.getArgs()[0], WorkFlowVo.class);
        //读取前端传过来的processType
        String processDefinitionKey = properties.getProperty(workFlowVo.getProcessType());
        workFlowVo.setProcessType(processDefinitionKey);
        //第二个参数
        Integer id = cast(joinPoint.getArgs()[1], Integer.class);
        if (workFlowVo.getLeaveBillId() != null){
            id = workFlowVo.getLeaveBillId();
        }
        if (workFlowVo.getPostChangeKey() != 0){
            id = workFlowVo.getPostChangeKey();
        }


        @SuppressWarnings("unchecked")
        RestResult<String> proceed = (RestResult<String>)joinPoint.proceed(new Object[]{workFlowVo, id});

        //后置处理
        if (processDefinitionKey.equals(ProcessDefinitionKey.LEAVE_BILL)){
           EmpLeaveBill empLeaveBill = this.empLeaveBillService.queryEmpLeaveBillByEmpId(id);
           empLeaveBill.setEmpState("1");
           int res = this.empLeaveBillService.saveEmpLeaveBill(empLeaveBill);
           if (res != 1 || !proceed.getMsg().equals("ok")){
                proceed.setMsg("no");
           }
        }else if (processDefinitionKey.equals(ProcessDefinitionKey.EMP_TERMINATE_LEAVE_TABLE)){
            EmpLeaveBill empLeaveBill = this.empLeaveBillService.queryEmpLeaveBillByEmpId(id);
            empLeaveBill.setCancellationState("1");
            int res = this.empLeaveBillService.saveEmpLeaveBill(empLeaveBill);
            if (res != 1 || !proceed.getMsg().equals("ok")){
                proceed.setMsg("no");
            }
        }else if (processDefinitionKey.equals(ProcessDefinitionKey.EMP_STATION_APPLICATION)){
            //修改状态。。。
            int postChangeKey = workFlowVo.getPostChangeKey();
            Change change=changeMapper.getChange(String.valueOf(postChangeKey));
            change.emp_state = "1";
            int res = this.changeService.updateByPostKey(change);
            if (res != 1 || !proceed.getMsg().equals("ok")){
                proceed.setMsg("no");
            }
        }
        return proceed;
    }
}
