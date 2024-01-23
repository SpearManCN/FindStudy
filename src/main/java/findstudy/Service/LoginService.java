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
}
