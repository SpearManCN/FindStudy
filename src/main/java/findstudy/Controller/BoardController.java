package findstudy.Controller;

import findstudy.Domain.MainBoard;
import findstudy.Domain.Member;
import findstudy.Service.BoardService;
import findstudy.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class BoardController {
    @Autowired
    LoginService loginService;
    @Autowired
    BoardService boardService;

    @RequestMapping("/study")
    public String study(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int auth;
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String id =  principal.getUsername();
        Member member = loginService.findMember(id);
        String nick = member.getNick();
        model.addAttribute("nick", nick);
        // 사용자의 Principal 객체 가져오기 (사용자 정보 포함)
        if (authentication.getPrincipal() instanceof UserDetails){auth = 1;}
        else{auth=0;}
        model.addAttribute("auth", auth);
        return "study";
    }
    @RequestMapping("/group")
    public String group(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int auth;
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String id =  principal.getUsername();
        Member member = loginService.findMember(id);
        String nick = member.getNick();
        model.addAttribute("nick", nick);
        // 사용자의 Principal 객체 가져오기 (사용자 정보 포함)
        if (authentication.getPrincipal() instanceof UserDetails){auth = 1;}
        else{auth=0;}
        model.addAttribute("auth", auth);
        return "group";
    }
    @RequestMapping("/community")
    public String community(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int auth;
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String id =  principal.getUsername();
        Member member = loginService.findMember(id);
        String nick = member.getNick();
        model.addAttribute("nick", nick);
        // 사용자의 Principal 객체 가져오기 (사용자 정보 포함)
        if (authentication.getPrincipal() instanceof UserDetails){auth = 1;}
        else{auth=0;}
        model.addAttribute("auth", auth);
        return "community";
    }

    @RequestMapping("/writeBoard")
    public String writeBoard(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int auth;
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String id =  principal.getUsername();
        Member member = loginService.findMember(id);
        String nick = member.getNick();
        model.addAttribute("nick", nick);
        // 사용자의 Principal 객체 가져오기 (사용자 정보 포함)
        if (authentication.getPrincipal() instanceof UserDetails){auth = 1;}
        else{auth=0;}
        model.addAttribute("auth", auth);
        return "writeBoard";
    }

    @RequestMapping("/writeBoardProc")
    @ResponseBody
    public int writeBoardProc(MainBoard mainBoard){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String id =  principal.getUsername();
        Member member = loginService.findMember(id);
        String nick = member.getNick();
        mainBoard.setViews(0);
        mainBoard.setWriter(nick);
        mainBoard.setSort(0); // 작업해줘야함 셀렉트박스
        mainBoard.setLikes(0);

        LocalDate currentDate = LocalDate.now();
        // 날짜를 원하는 형식으로 포맷팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        mainBoard.setDates(formattedDate);

        boardService.saveBoard(mainBoard);


        return 1;
    }


}
