package findstudy.Controller;

import findstudy.Domain.Member;
import findstudy.Service.LoginService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {
    @Autowired
    LoginService loginService;



    @RequestMapping("/home")
    public String home(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int auth;

        String id;
        String nick = "";
        // 사용자의 Principal 객체 가져오기 (사용자 정보 포함)
        if (authentication.getPrincipal() instanceof UserDetails){
            auth = 1;
            UserDetails principal = (UserDetails) authentication.getPrincipal();
            id = principal.getUsername();
            Member member = loginService.findMember(id);
            nick = member.getNick();
        }else{
            auth=0;
        }
        model.addAttribute("nick", nick);
        model.addAttribute("auth", auth);
        return "home";
    }

    @RequestMapping("/loginProc")
    public String loginForm(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int auth;
        // 사용자의 Principal 객체 가져오기 (사용자 정보 포함)
        if (authentication.getPrincipal() instanceof UserDetails){auth = 1;}
        else{auth=0;}
        model.addAttribute("auth", auth);
        return "loginForm";
    }

    @RequestMapping("/login_join")
    public String loginJoin(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int auth;
        // 사용자의 Principal 객체 가져오기 (사용자 정보 포함)
        if (authentication.getPrincipal() instanceof UserDetails){auth = 1;}
        else{auth=0;}
        model.addAttribute("auth", auth);
        return "login_join";
    }
    @RequestMapping("/login_find")
    public String loginFind(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int auth;
        // 사용자의 Principal 객체 가져오기 (사용자 정보 포함)
        if (authentication.getPrincipal() instanceof UserDetails){auth = 1;}
        else{auth=0;}
        model.addAttribute("auth", auth);
        return "login_find";
    }
    @RequestMapping("/login_findProc")
    @ResponseBody
    public String loginFindProc(@Param("name")String name, @Param("email") String email){
        Member member = loginService.findByNameAndEmail(name,email);
        if(member == null){return "";}
        return member.getId();
    }

    @RequestMapping("/login_changePw")
    public String loginChangePw(@Param("idVal")String idVal, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int auth;
        // 사용자의 Principal 객체 가져오기 (사용자 정보 포함)
        if (authentication.getPrincipal() instanceof UserDetails){auth = 1;}
        else{auth=0;}
        model.addAttribute("auth", auth);
        model.addAttribute("idStore", idVal);
        return "login_changePw";
    }



    @RequestMapping("/loginJoinProc")
    @ResponseBody
    public int login_joinProc(Member member, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int auth;
        // 사용자의 Principal 객체 가져오기 (사용자 정보 포함)
        if (authentication.getPrincipal() instanceof UserDetails){auth = 1;}
        else{auth=0;}
        model.addAttribute("auth", auth);
        Member memberNull = new Member();

        if(loginService.findMember(member.getId()) != null ){
            return 2; // 2는 이미 아이디가 있다는것
        }
        if(loginService.findMemberByNick(member.getNick()) != null ){
            return 3; // 3은 이미 닉네임이 있다는것
        }
        loginService.saveMember(member);
        return 1; // 성공시 1반환.
    }
}
