package cn.edu.guet.bean;

public class Post {
    private Integer id;
    private String post_id;
    private String post_name;
    private String post_level;
    private String post_resp;
    public Post(){

    }
    public Post(Integer id, String post_id, String post_name, String post_level, String post_resp) {
        this.id = id;
        this.post_id = post_id;
        this.post_name = post_name;
        this.post_level = post_level;
        this.post_resp = post_resp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getPost_level() {
        return post_level;
    }

    public void setPost_level(String post_level) {
        this.post_level = post_level;
    }

    public String getPost_resp() {
        return post_resp;
    }

    public void setPost_resp(String post_resp) {
        this.post_resp = post_resp;
    }
}
