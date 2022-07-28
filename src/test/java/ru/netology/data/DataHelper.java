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
    @Value
    public static class CardInfo {
        private final String firstCardNumber;
        private final String secondCardNumber;
    }

    public static CardInfo getCardInfo() {
        return new CardInfo("5559 0000 0000 0001", "5559 0000 0000 0002");
    }
}
