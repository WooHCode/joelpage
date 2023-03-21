package joel.joelpage.api;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import joel.joelpage.entity.LoginMember;
import joel.joelpage.repository.LoginRepository;
import joel.joelpage.service.JwtService;
import joel.joelpage.service.JwtServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final LoginRepository loginRepository;

    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Map<String, String> params,
                                HttpServletResponse response) throws NoSuchAlgorithmException, InvalidKeySpecException {
        LoginMember loginMember = loginRepository.findByMemberIdAndPassword(params.get("ide"), params.get("password"));

        if (loginMember != null) {
            JwtService jwtService = new JwtServiceImpl();
            Long id = loginMember.getSeq();
            String token = jwtService.getToken("id", id);

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);
            return ResponseEntity.ok().build();

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
