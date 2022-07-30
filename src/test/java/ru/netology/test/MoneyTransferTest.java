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
        var firstCardInfo = DataHelper.getCardInfo();
        var secondCardInfo = DataHelper.getOtherCardInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        int preTransactionBalance = dashboardPage.getCardBalance(firstCardInfo.getTestID());
        int preTransactionBalance2 = dashboardPage.getCardBalance(secondCardInfo.getTestID());
        dashboardPage.transferToFirstCardFromSecond();
        var transferPage = new TransferPage();
        transferPage.transfer(String.valueOf(amountToTransfer), secondCardInfo.getCardNumber());

        int postTransactionBalance = dashboardPage.getCardBalance(firstCardInfo.getTestID());
        int postTransactionBalance2 = dashboardPage.getCardBalance(secondCardInfo.getTestID());


        assertEquals(preTransactionBalance + amountToTransfer, postTransactionBalance);
        assertEquals(preTransactionBalance2 - amountToTransfer, postTransactionBalance2);

    }

    @Test
    void shouldNotTransferMoneyIfAmountOfMoneyToTransferIsHigherThanCardBalance() {

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var firstCardInfo = DataHelper.getCardInfo();
        var secondCardInfo = DataHelper.getOtherCardInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        int preTransactionBalance = dashboardPage.getCardBalance(firstCardInfo.getTestID());
        int preTransactionBalance2 = dashboardPage.getCardBalance(secondCardInfo.getTestID());
        dashboardPage.transferToFirstCardFromSecond();
        var transferPage = new TransferPage();

        int amountToTransfer = preTransactionBalance2 + 1000;

        transferPage.transfer(String.valueOf(amountToTransfer), secondCardInfo.getCardNumber());

        transferPage.transferError();

        int postTransactionBalance = dashboardPage.getCardBalance(firstCardInfo.getTestID());
        int postTransactionBalance2 = dashboardPage.getCardBalance(secondCardInfo.getTestID());

        assertEquals(preTransactionBalance, postTransactionBalance);
        assertEquals(preTransactionBalance2, postTransactionBalance2);
    }
}