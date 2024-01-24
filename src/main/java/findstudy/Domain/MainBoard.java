package findstudy.Domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class MainBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer no;
    private String title;
    private String content;
    private Integer views;
    private String writer;
    private String tag1;
    private String tag2;
    private String tag3;
    private Integer sort;
    private String link;
    private Integer likes;
    private String dates;
}
