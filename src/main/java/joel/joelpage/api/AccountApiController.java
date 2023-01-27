package joel.joelpage.api;

import joel.joelpage.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AccountApiController {
    private final LoginRepository loginRepository;

    @PostMapping("/api/account/login")
    public Long login(
            @RequestBody Map<String,String> params
            ) {
        loginRepository.findByMemberIdAndPassword(params.get("id"), params.get("password"));
        return null;
    }
}
