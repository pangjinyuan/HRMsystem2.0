package cn.edu.guet.bean;

public class EmpPower {
    private String EMP_POWER_KEY;
    private String post_id;
    private String power_name;
    private int father_id;
    private String father_name;
    private String url;
    public EmpPower(){

    }

    public EmpPower(String EMP_POWER_KEY, String post_id, String power_name, int father_id, String father_name, String url) {
        this.EMP_POWER_KEY = EMP_POWER_KEY;
        this.post_id = post_id;
        this.power_name = power_name;
        this.father_id = father_id;
        this.father_name = father_name;
        this.url = url;
    }

    public String getEMP_POWER_KEY() {
        return EMP_POWER_KEY;
    }

    public void setEMP_POWER_KEY(String EMP_POWER_KEY) {
        this.EMP_POWER_KEY = EMP_POWER_KEY;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPower_name() {
        return power_name;
    }

    public void setPower_name(String power_name) {
        this.power_name = power_name;
    }

    public int getFather_id() {
        return father_id;
    }

    public void setFather_id(int father_id) {
        this.father_id = father_id;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
