package istyazhkina.tests;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

class YandexTranslateTest extends BaseTest {

    @CsvSource({
            "столица, capital",
            "рассказ, story",
            "счет, account" //fails
    })
    @ParameterizedTest(name = "Проверка корректности перевода с русского на английский язык: {0} -> {1}")
    void checkRussianToEnglishTranslation(String russianValue, String englishValue) {

        step("Открываем страницу Yandex Translate", () -> open("https://translate.yandex.com/"));

        step("Выбираем языки перевода", () -> {
            $("#srcLangButton").click();
            $("#srcLangListbox").$$(".listbox-option").find(text("Russian")).click();
            $("#dstLangButton").click();
            $("#dstLangListbox").$$(".listbox-option").find(text("English")).click();
        });

        step("Указываем слово, которое нужно перевести", () -> $("#fakeArea").sendKeys(Keys.chord(russianValue)));

        step("Проверяем перевод", () -> {
            $(".translation-container").shouldHave(text(englishValue));
        });
    }
}
