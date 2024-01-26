package findstudy.Service;

import findstudy.Domain.Member;
import findstudy.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    LoginRepository loginRepository;
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = loginRepository.findById(id);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(member == null){
            throw new UsernameNotFoundException("User not found with username: " + id);
        }

        return new User(member.getId(), member.getPw(), authorities);
    }

}
