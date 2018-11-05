package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

@Controller
@RequestMapping(value = "/abc")
public class HelloWorldController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping(value = "/test")
    public ModelAndView sayHello(@RequestParam(required = false, value = "desc") String desc) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("username", userService.getUserName());
        mv.addObject("desc", desc);
        // 设置视图解析器名称/index.jsp
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/testPathVariable/{id}")
    public ModelAndView testPathVariable(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        System.out.println("testPathVariable id=" + id);
        return mv;
    }
}
