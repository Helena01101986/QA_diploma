package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static Faker fakerEn = new Faker(new Locale("en"));
    private static Faker fakerRu = new Faker(new Locale("ru"));


    /*заполнение формы данными подтвержденной карты и валидными данными*/
    public static CardInfo getNumberApprovedCard() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getValidYear(), getValidHolder(), getValidCVV());
    }

    /*заполнение формы данными отмененной карты и валидными данными*/
    public static CardInfo getNumberDeclinedCard() {
        return new CardInfo("4444 4444 4444 4442", getValidMonth(), getValidYear(), getValidHolder(), getValidCVV());
    }

    /*пустое поле "Номер карты"*/
    public static CardInfo getEmptyCardField() {
        return new CardInfo("", getValidMonth(), getValidYear(), getValidHolder(), getValidCVV());
    }

    /*менее 16 символов в поле "Номер карты"*/
    public static CardInfo getNumberless16digits() {
        return new CardInfo("4444 4444 4444", getValidMonth(), getValidYear(), getValidHolder(), getValidCVV());
    }

    /*случайный номер карты в поле*/
    public static CardInfo getRandomNumber() {
        return new CardInfo(fakerEn.business().creditCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCVV());
    }

    /*"0000 0000 0000 0000" в поле ""Номер карты"*/
    public static CardInfo getZeroNumber() {
        return new CardInfo("0000 0000 0000 0000", getValidMonth(), getValidYear(), getValidHolder(), getValidCVV());
    }

    /*Пустое поле "Месяц"*/
    public static CardInfo getEmptyMonthField() {
        return new CardInfo("4444 4444 4444 4441", "", getValidYear(), getValidHolder(), getValidCVV());
    }

    /*Месяц ранее текущего*/
    public static CardInfo getEarlierMonth() {
        return new CardInfo("4444 4444 4444 4441", getEarlierCurrentMonth(), getValidYear(), getValidHolder(), getValidCVV());
    }

    /*Невалидный месяц "00"*/
    public static CardInfo get00Month() {
        return new CardInfo("4444 4444 4444 4441", "00", getValidYear(), getValidHolder(), getValidCVV());
    }

    /*Невалидный месяц "13"*/
    public static CardInfo get13Month() {
        return new CardInfo("4444 4444 4444 4441", "13", getValidYear(), getValidHolder(), getValidCVV());
    }

    /*Пустое поле "Год"*/
    public static CardInfo getEmptyYearField() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), "", getValidHolder(), getValidCVV());
    }

    /*Один символ в поле "Год"*/
    public static CardInfo getOneSimbolInYearField() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), "2", getValidHolder(), getValidCVV());
    }

    /*Год ранее текущего*/
    public static CardInfo getEarlierYear() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getEarlierCurrentYear(), getValidHolder(), getValidCVV());
    }

    /*Год, превышающий срок карты (+6)*/
    public static CardInfo getOverYear() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getOver6Years(), getValidHolder(), getValidCVV());
    }

    /*Пустое поле "Владелец"*/
    public static CardInfo getEmptyHolderField() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getValidYear(), "", getValidCVV());
    }

    /*Один символ в поле "Владелец"*/
    public static CardInfo getOneLetter() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getValidYear(), "U", getValidCVV());
    }

    /*Кириллица в поле "Владелец"*/
    public static CardInfo getRusHolder() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getValidYear(), getRusName(), getValidCVV());
    }

    /*Цифры и символы в поле "Владелец"*/
    public static CardInfo getNumberHolder() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getValidYear(), "1+5", getValidCVV());
    }

    /*Пустое поле ""CVC/CVV*/
    public static CardInfo getEmptyCVVField() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getValidYear(), getValidHolder(), "");
    }

    /*"000" в поле "CVC/CVV"*/
    public static CardInfo getZeroCVV() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getValidYear(), getValidHolder(), "000");
    }

    /*Один символ в поле "CVC/CVV"*/
    public static CardInfo getOneSimbolCVV() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getValidYear(), getValidHolder(), "1");
    }

    /*Буквы и спецсимволы в поле "CVC/CVV"*/
    public static CardInfo getLettersCVV() {
        return new CardInfo("4444 4444 4444 4441", getValidMonth(), getValidYear(), getValidHolder(), "п=х");
    }

    /*пустая форма*/
    public static CardInfo getEmptyForm() {
        return new CardInfo("", "", "", "", "");
    }

    public static String getValidMonth() {
        String validMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        return validMonth;
    }

    public static String getEarlierCurrentMonth() {
        String earlierBeforeCurrentMonth = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
        return earlierBeforeCurrentMonth;
    }

    public static String getValidYear() {
        String validYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        return validYear;
    }

    public static String getEarlierCurrentYear() {
        String earlierBeforeCurrentYear = LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        return earlierBeforeCurrentYear;
    }

    public static String getOver6Years() {
        String Over6Years = LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
        return Over6Years;
    }

    public static String getValidHolder() {
        String validHolder = fakerEn.name().firstName();
        return validHolder;
    }

    public static String getRusName() {
        String RusName = fakerRu.name().firstName();
        return RusName;
    }

    public static String getValidCVV() {
        String validCVV = fakerEn.number().digits(3);
        return validCVV;
    }

    @Value
    public static class CardInfo {
        private String numberCard;
        private String validMonth;
        private String validYear;
        private String validHolder;
        private String validCVV;
    }
}
