package page;


import data.DataHelper;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DebitPage {
    public void fillingOutForm(DataHelper.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getNumberCard());
        monthField.setValue(cardInfo.getValidMonth());
        yearField.setValue(cardInfo.getValidYear());
        holderField.setValue(cardInfo.getValidHolder());
        cvvField.setValue(cardInfo.getValidCVV());
        continueButton.click();
    }
    public DebitPage() {
        heading.shouldBe(visible);
    }

    /*поля и кнопки*/
    private SelenideElement heading = $x("//h3[text()='Оплата по карте']");
    private static SelenideElement cardNumberField = $x("//span[text()='Номер карты']/..//input");
    private static SelenideElement monthField = $x("//span[text()='Месяц']/..//input");
    private static SelenideElement yearField = $x("//span[text()='Год']/..//input");
    private static SelenideElement holderField = $x("//span[text()='Владелец']/..//input");
    private static SelenideElement cvvField = $x("//span[contains(text(),'CVV')]/..//input");
    private static SelenideElement continueButton = $x("//span[text()='Продолжить']");

    /*всплывающие сообщения*/
    private SelenideElement successNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $x("//div[contains(@class, 'notification_status_error')]");
    private SelenideElement invalidFormat = $x("//span[contains(text(), 'Неверный формат')]");
    private SelenideElement requiredField = $x("//span[contains(text(), 'Поле обязательно')]");
    private SelenideElement incorrectDeadline = $x("//span[contains(text(), 'Неверно указан срок')]");
    private SelenideElement deadlineIsOver = $x("//span[contains(text(), 'Истёк срок')]");
    public void setSuccessNotificationVisible() {
        successNotification.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text("Операция одобрена Банком."));
    }
    public void errorNotification() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text("Ошибка! Банк отказал в проведении операции."));
    }
    public void invalidFormat() {
        invalidFormat.shouldBe(visible).shouldHave(text("Неверный формат"));
    }
    public void requiredField() {
        requiredField.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }
    public void incorrectDeadline() {
        incorrectDeadline.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }
    public void deadlineIsOver() {
        deadlineIsOver.shouldBe(visible).shouldHave(text("Истёк срок действия карты"));
    }


}
