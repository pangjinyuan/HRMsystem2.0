package cn.edu.guet.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
//因为父类的id是子类都有的，所以要比较id，需要让callSuper=true;不需要比较父类属性的时候，采用FALSE
public class WorkFlowVo {

    // 分页参数
    private Integer page = 1;
    private Integer limit = 10;

    // 流程部署名称
    private String deployName;
    // 流程部署ID
    private String deploymentId;

    private Integer leaveBillId;
    private String taskId;
    //连线的名字
    private String outcome;
    // 批注信息
    private String comment;
    // 接收多个Id 用于批量删除
    private Integer[] ids;
    // 流程实例ID
    private String processInstanceId;
    // 发起流程的类型
    private String processType;
    private int postChangeKey;

    // 查询时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
