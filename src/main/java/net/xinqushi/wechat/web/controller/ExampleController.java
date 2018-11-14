package net.xinqushi.wechat.web.controller;

import net.xinqushi.wechat.pojo.Example;
import net.xinqushi.wechat.web.result.Result;
import net.xinqushi.wechat.web.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class ExampleController {
    @Autowired
    ExampleService exampleService;
    @RequestMapping("/thymeleaf")
    public String  thymeleaf(Model model){
        model.addAttribute("name","你是我的夜");
        return "hello";
    }
    @RequestMapping("/select")
    @ResponseBody
    public Result<Example> select(@RequestParam  int id){
     Result<Example> result=exampleService.getUserById(id);
       return result;
    }
//    @RequestMapping("/page/{pageName}")
//    public String toPage(@PathVariable String pageName){
//
//        return pageName;
//    }
}
