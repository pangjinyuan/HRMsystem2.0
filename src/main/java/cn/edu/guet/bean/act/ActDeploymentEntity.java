package cn.edu.guet.bean.act;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActDeploymentEntity {
    private String id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date deploymentTime;

}
