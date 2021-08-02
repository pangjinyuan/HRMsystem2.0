package cn.edu.guet.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Power {
    private Integer id;
    private String father_name;
    private String power_name;
    private String url;
    public  Power(){

    }
    public Power(Integer id, String father_name, String power_name, String url) {
        this.id = id;
        this.father_name = father_name;
        this.power_name = power_name;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getPower_name() {
        return power_name;
    }

    public void setPower_name(String power_name) {
        this.power_name = power_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
