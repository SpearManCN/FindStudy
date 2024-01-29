package findstudy.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import findstudy.Domain.KakaoMember;
import findstudy.Domain.Member;
import findstudy.Domain.OauthToken;
import findstudy.Service.LoginService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


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
        if(member.getPw()==null||member.getPw()==""){
            member.setId("kakao"+member.getEmail());
            member.setPw("cn1056");
        }

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

    @GetMapping("/goKakao")
    public String goKakao(){
        return "redirect:https://kauth.kakao.com/oauth/authorize?client_id=089f7b2b7117e7dfafb58a1638cc179e&redirect_uri=http://localhost:8080/home2&response_type=code";

    }

    @GetMapping("/home2")
    public String goKakao(@RequestParam("code")String code, Model model, HttpServletRequest request){

        //여기서 인가코드를 이미 받아옴 그게 code 파람
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id","089f7b2b7117e7dfafb58a1638cc179e");
        params.add("redirect_uri","http://localhost:8080/home2");
        params.add("code",code);

        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
                new HttpEntity<>(params,headers);

        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token"
                , HttpMethod.POST
                ,kakaoTokenRequest
                ,String.class
        );

        // 여기서 reponse에 token이 들어가있음.

        ObjectMapper objectMapper = new ObjectMapper();
        OauthToken oauthToken = null;
        try{
            oauthToken = objectMapper.readValue(response.getBody(), OauthToken.class);
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }

        // 토큰이용해서 정보가져오기
        RestTemplate rt2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());

        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");


        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest =
                new HttpEntity<>(headers2);

        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me"
                , HttpMethod.POST
                ,kakaoProfileRequest
                ,String.class
        );
        //reponse2 에는 토큰으로 받아온 유저정보가 들어있다.


        //받은 회원정보 json객체를 object로 바꾼뒤 값 꺼내기


        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoMember kakaoMember = new KakaoMember();
        Map map = new HashMap();
        try{
            map = objectMapper2.readValue(response2.getBody(), Map.class);
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
        Map map3 = (Map) map.get("kakao_account");
        String kakaoEmail = (String) map3.get("email");
        //해당 이메일로 가입된 아이디가 있는지 확인 후
        //아이디가 있을시, 해당 아이디로 접속 후 home으로
        //아이디가 없을시, 해당 이메일으로 회원가입하는 폼으로


        Member kMember = loginService.findByEmail(kakaoEmail);

        if(kMember == null){
            model.addAttribute("kakaoEmail", kakaoEmail);
            return "kakaoJoin";
        }else{
            model.addAttribute("id",kMember.getId());
            model.addAttribute("pw",kMember.getPw());
            return "tmpPage";
        }

//        if(loginService.selectMember(kakaoJoin)==null){
//            loginService.join(kakaoJoin);
//        }
//        HttpSession session = request.getSession();
//        session.setAttribute("user", kakaoJoin);
    }

}
