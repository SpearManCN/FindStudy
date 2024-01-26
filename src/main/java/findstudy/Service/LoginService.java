package findstudy.Service;

import findstudy.Domain.Member;
import findstudy.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    public Member findMember(String id){
        return loginRepository.findById(id);
    }
    public Member findMemberByNick(String nick){
        return loginRepository.findByNick(nick);
    }
    public Member saveMember(Member member){
        return loginRepository.save(member);
    }

    public void updatePw(String pw, String id){
        loginRepository.updatePwById(pw,id);
    }

    public Member findByNameAndEmail(String name, String email){
        return loginRepository.findByNameAndEmail(name,email);
    }
}
