package cn.edu.nwu.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CryptoUtil {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public String encrypt(String rawPass) {
        return passwordEncoder.encode(rawPass);
    }

    public boolean match(String rawPass,String EncryptedPass){
        return passwordEncoder.matches(rawPass, EncryptedPass);
    }
}
