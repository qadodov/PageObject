package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyAndCheckBalanceChanges() {
        int amountToAdd = 1000;

        open("http://localhost:9999");
        Configuration.headless = true;
        Configuration.browserSize = "1280x720";

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        int preTransactionBalance = dashboardPage.getCardBalance(dashboardPage.getFirstCardID());
        int preTransactionBalance2 = dashboardPage.getCardBalance(dashboardPage.getSecondCardID());
        dashboardPage.depositMoneyToFirstCard(amountToAdd);

        int postTransactionBalance = preTransactionBalance + amountToAdd;
        dashboardPage.getCards().first().should(Condition.ownText(String.valueOf(postTransactionBalance)));

        int postTransactionBalance2 = preTransactionBalance2 - amountToAdd;
        dashboardPage.getCards().last().should(Condition.ownText(String.valueOf(postTransactionBalance2)));
    }
}