package istyazhkina.helpers;

import io.qameta.allure.Attachment;
import istyazhkina.tests.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AttachmentsHelper {

    @Attachment(value = "Browser Logs", type = "text/plain")
    public static String attachBrowserLogs(String message) {
        return message;
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] attachPageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String attachVideo() {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getVideoUrl()
                + "' type='video/mp4'></video></body></html>";
    }

    public static String getVideoUrl() {
        return "https://" + BaseTest.remoteUrlPart + "/video/" + ((RemoteWebDriver) getWebDriver()).getSessionId() + ".mp4";
    }
}
