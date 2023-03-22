package joel.joelpage.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "loginMember")
public class LoginMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_seq", nullable = false)
    private Long seq;

    @Column(name = "member_id", nullable = false)
    private String memberId;

    @Column(name = "member_pw", nullable = false, length = 100)
    private String password;

    @Column(name = "member_code", nullable = false)
    private int memberCode;  //0은 관리자 1은 평직원

    @OneToOne(mappedBy = "loginMember")
    private Employee employee;
}
