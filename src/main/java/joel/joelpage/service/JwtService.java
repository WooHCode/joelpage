package joel.joelpage.service;

public interface JwtService {
    String getToken(String key, Object value);

    String getClaims(String token);

    boolean isValid(String token);

    int getId(String token);
}
