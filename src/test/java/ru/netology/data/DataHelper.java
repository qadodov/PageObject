package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}
    @Value
    public static class AuthInfo {
        private final String login;
        private final String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }
    @Value
    public static final class VerificationCode {
        private final String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }
}
