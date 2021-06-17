package yju.wdb.finaltermm1601205.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Friend extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int birthYear;

    private String gender;

    private String phone;


//    public void changeTitle(String title){
//        this.title = title;
//    }
//
//    public void changeContent(String content){
//        this.content = content;
//    }
}