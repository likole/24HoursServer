package cn.likole.hours.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

import static com.opensymphony.xwork2.Action.SUCCESS;

/**
 * Created by likole on 4/20/2017.
 */
@Controller
@Scope("prototype")
public class errorController {

    Map<String,Object> map=new HashMap<String,Object>();

    public Map<String, Object> getMap() {
        return map;
    }

    public String notFound(){
        map.put("status",404);
        map.put("message","Not Found");
        return SUCCESS;
    }

}
