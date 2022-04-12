package hello.gonggugongbae.controller;

import hello.gonggugongbae.domain.location.Location;
import hello.gonggugongbae.domain.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/gggb/members/")
public class MemberController {

    @RequestMapping("new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }

    @RequestMapping("save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        double lat = Double.parseDouble(request.getParameter("latitude"));
        double lon = Double.parseDouble(request.getParameter("longitude"));
        Location location = new Location(lat, lon);

        Member member = new Member(username, location);
        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }
}
