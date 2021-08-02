package cn.edu.guet.bll.impl;

import cn.edu.guet.bean.CheckLogInfo;
import cn.edu.guet.bean.SysLog;
import cn.edu.guet.bll.ISysLogService;
import cn.edu.guet.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISysLogServiceImpl implements ISysLogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public int insetLog(SysLog log) {
        return logMapper.setLog(log);
    }

    @Override
    public List<CheckLogInfo> checkAllLog() {
        return logMapper.checkAllLog();
    }

    @Override
    public List<CheckLogInfo> checkLogFofKey(String key) {
        return  logMapper.checkLogFofKey(key);
    }
}
