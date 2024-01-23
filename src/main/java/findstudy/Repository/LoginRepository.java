package findstudy.Repository;

import findstudy.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LoginRepository extends JpaRepository<Member, Long> {
    public Member findById(String id);

}
