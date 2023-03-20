package joel.joelpage.service;

import java.util.Date;

public class JwtServiceImpl implements JwtService{

    private String secretKey= "adshjfsajdl12321332er4!@3!@#!@$dfjksaldjf!@#!@$!@#!@sdlkafjlds@!#!@#";
    @Override
    public String getToken(String key, Object value) {

        Date expTime = new Date();
        expTime.setTime(expTime.getTime() * 1000 * 60 * 5);

        return null;
    }
}
