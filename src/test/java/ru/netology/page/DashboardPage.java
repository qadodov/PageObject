package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {

    private final SelenideElement heading = $x("//*[@data-test-id=\"dashboard\"]");

    private ElementsCollection cards = $$(".list__item div");

    private final ElementsCollection transferButtons = $$(".list__item button");

    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    private String firstCardID = cards.first().getAttribute("data-test-id");
    private String secondCardID = cards.last().getAttribute("data-test-id");
    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    public int getCardBalance(String id) {
        String text = cards.findBy(Condition.attribute("data-test-id", id)).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage transferToFirstCardFromSecond() {
        transferButtons.first().click();
        return new TransferPage();
    }

    public TransferPage transferToSecondCardFromFirst() {
        transferButtons.last().click();
        return new TransferPage();
    }
}
