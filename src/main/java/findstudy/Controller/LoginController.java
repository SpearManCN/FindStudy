package findstudy.Controller;

import findstudy.Domain.Member;
import findstudy.Service.LoginService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;



    @RequestMapping("/home")
    public String home(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 사용자의 Principal 객체 가져오기 (사용자 정보 포함)

        Member member = loginService.findMember("cn1056");
        model.addAttribute("member", member);
        if(authentication.getPrincipal() instanceof UserDetails){
            UserDetails principal = (UserDetails) authentication.getPrincipal();
            model.addAttribute("user", principal);
        }

        System.out.println(member);
        return "home";
    }

    @RequestMapping("/loginProc")
    public String loginForm(){
        return "loginForm";
    }

    @RequestMapping("/home2")
    public String home2(){
        return "home2";
    }
    @RequestMapping("/study")
    public String study(){
        return "study";
    }



}
