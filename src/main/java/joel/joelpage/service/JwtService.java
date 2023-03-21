package joel.joelpage.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface JwtService {
    String getToken(String key, Object value) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
