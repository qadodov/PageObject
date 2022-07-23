package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public final class DashboardPage {

    private final String firstCardNumber = "5559 0000 0000 0001";
    private final String secondCardNumber = "5559 0000 0000 0002";

    private final SelenideElement heading = $x("//*[@data-test-id=\"dashboard\"]");

    private final ElementsCollection cards = $$(".list__item div");

    private final ElementsCollection addMoneyButtons = $$(".list__item button");

    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    private final String firstCardID = cards.first().getAttribute("data-test-id");
    private final String secondCardID = cards.last().getAttribute("data-test-id");
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

    public DashboardPage depositMoneyToFirstCard(int amount) {
        addMoneyButtons.first().click();
        $x("//*[@data-test-id=\"amount\"]//input").setValue(String.valueOf(amount));
        $x("//*[@data-test-id=\"from\"]//input").setValue(secondCardNumber);
        $x("//*[@data-test-id=\"action-transfer\"]").click();
        return new DashboardPage();
    }

    public String getFirstCardNumber() {
        return this.firstCardNumber;
    }

    public String getSecondCardNumber() {
        return this.secondCardNumber;
    }

    public SelenideElement getHeading() {
        return this.heading;
    }

    public ElementsCollection getCards() {
        return this.cards;
    }

    public ElementsCollection getAddMoneyButtons() {
        return this.addMoneyButtons;
    }

    public String getBalanceStart() {
        return this.balanceStart;
    }

    public String getBalanceFinish() {
        return this.balanceFinish;
    }

    public String getFirstCardID() {
        return this.firstCardID;
    }

    public String getSecondCardID() {
        return this.secondCardID;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DashboardPage)) return false;
        final DashboardPage other = (DashboardPage) o;
        final Object this$firstCardNumber = this.getFirstCardNumber();
        final Object other$firstCardNumber = other.getFirstCardNumber();
        if (this$firstCardNumber == null ? other$firstCardNumber != null : !this$firstCardNumber.equals(other$firstCardNumber))
            return false;
        final Object this$secondCardNumber = this.getSecondCardNumber();
        final Object other$secondCardNumber = other.getSecondCardNumber();
        if (this$secondCardNumber == null ? other$secondCardNumber != null : !this$secondCardNumber.equals(other$secondCardNumber))
            return false;
        final Object this$heading = this.getHeading();
        final Object other$heading = other.getHeading();
        if (this$heading == null ? other$heading != null : !this$heading.equals(other$heading)) return false;
        final Object this$cards = this.getCards();
        final Object other$cards = other.getCards();
        if (this$cards == null ? other$cards != null : !this$cards.equals(other$cards)) return false;
        final Object this$addMoneyButtons = this.getAddMoneyButtons();
        final Object other$addMoneyButtons = other.getAddMoneyButtons();
        if (this$addMoneyButtons == null ? other$addMoneyButtons != null : !this$addMoneyButtons.equals(other$addMoneyButtons))
            return false;
        final Object this$balanceStart = this.getBalanceStart();
        final Object other$balanceStart = other.getBalanceStart();
        if (this$balanceStart == null ? other$balanceStart != null : !this$balanceStart.equals(other$balanceStart))
            return false;
        final Object this$balanceFinish = this.getBalanceFinish();
        final Object other$balanceFinish = other.getBalanceFinish();
        if (this$balanceFinish == null ? other$balanceFinish != null : !this$balanceFinish.equals(other$balanceFinish))
            return false;
        final Object this$firstCardID = this.getFirstCardID();
        final Object other$firstCardID = other.getFirstCardID();
        if (this$firstCardID == null ? other$firstCardID != null : !this$firstCardID.equals(other$firstCardID))
            return false;
        final Object this$secondCardID = this.getSecondCardID();
        final Object other$secondCardID = other.getSecondCardID();
        if (this$secondCardID == null ? other$secondCardID != null : !this$secondCardID.equals(other$secondCardID))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $firstCardNumber = this.getFirstCardNumber();
        result = result * PRIME + ($firstCardNumber == null ? 43 : $firstCardNumber.hashCode());
        final Object $secondCardNumber = this.getSecondCardNumber();
        result = result * PRIME + ($secondCardNumber == null ? 43 : $secondCardNumber.hashCode());
        final Object $heading = this.getHeading();
        result = result * PRIME + ($heading == null ? 43 : $heading.hashCode());
        final Object $cards = this.getCards();
        result = result * PRIME + ($cards == null ? 43 : $cards.hashCode());
        final Object $addMoneyButtons = this.getAddMoneyButtons();
        result = result * PRIME + ($addMoneyButtons == null ? 43 : $addMoneyButtons.hashCode());
        final Object $balanceStart = this.getBalanceStart();
        result = result * PRIME + ($balanceStart == null ? 43 : $balanceStart.hashCode());
        final Object $balanceFinish = this.getBalanceFinish();
        result = result * PRIME + ($balanceFinish == null ? 43 : $balanceFinish.hashCode());
        final Object $firstCardID = this.getFirstCardID();
        result = result * PRIME + ($firstCardID == null ? 43 : $firstCardID.hashCode());
        final Object $secondCardID = this.getSecondCardID();
        result = result * PRIME + ($secondCardID == null ? 43 : $secondCardID.hashCode());
        return result;
    }

    public String toString() {
        return "DashboardPage(firstCardNumber=" + this.getFirstCardNumber() + ", secondCardNumber=" + this.getSecondCardNumber() + ", heading=" + this.getHeading() + ", cards=" + this.getCards() + ", addMoneyButtons=" + this.getAddMoneyButtons() + ", balanceStart=" + this.getBalanceStart() + ", balanceFinish=" + this.getBalanceFinish() + ", firstCardID=" + this.getFirstCardID() + ", secondCardID=" + this.getSecondCardID() + ")";
    }
}
