package cn.edu.guet.bean;

import java.util.Date;

public class SysLog {
    private Integer id;
    private String emp_id;
    private String emp_action;
    private String   create_date;
    public SysLog(){

    }
    public SysLog(String emp_id, String emp_action, String create_date) {
        this.emp_id = emp_id;
        this.emp_action = emp_action;
        this.create_date = create_date;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_action() {
        return emp_action;
    }

    public void setEmp_action(String emp_action) {
        this.emp_action = emp_action;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
}
