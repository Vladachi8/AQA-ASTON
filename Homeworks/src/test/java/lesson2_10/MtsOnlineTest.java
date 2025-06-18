package lesson2_10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;

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
        assertTrue(paymentPage.isPaymentPopupDisplayed(),
                "Попап оплаты должен отображаться после нажатия кнопки 'Продолжить'");
    }

    @Test
    public void testCommunicationServicesPlaceholders() {
        paymentPage.selectService("Услуги связи");
        Map<String, String> placeholders = paymentPage.getFieldPlaceholders();

        assertAll(
                () -> assertEquals("Номер телефона", placeholders.get("Телефон"),
                        "Неверный плейсхолдер для телефона"),
                () -> assertEquals("Сумма", placeholders.get("Сумма"),
                        "Неверный плейсхолдер для суммы"),
                () -> assertEquals("E-mail для отправки чека", placeholders.get("Email"),
                        "Неверный плейсхолдер для email")
        );
    }

    @Test
    public void testHomeInternetPlaceholders() {
        paymentPage.selectService("Домашний интернет");
        Map<String, String> placeholders = paymentPage.getFieldPlaceholders();

        assertAll(
                () -> assertEquals("Номер абонента", placeholders.get("Номер абонента")),
                () -> assertEquals("Сумма", placeholders.get("Сумма домашнего интернета")),
                () -> assertEquals("E-mail для отправки чека", placeholders.get("E-mail домашнего интернета"))
        );
    }

    @Test
    public void testInstallmentPlaceholders() {
        paymentPage.selectService("Рассрочка");
        Map<String, String> placeholders = paymentPage.getFieldPlaceholders();

        assertAll(
                () -> assertEquals("Номер счета на 44", placeholders.get("Номер счета на 44")),
                () -> assertEquals("Сумма", placeholders.get("Сумма рассрочки")),
                () -> assertEquals("E-mail для отправки чека", placeholders.get("E-mail рассрочки"))
        );
    }

    @Test
    public void testDebtPlaceholders() {
        paymentPage.selectService("Задолженность");
        Map<String, String> placeholders = paymentPage.getFieldPlaceholders();

        assertAll(
                () -> assertEquals("Номер счета на 2073", placeholders.get("Номер счета на 2073")),
                () -> assertEquals("Сумма", placeholders.get("Сумма задолженности")),
                () -> assertEquals("E-mail для отправки чека", placeholders.get("E-mail задолженности"))
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
