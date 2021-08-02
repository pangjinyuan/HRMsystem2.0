package cn.edu.guet.config;

import cn.edu.guet.bean.SysLog;
import cn.edu.guet.bll.ISysLogService;
import cn.edu.guet.utiltool.Sessionkey;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

/**

 @author Promise

 @createTime 2018年12月18日 下午9:33:28

 @description 切面日志配置
 */
@Aspect
@Component
public class LogAsPect {

    private final static Logger log = org.slf4j.LoggerFactory.getLogger(LogAsPect.class);

    @Autowired
    private ISysLogService sysLogService;

    //表示匹配带有自定义注解的方法
    @Pointcut("@annotation(cn.edu.guet.config.Log)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result =null;
        long beginTime = System.currentTimeMillis();
        try {
            log.info("我在目标方法之前执行！");
            result = point.proceed();
            long endTime = System.currentTimeMillis();
            insertLog(point,endTime-beginTime);
        } catch (Throwable e) {
            // TODO Auto-generated catch block
        }
        return result;
    }

    private void insertLog(ProceedingJoinPoint point ,long time) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        SysLog sys_log = new SysLog();
        Log userAction = method.getAnnotation(Log.class);
        String action = null;
        if (userAction != null) {
            // 注解上的描述
            action=userAction.value();
        }
        //利用拦截器记录session emp_id
        HttpSession session=null;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if(requestAttributes != null){
            session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
            // Do something
        }
        String emp_id=(String)session.getAttribute(Sessionkey.SESSION_KEY);
        // 请求的类名
        String className = point.getTarget().getClass().getName();
        // 请求的方法名
        String methodName = signature.getName();
        // 请求的方法参数值
        String args = Arrays.toString(point.getArgs());

        action=action+",类名:"+className+",方法名:"+methodName+",参数："+args+",执行时间："+time;
        sys_log.setEmp_action(action);
        //获取当前时间
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        //System.out.println("当前时间为："+retStrFormatNowDate);
        //String emp_id = "m1000";//应该从session中获取当前登录人的id，这里简单模拟下
        System.out.println(emp_id);
        sys_log.setEmp_id(emp_id);
        sys_log.setCreate_date(retStrFormatNowDate);
        log.info("当前登陆人：{},类名:{},方法名:{},参数：{},执行时间：{}",emp_id, className, methodName, args, time);

        sysLogService.insetLog(sys_log);
    }
 }