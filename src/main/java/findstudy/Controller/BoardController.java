package findstudy.Controller;

import findstudy.Domain.Comment;
import findstudy.Domain.MainBoard;
import findstudy.Domain.Member;
import findstudy.Service.BoardService;
import findstudy.Service.CommentService;
import findstudy.Service.LoginService;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {
    @Autowired
    LoginService loginService;
    @Autowired
    BoardService boardService;
    @Autowired
    CommentService commentService;

    @RequestMapping("/study")
    public String study(Model model, @RequestParam(value="nowPageNo",required = false, defaultValue = "1")int nowPageNo
           , @RequestParam(value="search",required = false, defaultValue = "")String search
    ){
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
        List<MainBoard> beforeResult = boardService.selectAllSearch(1,search);
        if(beforeResult.isEmpty()){return "/study";}
        // 페이징 처리
        int totNo = beforeResult.size();
        int noPerPage = 10; //페이지당 나타낼 최대 글 갯수
        int zeroLast = 1; //총 갯수가 10단위인지
        if(totNo%10==0){zeroLast = 0;}
        int totPage = totNo/10 + zeroLast; //totpage는 총 페이지의 갯수( 글갯수아님)
        int startBoardNo = (nowPageNo)*10-10; //페이지당 시작글 번호
        int endBoardNo = startBoardNo +10;  //페이지당 끝 글 번호
        int totNoData = totNo%10;
        if(totNoData==0){totNoData=10;}
        if(nowPageNo == totPage){endBoardNo= startBoardNo + totNoData; }


            List<MainBoard> afterResult = beforeResult.subList(startBoardNo,endBoardNo);
            model.addAttribute("boards", afterResult);
            List pageList = new ArrayList();
            model.addAttribute("nowPageNo", nowPageNo);
            model.addAttribute("totPage", totPage);

        return "study";
    }
    @RequestMapping("/group")
    public String group(Model model, @RequestParam(value="nowPageNo",required = false, defaultValue = "1")int nowPageNo
            , @RequestParam(value="search",required = false, defaultValue = "")String search){

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
        List<MainBoard> beforeResult = boardService.selectAllSearch(2, search);


        if(beforeResult.isEmpty()){return "/group";}
        int totNo = beforeResult.size();
        int noPerPage = 10; //페이지당 나타낼 최대 글 갯수
        int zeroLast = 1; //총 갯수가 10단위인지
        if(totNo%10==0){zeroLast = 0;}
        int totPage = totNo/10 + zeroLast; //totpage는 총 페이지의 갯수( 글갯수아님)
        int startBoardNo = (nowPageNo)*10-10; //페이지당 시작글 번호
        int endBoardNo = startBoardNo +10;  //페이지당 끝 글 번호
        int totNoData = totNo%10;
        if(totNoData==0){totNoData=10;}
        if(nowPageNo == totPage){endBoardNo= startBoardNo + totNoData; }
        List<MainBoard> afterResult = beforeResult.subList(startBoardNo,endBoardNo);
        model.addAttribute("boards", afterResult);
        List pageList = new ArrayList();
        model.addAttribute("nowPageNo", nowPageNo);
        model.addAttribute("totPage", totPage);



        return "group";
    }


    @RequestMapping("/community")
    public String community(Model model, @RequestParam(value="nowPageNo",required = false, defaultValue = "1")int nowPageNo
            , @RequestParam(value="search",required = false, defaultValue = "")String search){
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
        List<MainBoard> beforeResult = boardService.selectAllSearch(3,search);


        if(beforeResult.isEmpty()){return "/community";}
        int totNo = beforeResult.size();
        int noPerPage = 10; //페이지당 나타낼 최대 글 갯수
        int zeroLast = 1; //총 갯수가 10단위인지
        if(totNo%10==0){zeroLast = 0;}
        int totPage = totNo/10 + zeroLast; //totpage는 총 페이지의 갯수( 글갯수아님)
        int startBoardNo = (nowPageNo)*10-10; //페이지당 시작글 번호
        int endBoardNo = startBoardNo +10;  //페이지당 끝 글 번호
        int totNoData = totNo%10;
        if(totNoData==0){totNoData=10;}
        if(nowPageNo == totPage){endBoardNo= startBoardNo + totNoData; }
        List<MainBoard> afterResult = beforeResult.subList(startBoardNo,endBoardNo);
        model.addAttribute("boards", afterResult);
        List pageList = new ArrayList();
        model.addAttribute("nowPageNo", nowPageNo);
        model.addAttribute("totPage", totPage);
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
        mainBoard.setSort(mainBoard.getSort()); // 작업해줘야함 셀렉트박스
        mainBoard.setLikes(0);

        LocalDateTime currentDate = LocalDateTime.now();
        // 날짜를 원하는 형식으로 포맷팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm");
        String formattedDate = currentDate.format(formatter);
        mainBoard.setDates(formattedDate);

        boardService.saveBoard(mainBoard);
        return 1;
    }


    @RequestMapping("/boardDetail")
    public String boardDetail(@Param("no")int no, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int auth;
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String id =  principal.getUsername();
        Member member = loginService.findMember(id);
        String nick = member.getNick();
        model.addAttribute("nick", nick);
        if (authentication.getPrincipal() instanceof UserDetails){auth = 1;}
        else{auth=0;}
        model.addAttribute("auth", auth);

        MainBoard board = boardService.findByNo(no);
        model.addAttribute("mainBoard", board);

        List<Comment> comments = commentService.findByBoardNo(no);
        model.addAttribute("comments",comments);
        boardService.updateView(no);

        return "boardDetail";
    }


    @RequestMapping("/commentProc")
    @ResponseBody
    public int commentProc(Comment comment){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String id =  principal.getUsername();
        Member member = loginService.findMember(id);
        comment.setWriter(member.getNick());
        LocalDateTime currentDate = LocalDateTime.now();
        // 날짜를 원하는 형식으로 포맷팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm");
        String formattedDate = currentDate.format(formatter);
        comment.setDates(formattedDate);

        commentService.save(comment);

        return 1;
    }
}
