package lesson2_10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class MtsOnlineTest {
    private static WebDriver driver;
    private static MainPage mainPage;
    private static PaymentPage paymentPage;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");

        mainPage = new MainPage(driver);
        paymentPage = new PaymentPage(driver);

        mainPage.acceptCookies();
    }

    @Test
    public void testBlockTitle() {
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        String actualTitle = paymentPage.getBlockTitleText();

        System.out.println("Найден заголовок: " + actualTitle);
        assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому");
    }

    @Test
    public void checkPaymentIcons() {
        var icons = paymentPage.getPaymentIcons();

        System.out.println("Найдено иконок платежных систем: " + icons.size());
        for (var icon : icons) {
            System.out.println(icon.getAttribute("alt"));
            assertTrue(icon.isDisplayed(), "Иконка не отображается");
        }
    }

    @Test
    public void checkLink() {
        String href = paymentPage.getDetailsLinkHref();
        System.out.println("Href: " + href);

        assertTrue(href.contains("mts.by"), "Ссылка ведет на внешний ресурс");

        paymentPage.clickDetailsLink();
        assertTrue(driver.getTitle().contains("Порядок оплаты и безопасность интернет платежей"),
                "Не та страница");

        System.out.println("Открыта нужная страница. Title: " + driver.getTitle());
    }

    @Test
    public void testPaymentForm() {
        paymentPage.fillPaymentForm("297777777", "1", "RibkaKus@gmail.com");
        assertTrue(paymentPage.isContinueButtonEnabled(),"Кнопка должна быть активна после заполнения формы");
        paymentPage.clickContinueButton();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
