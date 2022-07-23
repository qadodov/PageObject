package ru.netology.data;

public class DataHelper {
    private DataHelper() {}

    public static final class AuthInfo {
        private final String login;
        private final String password;

        public AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return this.login;
        }

        public String getPassword() {
            return this.password;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof AuthInfo)) return false;
            final AuthInfo other = (AuthInfo) o;
            final Object this$login = this.getLogin();
            final Object other$login = other.getLogin();
            if (this$login == null ? other$login != null : !this$login.equals(other$login)) return false;
            final Object this$password = this.getPassword();
            final Object other$password = other.getPassword();
            if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
            return true;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $login = this.getLogin();
            result = result * PRIME + ($login == null ? 43 : $login.hashCode());
            final Object $password = this.getPassword();
            result = result * PRIME + ($password == null ? 43 : $password.hashCode());
            return result;
        }

        public String toString() {
            return "DataHelper.AuthInfo(login=" + this.getLogin() + ", password=" + this.getPassword() + ")";
        }
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    public static final class VerificationCode {
        private final String code;

        public VerificationCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof VerificationCode)) return false;
            final VerificationCode other = (VerificationCode) o;
            final Object this$code = this.getCode();
            final Object other$code = other.getCode();
            if (this$code == null ? other$code != null : !this$code.equals(other$code)) return false;
            return true;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $code = this.getCode();
            result = result * PRIME + ($code == null ? 43 : $code.hashCode());
            return result;
        }

        public String toString() {
            return "DataHelper.VerificationCode(code=" + this.getCode() + ")";
        }
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }
}
