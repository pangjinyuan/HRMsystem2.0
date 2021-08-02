package cn.edu.guet.bll;

import cn.edu.guet.bean.CheckLogInfo;
import cn.edu.guet.bean.SysLog;

import java.util.List;

public interface ISysLogService {
    int insetLog(SysLog log);
    List<CheckLogInfo> checkAllLog();
    List<CheckLogInfo> checkLogFofKey(String key);
}
