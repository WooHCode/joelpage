package joel.joelpage.service;

import io.jsonwebtoken.*;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService{

    private String publicKey = getPublicKey();
    private String privateKey = getPrivateKey();

    public JwtServiceImpl() throws NoSuchAlgorithmException {
    }

    @Override
    public String getToken(String key, Object value) throws NoSuchAlgorithmException, InvalidKeySpecException {

        Date expTime = new Date();
        expTime.setTime(expTime.getTime() * 1000 * 60 * 30); //현재시간에서 30분

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        byte[] publicBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicBytes);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        byte[] privateBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ","JWT");
        headerMap.put("alg", "RS256");

        Map<String, Object> map = new HashMap<>();
        map.put(key, value);

        JwtBuilder builder = Jwts.builder()
                .setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expTime)
                .signWith(privateKey, SignatureAlgorithm.RS256);

        return builder.compact();
    }

    @Override
    public Claims getClaims(String token) {
        if (token != null || "".equals(token)) {
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                byte[] privateBytes = Base64.getDecoder().decode(privateKey);
                PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateBytes);
                PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
                return Jwts.parserBuilder().setSigningKey(privateKey).build().parseClaimsJws(token).getBody();
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException(e);
            } catch (ExpiredJwtException e) {

            } catch (JwtException e) {

            }
        }
        return null;
    }

    public String getPublicKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
    }
    public String getPrivateKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        return Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
    }
}
