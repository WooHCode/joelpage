package joel.joelpage.api;

import joel.joelpage.entity.LoginMember;
import joel.joelpage.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final LoginRepository loginRepository;

    @PostMapping("/api/account/login")
    public Long login(@RequestBody Map<String, String> params) {
        LoginMember loginMember = loginRepository.findByMemberIdAndPassword(params.get("ide"), params.get("password"));

        if (loginMember != null) {
            return loginMember.getSeq();
        }
        return 0l;
    }
}
