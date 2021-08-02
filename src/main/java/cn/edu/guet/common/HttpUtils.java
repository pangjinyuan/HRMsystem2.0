package cn.edu.guet.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static cn.edu.guet.common.CastUtils.cast;


public class HttpUtils {
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes =
                cast(RequestContextHolder.getRequestAttributes(), ServletRequestAttributes.class);
        HttpServletRequest request = null;
        if (requestAttributes != null) {
            request = requestAttributes.getRequest();
        }
        return request;
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static <T> T getAttribute(String keyName, Class<T> type) {
        return (T) getSession().getAttribute(keyName);
    }

    public static void setAttribute(String ketName, Object value) {
        getSession().setAttribute(ketName, value);
    }
}
