package lesson2_9;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        try {
            By cookieAcceptButton = By.xpath("//div[@class='cookie__buttons']/button[@id='cookie-agree'][contains(text(), 'Принять')]");
            wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
        } catch (Exception e) {
            System.out.println("Куки не предлагались");
        }
    }

    @Test
    public void testBlockTitle() {
        By blockTitleLocator = By.xpath("//div[@class='pay__wrapper']/h2[contains(., 'Онлайн пополнение без комиссии')]");
        WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitleLocator));

        System.out.println("Найден заголовок: " + blockTitle.getText());
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        String actualTitle = blockTitle.getText();
        assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому");
    }

    @Test
    public void checkPaymentIcons() {
        By iconsLocator = By.xpath("//div[@class='pay__partners']/ul//img");
        List<WebElement> icons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(iconsLocator));

        System.out.println("Найдено иконок платежных систем: " + icons.size());
        for (WebElement icon : icons) {
            System.out.println(icon.getAttribute("alt"));
            assertTrue(icon.isDisplayed(), "Иконка не отображается");
        }
    }

    @Test
    public void checkLink(){
        By linkInfLocator = By.xpath("//div[@class='pay__wrapper']//a[contains(., 'Подробнее о сервисе')]");
        WebElement linkInf = wait.until(ExpectedConditions.elementToBeClickable(linkInfLocator));

        String href = linkInf.getAttribute("href");
        System.out.println("Href: " + href);

        assertTrue(href.contains("mts.by"), "Ссылка ведет на внешний ресурс");
        linkInf.click();

        try {
            By cookieAcceptButton = By.xpath("//div[@class='cookie__buttons']/button[@id='cookie-agree'][contains(text(), 'Принять')]");
            wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
        } catch (Exception e) {
            System.out.println("Окно куки не найдено");
        }

        assertTrue(driver.getTitle().contains("Порядок оплаты и безопасность интернет платежей"), "Не та страница");
        System.out.println("Открыта нужная страница. Title: " + driver.getTitle());
    }

    @Test
    public void testPaymentForm() {
            By phoneInputLocator = By.xpath("//input[@id='connection-phone']");
            WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInputLocator));
            phoneInput.sendKeys("297777777");

            By cashSumLocator = By.xpath("//input[@id='connection-sum']");
            WebElement cashSum = wait.until(ExpectedConditions.visibilityOfElementLocated(cashSumLocator));
            cashSum.sendKeys("1");

            By eMailLocator = By.xpath("//input[@id='connection-email']");
            WebElement eMail = wait.until(ExpectedConditions.visibilityOfElementLocated(eMailLocator));
            eMail.sendKeys("RibkaKus@gmail.com");

            By continueButtonLocator = By.xpath("//div[@class='pay__wrapper']//button[contains(., 'Продолжить')]");
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(continueButtonLocator));

            assertTrue(continueButton.isEnabled(), "Кнопка 'Продолжить' неактивна");
            continueButton.click();

        try {
            By iframeLocator = By.xpath("//iframe[@class='bepaid-iframe']");
            WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(iframeLocator));
            driver.switchTo().frame(iframe);

            By popupLocator = By.xpath("//div[@class='app-wrapper__content']");
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(popupLocator));

            assertTrue(popup.isDisplayed(), "Попап не отображается во фрейме");
            System.out.println("Попап успешно найден внутри bepaid-iframe");
        } catch (TimeoutException e) {
            System.err.println("Не удалось найти iframe или попап");
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
