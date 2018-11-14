package net.xinqushi.wechat.web.controller;

import net.xinqushi.wechat.common.utils.PicUtil;
import net.xinqushi.wechat.model.PicUploadResult;
import net.xinqushi.wechat.web.result.CodeMsg;
import net.xinqushi.wechat.web.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/pic")
public class PicController {
    @RequestMapping("/upload")
    @ResponseBody
    public PicUploadResult uploadPic(@RequestParam("file") MultipartFile file, HttpSession session){
      String url;
     PicUploadResult result=new PicUploadResult();
     System.out.println(file.getOriginalFilename());
        try {
             url= new PicUtil().upload(file,PicUtil.PICTURE_CSS_01);
             result.setUrl(url);
            result.setStatus(1);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(0);
            result.setMsg("空间不足");
        }
        return result;
    }
}
