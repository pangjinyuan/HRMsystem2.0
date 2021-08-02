package cn.edu.guet.common;

public class CastUtils {
    public static<T> T cast(Object object, Class<T> type){
        return (T) object;
    }
}
