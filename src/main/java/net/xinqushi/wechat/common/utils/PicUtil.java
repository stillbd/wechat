package net.xinqushi.wechat.common.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
*@author  李志龙
*@created 16:17 2018/8/24
 *@classname PicUtil
*@classdescription
 *  上传图片到七牛云
*
*/
public class PicUtil {
    //秘钥
    final String ACCESS_KEY = "eGhj-pHW_daavswNDqnbNER3tOs8hEKejTofVvnr";
    final String SECRET_KEY = "c2LdKG3AvBn0UhYg7PFdBswVz6uG_oikmrvr99cl";
    final String bucketname = "picture";
    final String domainOfBucket="http://pdx11avb0.bkt.clouddn.com/";
   public  static final String PICTURE_CSS_01="-ZB";//将图片后缀加上这个，可以以缩略图形式显示
    //密钥配置
    final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    //创建上传对象
    final UploadManager uploadManager =new UploadManager();
    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    public String upload(File file,String picCss) throws IOException {
        try {
         String fileName=file.getName();
            String suffix=fileName.substring(fileName.lastIndexOf('.'));
            //随机生成图片名
            String key = UUID.randomUUID() + suffix;
            //调用put方法上传

            Response res = uploadManager.put(file, key, getUpToken());

            //打印返回的信息
//             System.out.println("222222"+res.bodyString());
              System.out.println(domainOfBucket+key); //图片外链
            return domainOfBucket+key+picCss;
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println("00000"+r.toString());
            return r.toString();

        }

    }
    public String upload(MultipartFile file,String picCss) throws IOException {
        try {
            String fileName=file.getOriginalFilename();
            System.out.println(fileName);
            String suffix=fileName.substring(fileName.lastIndexOf('.'));
            //随机生成图片名
            String key = UUID.randomUUID() + suffix;
            //调用put方法上传

            Response res = uploadManager.put(file.getBytes(), key, getUpToken());

            //打印返回的信息
            System.out.println("222222"+res.bodyString());
            System.out.println(domainOfBucket+key); //图片外链
            return domainOfBucket+key+picCss;
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println("00000"+r.toString());
            return r.toString();

        }

    }

    public static void main(String[] args) {
        File file=new File("E:\\图片\\11.jpg");
        try {
            new PicUtil().upload(file,PicUtil.PICTURE_CSS_01);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
