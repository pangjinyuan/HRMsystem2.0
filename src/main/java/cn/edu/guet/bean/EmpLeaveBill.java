package cn.edu.guet.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName EMP_LEAVE_TABLE
 */
@Data
public class EmpLeaveBill implements Serializable {
    /**
     *
     */
    private Long empLeaveKey;

    /**
     *
     */
    private String empId;

    /**
     *
     */
    private String deptId;

    /**
     *
     */
    private String empLeaveType;

    /**
     *
     */
    private Date beginTime;

    /**
     *
     */
    private Date enddingTime;

    /**
     *
     */
    private String phoneNumber;

    /**
     *
     */
    private String empLeaveReason;

    /**
     *
     */
    private String leaveHandler;

    /**
     *
     */
    private String empState;

    /**
     *
     */
    private String title;
    private String cancellationState;

    private String leaveUserName;

    private String leaveHandlerName;

    private static final long serialVersionUID = 1L;
}