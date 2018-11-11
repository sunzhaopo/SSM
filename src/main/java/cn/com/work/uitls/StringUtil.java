package cn.com.work.uitls;/**
 * Created by user on 2018/11/7.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * @author user
 * @create 2018-11-07 22:37
 * @desc 字符串工具类
 */
public class StringUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

    private StringUtil(){};
    /**
     * 字符为空
     */
    public static  boolean isEmpty(String str){
        return str == null || str.length() <= 0 || str.replaceAll("\\s", "").length() ==0;
    }
    /**
     判断输入的字节数组是否为空
     * @return boolean 空则返回true,非空则flase
     */
    public static boolean isEmpty(byte[] bytes){
        return null==bytes || 0==bytes.length;
    }

    /**
     * 字符编码
     * @see 该方法默认会以UTF-8编码字符串
     * @see 若想自己指定字符集,可以使用<code>encode(String chinese, String charset)</code>方法
     */
    public static String encode(String chinese){
        return encode(chinese, "UTF-8");
    }
    /**
     * 字符编码
     * @see 该方法通常用于对中文进行编码
     * @see 若系统不支持指定的编码字符集,则直接将<code>chinese</code>原样返回
     */
    public static String encode(String chinese, String charset){
        chinese = (chinese==null ? "" : chinese);
        try {
            return URLEncoder.encode(chinese, charset);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("编码字符串[" + chinese + "]时发生异常:系统不支持该字符集[" + charset + "]");
            return chinese;
        }
    }
    /**
     * 字符解码
     * @see 该方法默认会以UTF-8解码字符串
     * @see 若想自己指定字符集,可以使用<code>decode(String chinese, String charset)</code>方法
     */
    public static String decode(String chinese){
        return decode(chinese, "UTF-8");
    }

    /**
     * 字符解码
     * @see 该方法通常用于对中文进行解码
     * @see 若系统不支持指定的解码字符集,则直接将<code>chinese</code>原样返回
     */
    public static String decode(String chinese, String charset){
        chinese = (chinese==null ? "" : chinese);
        try {
            return URLDecoder.decode(chinese, charset);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("解码字符串[" + chinese + "]时发生异常:系统不支持该字符集[" + charset + "]");
            return chinese;
        }
    }

}
