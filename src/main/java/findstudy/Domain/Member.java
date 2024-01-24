package findstudy.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer no;
    private String name;
    private String nick;
    private String id;
    private String pw;
    private String email;
    private Integer phone;
    private Integer sns;
}
