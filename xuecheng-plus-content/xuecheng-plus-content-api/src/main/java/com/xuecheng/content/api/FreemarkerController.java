package com.xuecheng.content.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author cmy
 * @version 1.0
 * @description TODO
 * @date 2023/3/6 11:28
 */
@Controller//返回静态页面，非jason，使用controller
public class FreemarkerController {

    @GetMapping("/testfreemarker")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        //设置模型数据
        modelAndView.addObject("name","小明");
        //设置视图名称，就是模板文件名称去掉ftl
        modelAndView.setViewName("test");
        return modelAndView;
    }


}
