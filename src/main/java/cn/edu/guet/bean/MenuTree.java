package cn.edu.guet.bean;

import java.util.List;
import java.util.Map;

public class MenuTree {
    private Map<String, List<EmpPower>> menutree;

    public Map<String, List<EmpPower>> getMenutree() {
        return menutree;
    }

    public void setMenutree(Map<String, List<EmpPower>> menutree) {
        this.menutree = menutree;
    }
}
