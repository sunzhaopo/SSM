package cn.com.work.uitls;/**
 * Created by user on 2018/11/11.
 */

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.SocketException;

/**
 * @author user
 * @create 2018-11-11 11:36
 * @desc ftp 工具类
 */
public class FtpUtils {

    private static Logger logger = Logger.getLogger(FtpUtils.class);

    private static FTPClient ftpClient = null;
    /**ftp服务器地址 */
    private static String ftpHost;
    /**ftp服务器端口默认21 */
    private static int ftpPort = 21;
    /**ftp服务器用户名*/
    private static String ftpUserName;
    /***ftp服务器密码*/
    private static String ftpUserPassword;
    /** 设置缓冲区大小4M **/
    private static final int BUFFER_SIZE = 1024 * 1024 * 4;
    /** 本地字符编码  **/
    private static String localCharset = "GBK";

    /** FTP协议里面，规定文件名编码为iso-8859-1 **/
    private static String serverCharset = "ISO-8859-1";

    /** UTF-8字符编码 **/
    private static final String CHARSET_UTF8 = "UTF-8";
    /** FTP基础目录 **/
    private static final String BASE_PATH = "ftp/";

    /** OPTS UTF8字符串常量 **/
    private static final String OPTS_UTF8 = "OPTS UTF8";
    public FtpUtils(String ftpHost, int ftpPort, String ftpUserName, String ftpUserPassword) {
        this.ftpHost = ftpHost;
        this.ftpPort = ftpPort;
        this.ftpUserName = ftpUserName;
        this.ftpUserPassword = ftpUserPassword;
    }

    public FtpUtils() {
    }

    /**
     * 获取FTPClient对象
     *
     * param  ftpHost     FTP主机服务器
     * param  ftpPassword FTP 登录密码
     * param  ftpUserName FTP登录用户名
     * param  ftpPort     FTP端口 默认为21
     * return
     */
    private static FTPClient getFTPClient() {
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

    /**
     * 关闭FTP连接
     */
    private void closeConnect() {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                logger.error("关闭FTP连接失败", e);
            }
        }
    }
    /**
     * FTP服务器路径编码转换
     *
     * @param ftpPath FTP服务器路径
     * @return String
     */
    private static String changeEncoding(String ftpPath) {
        String directory = null;
        try {
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
                localCharset = CHARSET_UTF8;
            }
            directory = new String(ftpPath.getBytes(localCharset), serverCharset);
        } catch (Exception e) {
            logger.error("路径编码转换失败", e);
        }
        return directory;
    }
    /**
          * 在服务器上递归创建目录
          *
          * @param dirPath 上传目录路径
          * @return
          */
    private void createDirectorys(String dirPath) {
        try {
            if (!dirPath.endsWith("/")) {
                dirPath += "/";
            }
            String directory = dirPath.substring(0, dirPath.lastIndexOf("/") + 1);
            ftpClient.makeDirectory("/");
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            }else{
                start = 0;
            }
            end = directory.indexOf("/", start);
            while(true) {
                String subDirectory = new String(dirPath.substring(start, end));
                if (!ftpClient.changeWorkingDirectory(subDirectory)) {
                    if (ftpClient.makeDirectory(subDirectory)) {
                        ftpClient.changeWorkingDirectory(subDirectory);
                    } else {
                        logger.info("创建目录失败");
                        return;
                    }
                }
                start = end + 1;
                end = directory.indexOf("/", start);
                //检查所有目录是否创建完毕  
                if (end <= start) {
                    break;
                }
            }
        } catch (Exception e) {
            logger .error("上传目录创建失败", e);
        }
    }

    /**
     * 本地文件上传到FTP服务器
     *
     * @param ftpPath FTP服务器文件相对路径，例如：test/123
     * @param savePath 本地文件路径，例如：D:/test/123/test.txt
     * @param fileName 上传到FTP服务的文件名，例如：666.txt
     * @return boolean 成功返回true，否则返回false
     */
    public boolean uploadLocalFile(String ftpPath, String savePath, String fileName) {
        // 登录
        getFTPClient();
        boolean flag = false;
        if (ftpClient != null) {
            File file = new File(savePath);
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                ftpClient.setBufferSize(BUFFER_SIZE);
                // 设置编码：开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）
                if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
                    localCharset = CHARSET_UTF8;
                }
                ftpClient.setControlEncoding(localCharset);
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                String path = changeEncoding(BASE_PATH + ftpPath);
                // 目录不存在，则递归创建
                if (!ftpClient.changeWorkingDirectory(path)) {
                   this.createDirectorys(path);
                }
                // 设置被动模式，开通一个端口来传输数据
                ftpClient.enterLocalPassiveMode();
                // 上传文件
                flag = ftpClient.storeFile(new String(fileName.getBytes(localCharset), serverCharset), fis);
            } catch (Exception e) {
                logger.error("本地文件上传FTP失败", e);
            } finally {
                IOUtils.closeQuietly(fis);
                closeConnect();
            }
        }
        return flag;
    }
    /**
     * 下载指定文件到本地
     *
     * @param ftpPath FTP服务器文件相对路径，例如：test/123
     * @param fileName 要下载的文件名，例如：test.txt
     * @param savePath 保存文件到本地的路径，例如：D:/test
     * @return 成功返回true，否则返回false
     */
    public boolean downloadFile(String ftpPath, String fileName, String savePath) {
        // 登录
        getFTPClient();
        boolean flag = false;
        if (ftpClient != null) {
            try {
                String path = changeEncoding(BASE_PATH + ftpPath);
                // 判断是否存在该目录
                if (!ftpClient.changeWorkingDirectory(path)) {
                    logger.error(BASE_PATH + ftpPath + "该目录不存在");
                    return flag;
                }
                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
                String[] fs = ftpClient.listNames();
                // 判断该目录下是否有文件
                if (fs == null || fs.length == 0) {
                    logger.error(BASE_PATH + ftpPath + "该目录下没有文件");
                    return flag;
                }
                for (String ff : fs) {
                    String ftpName = new String(ff.getBytes(serverCharset), localCharset);
                    if (ftpName.equals(fileName)) {
                        File file = new File(savePath + '/' + ftpName);
                        try (OutputStream os = new FileOutputStream(file)) {
                            flag = ftpClient.retrieveFile(ff, os);
                        } catch (Exception e) {
                            logger.error(e.getMessage(), e);
                        }
                        break;
                    }
                }
            } catch (IOException e) {
                logger.error("下载文件失败", e);
            } finally {
                closeConnect();
            }
        }
        return flag;
    }
}
