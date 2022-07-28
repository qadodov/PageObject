package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TransferPage {

        private final String firstCardNumber = "5559 0000 0000 0001";
        private final String secondCardNumber = "5559 0000 0000 0002";

        private final SelenideElement amountField = $x("//*[@data-test-id=\"amount\"]//input");
        private final SelenideElement cardNumberField = $x("//*[@placeholder=\"0000 0000 0000 0000\"]");
        private final SelenideElement transferButton = $x("//*[@data-test-id=\"action-transfer\"]");

        private final SelenideElement transferHeading = $x("//h1");

        public TransferPage() {
                transferHeading.should(Condition.text("Пополнение карты")).shouldBe(Condition.visible);
        }

        public DashboardPage transfer(String amount, String card) {
                amountField.setValue(amount);
                cardNumberField.setValue(card);
                transferButton.click();
                return new DashboardPage();
        }
}
