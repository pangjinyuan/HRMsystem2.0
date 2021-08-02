package cn.edu.guet.bean;

public class Result {
    private String res;
    private String emp_id;

    public Result(String res, String emp_id) {
        this.res = res;
        this.emp_id = emp_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    //用来判断是否给登录的结果类,也可以用来判断其他结果
    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
