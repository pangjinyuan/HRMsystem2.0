package cn.edu.guet.bean;

public class EmpAccount {

    private String emp_id;
    private String emp_password;
    private String post_id;
    public EmpAccount(){
    }

    public EmpAccount(String emp_id, String emp_password, String post_id) {
        this.emp_id = emp_id;
        this.emp_password = emp_password;
        this.post_id = post_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_password() {
        return emp_password;
    }

    public void setEmp_password(String emp_password) {
        this.emp_password = emp_password;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }
}
