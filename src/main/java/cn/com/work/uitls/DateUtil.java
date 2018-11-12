package cn.com.work.uitls;/**
 * Created by user on 2018/11/11.
 */

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author user
 * @create 2018-11-11 11:12
 * @desc 日期工具
 */
public class DateUtil {

    private static Logger logger = Logger.getLogger(DateUtil.class);

    private DateUtil(){};

    /**
     * 日期格式
     */

    public  static String dateFormat = "yyyy-MM-dd";

    public  static String timeFormat = "HH:mm:ss";

    public  static String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    public static SimpleDateFormat getSdf(String dateTimeType){
        return new SimpleDateFormat(dateTimeType);
    }
    /**
     * 获取前一天日期yyyy-MM-dd
     * @see 经测试，针对闰年02月份或跨年等情况，该代码仍有效。测试代码如下
     * @see calendar.set(Calendar.YEAR, 2013);
     * @see calendar.set(Calendar.MONTH, 0);
     * @see calendar.set(Calendar.DATE, 1);
     * @see 测试时，将其放到<code>calendar.add(Calendar.DATE, -1);</code>前面即可
     * @return 返回的日期格式为yyyy-MM-dd
     */
    public static String getYestoday(){
        return getYestoday(dateFormat);
    }
    public static String getYestoday(String format){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    /**
     * 获取当前的日期 默认 yyyy-MM-dd
     */
    public static String getCurrentDate(){
        return getCurrentDate(dateFormat);
    }
    /**
     * 获取当前的日期
     */
    public static String getCurrentDate(String format){
        return  getSdf(format).format(new Date());
    }

    /**
     *  @see 获取当前的时间
     * @return
     */
    public static String getCurrentTime(){
        return  getSdf(dateTimeFormat).format(new Date());
    }
    /**
     * @see 比较两个时间大小
     * @return
     */
    public static int comparDate(Date bDate,Date eDate ){
        return bDate.compareTo(eDate);
    }
    public static int comparDate(String bDate,String eDate ){
        SimpleDateFormat sdf = getSdf(dateTimeFormat);
        Date beginDate  = null ;
        Date endDate = null ;
        try {
            beginDate = sdf.parse(bDate);
            endDate = sdf.parse(eDate);
        } catch (ParseException e) {
            logger.error("");
        }
        return beginDate.compareTo(endDate);
    }
}
