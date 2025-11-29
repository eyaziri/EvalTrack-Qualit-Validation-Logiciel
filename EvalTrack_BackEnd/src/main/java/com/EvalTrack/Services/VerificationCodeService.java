package com.EvalTrack.Services;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VerificationCodeService {

    private final Map<String, String> verificationCodes = new ConcurrentHashMap<>();

    public void storeCode(String email, String code) {
        verificationCodes.put(email, code);
    }

    public boolean verifyCode(String email, String code) {
        return code.equals(verificationCodes.get(email));
    }

    public void removeCode(String email) {
        verificationCodes.remove(email);
    }
}
