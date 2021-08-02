package cn.edu.guet.mapper;

import cn.edu.guet.bean.CheckLogInfo;
import cn.edu.guet.bean.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogMapper {
    public int setLog(@Param("log") SysLog log);
    public List<CheckLogInfo> checkAllLog();
    public List<CheckLogInfo> checkLogFofKey(String key);
}
