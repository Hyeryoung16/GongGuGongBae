package hello.gonggugongbae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberFormController {

    @RequestMapping("/gggb/members/new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }
}
