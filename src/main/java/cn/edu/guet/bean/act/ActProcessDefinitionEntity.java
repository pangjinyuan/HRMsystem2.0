package cn.edu.guet.bean.act;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActProcessDefinitionEntity {
    private String id;
    private String name;
    private String key;
    private Integer version;
    private String deploymentId;
    private String resourceName;
    private String diagramResourceName;
}
