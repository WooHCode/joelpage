package joel.joelpage.api;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import joel.joelpage.entity.LoginMember;
import joel.joelpage.repository.LoginRepository;
import joel.joelpage.service.EmployeeService;
import joel.joelpage.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final LoginRepository loginRepository;
    private final EmployeeService employeeService;
    private final JwtService jwtService;

    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Map<String, String> params,
                                HttpServletResponse response) throws NoSuchAlgorithmException, InvalidKeySpecException {
        LoginMember loginMember = loginRepository.findByMemberIdAndPassword(params.get("ide"), params.get("password"));

        if (loginMember != null) {
            Long id = loginMember.getSeq();
            String empName = employeeService.findByLoginMemberId(id).getEmpName();
            String token = jwtService.getToken("id", id);
            Map<Long, String> answerMap = new HashMap<>();
            answerMap.put(id,empName);

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);
            return new ResponseEntity<>(answerMap, HttpStatus.OK);

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/account/check")
    public ResponseEntity check(@CookieValue(value = "token", required = false) String token) {
        Claims claims = jwtService.getClaims(token);

        if (claims != null) {
            int id = Integer.parseInt(claims.get("id").toString());
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    @PostMapping("/api/account/logout")
    public ResponseEntity logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token",null);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
