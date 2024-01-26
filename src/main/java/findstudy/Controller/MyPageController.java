package findstudy.Controller;

import findstudy.Domain.Member;
import findstudy.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyPageController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/myPage")
    public String myPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String id =  principal.getUsername();
        Member member = loginService.findMember(id);
        String nick = member.getNick();
        model.addAttribute("nick", nick);


        int auth;
        if (authentication.getPrincipal() instanceof UserDetails){auth = 1;}
        else{auth=0;}
        model.addAttribute("auth", auth);
        model.addAttribute("member", member);
        return "myPage";
    }

    @RequestMapping("/myPageChangePw")
    public String myPageChangePw(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String id2 =  principal.getUsername();
        Member member = loginService.findMember(id2);
        String nick = member.getNick();
        model.addAttribute("nick", nick);
        int auth;
        if (authentication.getPrincipal() instanceof UserDetails){auth = 1;}
        else{auth=0;}
        String id =  principal.getUsername();
        model.addAttribute("id", id);
        model.addAttribute("auth", auth);
        return "myPageChangePw";
    }
    @RequestMapping("/changePwProc")
    @ResponseBody
    public int changePwProc(@Param("pw")String pw, @Param("id")String id, Model model){
        loginService.updatePw(pw,id);
        System.out.println(pw + id);
        return 1;
    }
}
