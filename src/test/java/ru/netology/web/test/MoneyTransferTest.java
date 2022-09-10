package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import lombok.var;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.MoneyTransferPage;

import static com.codeborne.selenide.Selenide.*;

public class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    void clear() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    void ShouldTransferMoneyFromFirstCardToSecondCard() {
        DashboardPage dashboardPage = new DashboardPage();

        var startBalanceCardFirst = dashboardPage.getCardBalance("1");
        var startBalanceCardSecond = dashboardPage.getCardBalance("2");
        var moneyTransferPage = new MoneyTransferPage();
        var amount = moneyTransferPage.getAmount();



        int expectedCardFirst = dashboardPage.getCardBalance("1") + 1000;
        int expectedCardSecond = dashboardPage.getCardBalance("2") - 1000;

        dashboardPage.getMoneyTransferFromSecondToFirst();
        var moneyTransferPage = new MoneyTransferTest();
        moneyTransferPage.
                moneyTransfer(DataHelper.getCardInfo("2"), "1000");





        var amount = moneyTransferPage.getAmount();
        moneyTransferPage.transferFromFirstToSecond(amount);
        var actualBalance1 = dashboardPage.getCardBalance(0);
        var actualBalance2 = dashboardPage.getCardBalance(1);
        Assertions.assertEquals(startingBalance1 + amount, actualBalance1);
        Assertions.assertEquals(startingBalance2, actualBalance2 + amount);
    }
}
