package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        Configuration.headless = true;
        Configuration.browserSize = "1280x720";
        open("http://localhost:9999");
    }


    @Test
    void shouldTransferMoneyAndCheckBalanceChanges() {
        int amountToTransfer = 1000;

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        int preTransactionBalance = dashboardPage.getCardBalance(dashboardPage.getFirstCardID());
        int preTransactionBalance2 = dashboardPage.getCardBalance(dashboardPage.getSecondCardID());
        dashboardPage.transferToFirstCardFromSecond();
        var transferPage = new TransferPage();
        transferPage.transfer(String.valueOf(amountToTransfer), DataHelper.getCardInfo().getSecondCardNumber());

        int postTransactionBalance = dashboardPage.getCardBalance(dashboardPage.getFirstCardID());
        int postTransactionBalance2 = dashboardPage.getCardBalance(dashboardPage.getSecondCardID());


        assertEquals(preTransactionBalance + amountToTransfer, postTransactionBalance);
        assertEquals(preTransactionBalance2 - amountToTransfer, postTransactionBalance2);

    }

    @Test
    void shouldNotTransferMoneyIfAmountOfMoneyToTransferIsHigherThanCardBalance() {

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        int preTransactionBalance = dashboardPage.getCardBalance(dashboardPage.getFirstCardID());
        int preTransactionBalance2 = dashboardPage.getCardBalance(dashboardPage.getSecondCardID());
        dashboardPage.transferToFirstCardFromSecond();
        var transferPage = new TransferPage();

        int amountToTransfer = preTransactionBalance2 + 1;

        transferPage.transfer(String.valueOf(amountToTransfer), DataHelper.getCardInfo().getSecondCardNumber());

        int postTransactionBalance = dashboardPage.getCardBalance(dashboardPage.getFirstCardID());
        int postTransactionBalance2 = dashboardPage.getCardBalance(dashboardPage.getSecondCardID());

        assertEquals(-1, postTransactionBalance2);
    }
}