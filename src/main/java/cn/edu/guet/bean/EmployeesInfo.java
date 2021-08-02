package cn.edu.guet.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeesInfo implements Serializable {
    private Long empInfoKey;

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
    private String postId;

    /**
     *
     */
    private String empName;

    /**
     *
     */
    private String empSex;

    /**
     *
     */
    private String empState;

    /**
     *
     */
    private String empEntryTime;

    /**
     *
     */
    private String empIdentifyType;

    /**
     *
     */
    private String empIdentifyNumber;

    /**
     *
     */
    private String empPhoneNumber;

    /**
     *
     */
    private String empEMail;

    /**
     *
     */
    private String empNation;

    /**
     *
     */
    private String empNativePlace;

    /**
     *
     */
    private String empBirthday;

    /**
     *
     */
    private String empLocation;

    /**
     *
     */
    private String empEduBgd;

    /**
     *
     */
    private String empEntryWay;

    /**
     *
     */
    private String empEduSchool;

    private Long leader;

    private static final long serialVersionUID = 1L;
}
