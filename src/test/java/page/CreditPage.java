package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import java.time.Duration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CreditPage {

    /*поля и кнопки*/
    private SelenideElement heading = $x("//h3[text()='Кредит по данным карты']");
    private SelenideElement cardNumberField = $x("//span[text()='Номер карты']/..//input");
    private SelenideElement monthField = $x("//span[text()='Месяц']/..//input");
    private SelenideElement yearField = $x("//span[text()='Год']/..//input");
    private SelenideElement holderField = $x("//span[text()='Владелец']/..//input");
    private SelenideElement cvvField = $x("//span[contains(text(),'CVV')]/..//input");
    private SelenideElement continueButton = $x("//span[text()='Продолжить']");

    /*всплывающие сообщения*/
    private SelenideElement successNotification = $x("//div[contains(@class,'notification_status_ok')]");

    private SelenideElement errorNotification = $x("//div[contains(@class, 'notification_status_error')]");

    private SelenideElement invalidFormat = $x("//span[contains(text(), 'Неверный формат')]");

    private SelenideElement requiredField = $x("//span[contains(text(), 'Поле обязательно')]");

    private SelenideElement incorrectDeadline = $x("//span[contains(text(), 'Неверно указан срок')]");

    private SelenideElement deadlineIsOver = $x("//span[contains(text(), 'Истёк срок')]");

    public void successNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text("Операция оформлена банком"));
    }

    public void errorNotification() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text("Ошибка! Банк отказал в проведении операции."));
    }

    public void requiredField() {
        requiredField.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void invalidFormat() {
        invalidFormat.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void deadlineIsOver() {
        deadlineIsOver.shouldBe(visible).shouldHave(text("Истёк срок действия карты"));
    }

    public void incorrectDeadline() {
        incorrectDeadline.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }


    public void setRequiredFieldForCVVField(String text) {
        cvvField.shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void setRequiredFieldForNumberCard(String text) {
        cardNumberField.shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void setRequiredFieldForMonthField(String text) {
        monthField.shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void setRequiredFieldForYearField(String text) {
        yearField.shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void setRequiredFieldForHolderField(String text) {
        holderField.shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public CreditPage() {
        heading.shouldBe(visible);
    }

    public void fillingOutForm(DataHelper.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getNumberCard());
        monthField.setValue(cardInfo.getValidMonth());
        yearField.setValue(cardInfo.getValidYear());
        holderField.setValue(cardInfo.getValidHolder());
        cvvField.setValue(cardInfo.getValidCVV());
        continueButton.click();
    }
}
