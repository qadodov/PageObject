package ru.netology.page;

import ru.netology.data.DataHelper;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        $x("//input[@name=\"login\"]").setValue(info.getLogin());
        $x("//input[@name=\"password\"]").setValue(info.getPassword());
        $x("//*[@data-test-id=\"action-login\"]").click();
        return new VerificationPage();
    }

}
