package cn.edu.guet.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResult <T>{
    private Integer code = 0;
    private String msg;
    private Long count;
    private T data;
}
