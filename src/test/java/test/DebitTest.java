package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.DebitPage;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitTest {
    private MainPage mainPage;
    private DebitPage debitPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUpTest() {
        open("http://localhost:8080/");
        Configuration.holdBrowserOpen = true;
        mainPage = new MainPage();
        debitPage = mainPage.goToDebitPage();
    }
    @AfterEach
    public void clean() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Buy by approved card")
    void shouldTestBuyWithApprovedCard() {
        debitPage.fillingOutForm(DataHelper.getNumberApprovedCard());
        debitPage.successNotification();
        assertEquals("APPROVED", SQLHelper.getStatusForPayment());
    }

    @Test
    @DisplayName("Покупка отклоненной картой")
    void shouldTestBuyWithDeclinedCard() {
        debitPage.fillingOutForm(DataHelper.getNumberDeclinedCard());
        debitPage.errorNotification();
        assertEquals("DECLINED", SQLHelper.getStatusForPayment());
    }

    @Test
    @DisplayName("Незаполненное поле 'Номер карты'")
    void shouldTestEmptyCardNumberField() {
        debitPage.fillingOutForm(DataHelper.getEmptyCardField());
        debitPage.requiredField();

    }

    @Test
    @DisplayName("В поле 'Номер карты' 16 символов")
    void shouldTestNumberLess16Digits(){
        debitPage.fillingOutForm(DataHelper.getNumberless16digits());
        debitPage.invalidFormat();
    }

    @Test
    @DisplayName("Покупка картой со случайным номером")
    void shouldTestRandomNumberCard() {
        debitPage.fillingOutForm(DataHelper.getRandomNumber());
        debitPage.errorNotification();
    }

    @Test
    @DisplayName("Покупка с номером карты 0000 0000 0000 0000")
    void shouldTestZeroNumberCard() {
        debitPage.fillingOutForm(DataHelper.getZeroNumber());
        debitPage.errorNotification();
    }
    
    @Test
    @DisplayName("Покупка с незаполненным полем 'Месяц'")
    void shouldTestEmptyMonthField() {
        debitPage.fillingOutForm(DataHelper.getEmptyMonthField());
        debitPage.requiredField();
    }

    @Test
    @DisplayName("Месяц ранее текущего в поле 'Месяц'")
    void shouldTestEarlierMonth() {
        debitPage.fillingOutForm(DataHelper.getEarlierMonth());
        debitPage.successNotification();
    }

    @Test
    @DisplayName("Невалидное значение '00' в поле 'Месяц'")
    void shouldTestZeroMonth() {
        debitPage.fillingOutForm(DataHelper.get00Month());
        debitPage.invalidFormat();
    }

    @Test
    @DisplayName("Невалидное значение '13' в поле 'Месяц'")
    void shouldTest13Month() {
        debitPage.fillingOutForm(DataHelper.get13Month());
        debitPage.invalidFormat();
    }

    @Test
    @DisplayName("Покупка с незаполненным полем 'Год'")
    void shouldTestEmptyYearField() {
        debitPage.fillingOutForm(DataHelper.getEmptyYearField());
        debitPage.requiredField();
    }

    @Test
    @DisplayName("Один символ в поле 'Год'")
    void shouldTestOneSimbolInYearField() {
        debitPage.fillingOutForm(DataHelper.getOneSimbolInYearField());
        debitPage.incorrectDeadline();
    }

    @Test
    @DisplayName("Год ранее текущего в поле 'Год'")
    void shouldTestEarlierYear() {
        debitPage.fillingOutForm(DataHelper.getEarlierYear());
        debitPage.deadlineIsOver();
    }

    @Test
    @DisplayName("Год, превышающий срок карты")
    void shouldTestOverYear() {
        debitPage.fillingOutForm(DataHelper.getOverYear());
        debitPage.successNotification();
    }

    @Test
    @DisplayName("Пустое поле 'Владелец'")
    void shouldTestEmptyHolderField() {
        debitPage.fillingOutForm(DataHelper.getEmptyHolderField());
        debitPage.requiredField();
    }

    @Test
    @DisplayName("Один символ в поле 'Владелец'")
    void shouldTestOneLetterInHolderField() {
        debitPage.fillingOutForm(DataHelper.getOneLetter());
        debitPage.invalidFormat();
    }

    @Test
    @DisplayName("Кириллица в поле 'Владелец'")
    void shouldTestRusHolderName() {
        debitPage.fillingOutForm(DataHelper.getRusHolder());
        debitPage.invalidFormat();
    }

    @Test
    @DisplayName("Цифры и символы в поле 'Владелец'")
    void shouldTestNumberInHolderField() {
        debitPage.fillingOutForm(DataHelper.getNumberHolder());
        debitPage.invalidFormat();
    }

    @Test
    @DisplayName("Пустое поле 'CVC/CVV'")
    void shouldTestEmptyCVVField() {
        debitPage.fillingOutForm(DataHelper.getEmptyCVVField());
        debitPage.requiredField();
    }

    @Test
    @DisplayName("Нулевое значение в поле 'CVC/CVV'")
    void shouldTestZeroInCVVField() {
        debitPage.fillingOutForm(DataHelper.getZeroCVV());
        debitPage.invalidFormat();
    }

    @Test
    @DisplayName("Один символ в поле 'CVC/CVV'")
    void shouldTestOneSimbolCVV() {
        debitPage.fillingOutForm(DataHelper.getOneSimbolCVV());
        debitPage.invalidFormat();
    }

    @Test
    @DisplayName("Буквы и спесимволы в поле 'CVC/CVV'")
    void shouldTestLettersInCVVField() {
        debitPage.fillingOutForm(DataHelper.getLettersCVV());
        debitPage.invalidFormat();
    }

    /*@Test
    @DisplayName("Незаполненная форма")
    void shouldTestEmptyForm() {
        debitPage.fillingOutForm(DataHelper.getEmptyForm());
        debitPage.requiredField();
    }*/
}
