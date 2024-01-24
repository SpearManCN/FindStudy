package findstudy.Repository;

import findstudy.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LoginRepository extends JpaRepository<Member, Long> {
    public Member findById(String id);
    public Member findByNick(String nick);
    public Member save(Member member);

    @Modifying
    @Transactional
    @Query("UPDATE Member m set m.pw = :pw where m.id = :id")
    public void updatePwById(@Param("pw") String pw, @Param("id") String id);
}
