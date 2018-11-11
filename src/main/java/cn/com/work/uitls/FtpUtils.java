package cn.com.work.uitls;/**
 * Created by user on 2018/11/11.
 */

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.SocketException;

/**
 * @author user
 * @create 2018-11-11 11:36
 * @desc ftp 工具类
 */
public class FtpUtils {

    private static Logger logger = Logger.getLogger(FtpUtils.class);

    private FTPClient ftpClient = null;
    private static String ftpHost;
    private static int ftpPort;
    private static String ftpUserName;
    private static String ftpUserPassword;
    public FtpUtils(String ftpHost,int ftpPort ,String ftpUserName,String ftpUserPassword){
        this.ftpHost = ftpHost;
        this.ftpPort = ftpPort;
        this.ftpUserName = ftpUserName;
        this.ftpUserPassword = ftpUserPassword;
    }
    public FtpUtils(){}
    /**
     * 获取FTPClient对象
     * @param ftpHost FTP主机服务器
     * @param ftpPassword FTP 登录密码
     * @param ftpUserName FTP登录用户名
     * @param ftpPort FTP端口 默认为21
     * @return
     */
    public static FTPClient getFTPClient() {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpUserPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                logger.info("未连接到FTP,用户名或密码错误!");
                ftpClient.disconnect();
            } else {
                logger.info("FTP连接成功!");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            logger.info("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }
}
