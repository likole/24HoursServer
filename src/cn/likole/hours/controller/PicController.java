package cn.likole.hours.controller;

import cn.likole.hours.service.PicService;
import cn.likole.hours.service.UserService;
import cn.likole.hours.util.MD5Util;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by likole on 6/4/2017.
 */
@Controller
@Scope("prototype")
public class PicController extends ActionSupport{

    @Autowired
    UserService userService;

    private File pic;
    private String picFileName;
    private String picContentType;
    private String token;

    public File getPic() {
        return pic;
    }

    public void setPic(File pic) {
        this.pic = pic;
    }

    public String getPicFileName() {
        return picFileName;
    }

    public void setPicFileName(String picFileName) {
        this.picFileName = picFileName;
    }

    public String getPicContentType() {
        return picContentType;
    }

    public void setPicContentType(String picContentType) {
        this.picContentType = picContentType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    Map<String,Object> map=new HashMap<String,Object>();

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Autowired
    PicService picService;

    public String setAvatar() throws IOException, NoSuchAlgorithmException {
        if(token==null||token.length()!=32)
        {
            map.put("status",105);
            map.put("message","token值无效！");
            return  SUCCESS;
        }else if(pic==null)
        {
            map.put("status",201);
            map.put("message","图片无效！");
            return  SUCCESS;
        }
        String root= ServletActionContext.getServletContext().getRealPath("/pic");
        InputStream is=new FileInputStream(pic);
        String newname=MD5Util.MD5encode(picFileName)+picFileName.substring(picFileName.lastIndexOf('.'));
        OutputStream os=new FileOutputStream(new File(root,newname ));
        byte[] buffer=new byte[1024];
        while(is.read(buffer,0,buffer.length)!=-1)
        {
            os.write(buffer);
        }
        is.close();
        os.close();
        picService.setAvatar(token,newname);
        map.put("status",0);
        return SUCCESS;
    }

}
