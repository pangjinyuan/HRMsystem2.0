package cn.edu.guet.bean;

public class CheckLogInfo {
    private Integer id;
    private String emp_id;
    private String emp_name;
    private String post_name;
    private String emp_action;
    private String create_time;

    public CheckLogInfo(){


    }
    public CheckLogInfo(Integer id, String emp_id, String emp_name, String post_name, String emp_action, String create_time) {
        this.id = id;
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.post_name = post_name;
        this.emp_action = emp_action;
        this.create_time = create_time;
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

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getEmp_action() {
        return emp_action;
    }

    public void setEmp_action(String emp_action) {
        this.emp_action = emp_action;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
