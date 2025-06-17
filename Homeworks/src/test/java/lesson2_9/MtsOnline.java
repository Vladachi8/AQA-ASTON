package lesson2_9;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MtsOnline {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
    }

    @Test
    public void testBlockTitle() {
        try {
            By cookieAcceptButton = By.xpath("//div[@class='cookie__buttons']/button[@id='cookie-agree'][contains(text(), 'Принять')]");
            wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
        } catch (Exception e) {
            System.out.println("Окно куки не найдено");
        }

        By blockTitleLocator = By.xpath("//div[@class='pay__wrapper']/h2[contains(., 'Онлайн пополнение без комиссии')]");
        WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitleLocator));

        System.out.println("Найден заголовок: " + blockTitle.getText());
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        String actualTitle = blockTitle.getText();
        assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
