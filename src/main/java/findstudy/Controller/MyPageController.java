package findstudy.Controller;

import findstudy.Domain.Member;
import findstudy.Domain.Message;
import findstudy.Repository.MessageRepository;
import findstudy.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MyPageController {

    @Autowired
    LoginService loginService;
    @Autowired
    MessageRepository messageRepository;
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

    @RequestMapping("/myPageMessage")
    public String myPageMessage(Model model){
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

        List<Message> messageList =  messageRepository.findByFromNickOrToNickOrderByNoDesc(nick, nick);
        model.addAttribute("messageList", messageList);
        return "myPageMessage";
    }

    @RequestMapping("/myPageMessageDetail")
    public String myPageMessageDetail(@Param("no")int no, Model model){
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

        Message message = messageRepository.findByNo(no);
        model.addAttribute("message", message);
        return "myPageMessageDetail";
    }

    @RequestMapping("/writeMessage")
    public String writeMessage(Model model, @RequestParam(value="replyTo",required = false, defaultValue = "")String replyTo){
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

        if(replyTo!=""){model.addAttribute("replyTo",replyTo);}


        return "writeMessage";
    }

    @RequestMapping("/writeMessageProc")
    @ResponseBody
    public int writeMessageProc(Message message){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String id2 =  principal.getUsername();
        Member member = loginService.findMember(id2);
        String nick = member.getNick();
        LocalDateTime currentDate = LocalDateTime.now();
        // 날짜를 원하는 형식으로 포맷팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm");
        String formattedDate = currentDate.format(formatter);
        message.setFromNick(nick);
        message.setDates(formattedDate);

        messageRepository.save(message);

        return 1;
    }
}
