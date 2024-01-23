package findstudy.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Member {
    @Id
    private Integer no;
    private String name;
    private String id;
    private String pw;
    private String email;
    private Integer phone;
}
