package istyazhkina.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.getWebDriverLogs;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static istyazhkina.helpers.AttachmentsHelper.attachBrowserLogs;
import static istyazhkina.helpers.AttachmentsHelper.attachPageSource;
import static istyazhkina.helpers.AttachmentsHelper.attachScreenshot;
import static istyazhkina.helpers.AttachmentsHelper.attachVideo;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class BaseTest {

    public static String remoteUrlPart = System.getProperty("remote.browser.url", "selenoid.autotests.cloud");
    private static String credentials = System.getProperty("remote.credentials", "");


    @BeforeAll
    static void setUp() {
        addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));

        Configuration.timeout = 60000;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://" + credentials + "@" + remoteUrlPart + ":4444/wd/hub/";
    }

    @AfterEach
    @Step("Add attachments & close WebDriver")
    void attachFiles() {
        attachBrowserLogs(String.join("\n", getWebDriverLogs(BROWSER)));
        attachScreenshot();
        attachPageSource();
        attachVideo();

        closeWebDriver();
    }

}
