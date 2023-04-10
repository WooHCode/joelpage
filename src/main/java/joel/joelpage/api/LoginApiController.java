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
@CrossOrigin(origins = {"http://joeladmin.store", "http://ec2-52-79-168-230.ap-northeast-2.compute.amazonaws.com:8080"})
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
            String memberCode = String.valueOf(employeeService.findByLoginMemberId(id).getLoginMember().getMemberCode());
            String token = jwtService.getToken("id", id);
            Map<Integer, Object> answerMap = new HashMap<>();
            answerMap.put(1,empName);
            answerMap.put(2, id);

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            Cookie memberCodeCookie = new Cookie("memberCode", memberCode);
            memberCodeCookie.setMaxAge(60*60*24);
            memberCodeCookie.setPath("/");
            memberCodeCookie.setHttpOnly(false);
            answerMap.put(3, memberCode);

            response.addCookie(memberCodeCookie);
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

        Cookie memberCodeCookie = new Cookie("memberCode", null);
        memberCodeCookie.setMaxAge(0);
        memberCodeCookie.setPath("/");

        response.addCookie(cookie);
        response.addCookie(memberCodeCookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
