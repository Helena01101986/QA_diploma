package Test;

import Data.DataHelper;
import Data.SQLHelper;
import Page.DebitPage;
import Page.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitTest {
    private MainPage mainPage;
    private DebitPage debitPage;

    @BeforeEach
    void setUpTest() {
        open("http://localhost:8080/");
        mainPage = new MainPage();
        debitPage = mainPage.goToDebitPage();
    }


    @Test
    @DisplayName("Покупка подтвержденной картой")
    void shouldTestBuyWithApprovedCard(){
        debitPage.FillingOutForm(DataHelper.getNumberApprovedCard());
        debitPage.successNotification();
        assertEquals("APPROVED", SQLHelper.getStatusForPayment());
    }

    @Test
    @DisplayName("Покупка отклоненной картой")
    void shouldTestBuyWithDeclinedCard(){
        debitPage.FillingOutForm(DataHelper.getNumberDeclinedCard());
        debitPage.errorNotification();
        assertEquals("DECLINED", SQLHelper.getStatusForPayment());
    }



}
