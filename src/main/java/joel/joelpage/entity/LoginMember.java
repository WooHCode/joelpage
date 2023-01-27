package joel.joelpage.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "members")
public class LoginMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id", nullable = false)
    private Long id;

    @Column(name = "member_ide", nullable = false)
    private String memberId;

    @Column(name = "member_pw", nullable = false, length = 100)
    private String password;
}
