package cn.edu.guet.bean;

public class PowerResult {
    private  Power power;
    private  Integer res;

    public PowerResult(){

    }
    public PowerResult(Power power, Integer res) {
        this.power = power;
        this.res = res;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }
}
