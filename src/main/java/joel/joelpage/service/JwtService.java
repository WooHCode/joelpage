package joel.joelpage.service;

public interface JwtService {
    String getToken(String key, Object value);
}
