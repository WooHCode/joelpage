package joel.joelpage.repository;

import joel.joelpage.entity.LoginMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginMember, Long> {
    LoginMember findByMemberIdAndPassword(String ide, String password);

}
