package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;
import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement cardFirst = $("[data-test-id=92df3f1c-a033-48e6-8390-206f6b1f56c0]");
    private SelenideElement cardSecond = $("[data-test-id=0f3f5c2a-249e-4c3d-8287-09f7a039391d]");
    private SelenideElement depositButton = $("[data-test-id=action-deposit]");
    private SelenideElement reload = $("[data-test-id=action-reload]");
    private SelenideElement cancelButton = $x("//*[contains(text(),'Отмена')]");

    private static final ElementsCollection cards = $$(".list__item div");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(String id) {
        String cardBalance = cards.findBy(text(DataHelper.getCardInfo(id).getNumber().substring(16))).getText();
        return extractBalance(cardBalance);
    }

    private int extractBalance(String cardBalance) {
        val start = cardBalance.indexOf(balanceStart);
        val finish = cardBalance.indexOf(balanceFinish);
        val value = cardBalance.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void getMoneyTransferFromSecondToFirst() {
        depositButton.first().click();
    }

    public void getMoneyTransferFromFirstToSecond() {
        depositButton.last().click();
    }

    public void reloadBalance() {
        reload.click();
    }

    public void cancelMoneyTransfer() {
        cancelButton.click();
    }

}
