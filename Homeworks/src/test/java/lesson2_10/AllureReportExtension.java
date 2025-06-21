package lesson2_10;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;
import java.util.Optional;

public class AllureReportExtension implements TestWatcher {
    private WebDriver driver;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        if (driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(
                        "Скриншот при падении",
                        "image/png",
                        new ByteArrayInputStream(screenshot),
                        "png"
                );
                Allure.addAttachment("Ошибка", "text/plain", cause.getMessage());
            } catch (Exception e) {
                Allure.step("Ошибка при создании скриншота: " + e.getMessage());
            }
        }
        Allure.step("Тест упал: " + context.getDisplayName());
    }

//    @Override
//    public void testDisabled(ExtensionContext context, Optional<String> reason) {
//        TestWatcher.super.testDisabled(context, reason);
//    }
//
//    @Override
//    public void testSuccessful(ExtensionContext context) {
//        TestWatcher.super.testSuccessful(context);
//    }
//
//    @Override
//    public void testAborted(ExtensionContext context, Throwable cause) {
//        TestWatcher.super.testAborted(context, cause);
//    }
}
