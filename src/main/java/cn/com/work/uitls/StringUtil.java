package cn.com.work.uitls;/**
 * Created by user on 2018/11/7.
 */

/**
 * @author user
 * @create 2018-11-07 22:37
 * @desc 字符串工具类
 */
public class StringUtil {
    /**
     * 字符为空
     */
    public static  boolean isEmpty(String str){
        return str == null || str.length() <= 0;
    }
    public static  boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
