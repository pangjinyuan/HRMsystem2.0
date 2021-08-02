package cn.edu.guet.bean;

public class Change {
    public String emp_info_key;
    public String emp_id;
    public String dept_id;
    public String dept_name;
    public String post_id;
    public String post_name;
    public String emp_name;
    public String emp_sex;
    public String emp_state;
    public String emp_entry_time;
    public String emp_identify_type;
    public String emp_identify_number;
    public String emp_phone_number;
    public String emp_e_mail;
    public String emp_nation;
    public String emp_native_place;
    public String emp_birthday;
    public String emp_location;
    public String emp_edu_bgd;
    public String emp_entry_way;

    public String post_change_key;
    public String change_type;

    public Change() {
    }

    public Change(Change change) {
    }


    public Change(String emp_id, String dept_id, String dept_name, String post_id, String post_name, String emp_name, String emp_sex, String emp_entry_time, String change_type, String emp_phone_number, String emp_state) {
        this.emp_id = emp_id;
        this.dept_id = dept_id;
        this.dept_name = dept_name;
        this.post_id = post_id;
        this.post_name = post_name;
        this.emp_name = emp_name;
        this.emp_sex = emp_sex;
        this.emp_state = emp_state;
        this.emp_entry_time = emp_entry_time;
        this.emp_phone_number = emp_phone_number;
        this.change_type = change_type;
    }

    public Change(String emp_info_key, String emp_id, String dept_id, String post_id, String emp_name, String emp_sex, String emp_state, String emp_entry_time, String emp_identify_type, String getEmp_identify_number, String emp_phone_number, String emp_e_mail, String emp_nation, String emp_native_place, String emp_location, String emp_edu_bgd, String emp_entry_way, String emp_edu_school) {
        this.emp_info_key = emp_info_key;
        this.emp_id = emp_id;
        this.dept_id = dept_id;
        this.post_id = post_id;
        this.emp_name = emp_name;
        this.emp_sex = emp_sex;
        this.emp_state = emp_state;
        this.emp_entry_time = emp_entry_time;
        this.emp_identify_type = emp_identify_type;
        this.emp_identify_number = getEmp_identify_number;
        this.emp_phone_number = emp_phone_number;
        this.emp_e_mail = emp_e_mail;
        this.emp_nation = emp_nation;
        this.emp_native_place = emp_native_place;
        this.emp_location = emp_location;
        this.emp_edu_bgd = emp_edu_bgd;
        this.emp_entry_way = emp_entry_way;

    }

    public String getChange_type() {
        return change_type;
    }

    public void setChange_type(String change_type) {
        this.change_type = change_type;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getPost_change_key() {
        return post_change_key;
    }

    public void setPost_change_key(String post_change_key) {
        this.post_change_key = post_change_key;
    }

    public String getEmp_info_key() {
        return emp_info_key;
    }

    public void setEmp_info_key(String emp_info_key) {
        this.emp_info_key = emp_info_key;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_sex() {
        return emp_sex;
    }

    public void setEmp_sex(String emp_sex) {
        this.emp_sex = emp_sex;
    }

    public String getEmp_state() {
        return emp_state;
    }

    public void setEmp_state(String emp_state) {
        this.emp_state = emp_state;
    }

    public String getEmp_entry_time() {
        return emp_entry_time;
    }

    public void setEmp_entry_time(String emp_entry_time) {
        this.emp_entry_time = emp_entry_time;
    }

    public String getEmp_identify_type() {
        return emp_identify_type;
    }

    public void setEmp_identify_type(String emp_identify_type) {
        this.emp_identify_type = emp_identify_type;
    }

    public String getGetEmp_identify_number() {
        return emp_identify_number;
    }

    public void setGetEmp_identify_number(String getEmp_identify_number) {
        this.emp_identify_number = getEmp_identify_number;
    }

    public String getEmp_phone_number() {
        return emp_phone_number;
    }

    public void setEmp_phone_number(String emp_phone_number) {
        this.emp_phone_number = emp_phone_number;
    }

    public String getEmp_e_mail() {
        return emp_e_mail;
    }

    public void setEmp_e_mail(String emp_e_mail) {
        this.emp_e_mail = emp_e_mail;
    }

    public String getEmp_nation() {
        return emp_nation;
    }

    public void setEmp_nation(String emp_nation) {
        this.emp_nation = emp_nation;
    }

    public String getEmp_native_place() {
        return emp_native_place;
    }

    public void setEmp_native_place(String emp_native_place) {
        this.emp_native_place = emp_native_place;
    }

    public String getEmp_location() {
        return emp_location;
    }

    public void setEmp_location(String emp_location) {
        this.emp_location = emp_location;
    }

    public String getEmp_edu_bgd() {
        return emp_edu_bgd;
    }

    public void setEmp_edu_bgd(String emp_edu_bgd) {
        this.emp_edu_bgd = emp_edu_bgd;
    }

    public String getEmp_entry_way() {
        return emp_entry_way;
    }

    public void setEmp_entry_way(String emp_entry_way) {
        this.emp_entry_way = emp_entry_way;
    }


    @Override
    public String toString() {
        return "Change{" +
                "emp_info_key='" + emp_info_key + '\'' +
                ", emp_id='" + emp_id + '\'' +
                ", dept_id='" + dept_id + '\'' +
                ", dept_name='" + dept_name + '\'' +
                ", post_id='" + post_id + '\'' +
                ", post_name='" + post_name + '\'' +
                ", emp_name='" + emp_name + '\'' +
                ", emp_sex='" + emp_sex + '\'' +
                ", emp_state='" + emp_state + '\'' +
                ", emp_entry_time='" + emp_entry_time + '\'' +
                ", emp_identify_type='" + emp_identify_type + '\'' +
                ", emp_identify_number='" + emp_identify_number + '\'' +
                ", emp_phone_number='" + emp_phone_number + '\'' +
                ", emp_e_mail='" + emp_e_mail + '\'' +
                ", emp_nation='" + emp_nation + '\'' +
                ", emp_native_place='" + emp_native_place + '\'' +
                ", emp_birthday='" + emp_birthday + '\'' +
                ", emp_location='" + emp_location + '\'' +
                ", emp_edu_bgd='" + emp_edu_bgd + '\'' +
                ", emp_entry_way='" + emp_entry_way + '\'' +
                ", post_change_key='" + post_change_key + '\'' +
                ", change_type='" + change_type + '\'' +
                '}';
    }
}


