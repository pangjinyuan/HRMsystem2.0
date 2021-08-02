package cn.edu.guet.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EmpLeaveBillVo {
    private Long empLeaveKey;
    private String title;
    private String empLeaveType;
    private String empLeaveReason;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date enddingTime;
    private String LeaveUserName;
    private String LeaveHandlerName;
    private String empState;
    private String cancellationState;
}
