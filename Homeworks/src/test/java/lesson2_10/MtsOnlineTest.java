package lesson2_10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class MtsOnlineTest {
    private static WebDriver driver;
    private static MainPage mainPage;
    private static PaymentBlock paymentBlock;
    private static BepaidIframe bepaidIframe;
    private Actions actions;

    private final String PHONE = "297777777";
    private final String AMOUNT = "1.00";
    private final String EMAIL = "RibkaKus@gmail.com";

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver, actions);
        paymentBlock = new PaymentBlock(driver, actions);
        bepaidIframe = new BepaidIframe(driver, actions);

        actions.pause(1000)
                .build()
                .perform();
        driver.get("https://www.mts.by/");

        mainPage.acceptCookies();
    }

    @Test
    public void testBlockTitle() {
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        String actualTitle = paymentBlock.getBlockTitleText();

        System.out.println("Найден заголовок: " + actualTitle);
        assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому");
    }

    @Test
    public void checkPaymentIcons() {
        var icons = paymentBlock.getPaymentIcons();

        System.out.println("Найдено иконок платежных систем: " + icons.size());
        for (var icon : icons) {
            System.out.println(icon.getAttribute("alt"));
            assertTrue(icon.isDisplayed(), "Иконка не отображается");
        }
    }

    @Test
    public void checkLink() {
        String href = paymentBlock.getDetailsLinkHref();
        System.out.println("Href: " + href);
        assertTrue(href.contains("mts.by"), "Ссылка ведет на внешний ресурс");

        paymentBlock.clickDetailsLink();
        assertTrue(driver.getTitle().contains("Порядок оплаты и безопасность интернет платежей"),
                "Не та страница");
        System.out.println("Открыта нужная страница. Title: " + driver.getTitle());
    }

    @Test
    public void testPaymentForm() {
        paymentBlock.fillPaymentForm(PHONE, AMOUNT, EMAIL);
        assertTrue(paymentBlock.isContinueButtonEnabled(), "Кнопка должна быть активна после заполнения формы");
        paymentBlock.clickContinueButton();
        assertTrue(paymentBlock.isPaymentPopupDisplayed(),
                "Попап оплаты должен отображаться после нажатия кнопки 'Продолжить'");
    }

    @Test
    public void testCommunicationServicesPlaceholders() {
        paymentBlock.selectService("Услуги связи");
        Map<String, String> placeholders = paymentBlock.getFieldPlaceholders();

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
        paymentBlock.selectService("Домашний интернет");
        Map<String, String> placeholders = paymentBlock.getFieldPlaceholders();

        assertAll(
                () -> assertEquals("Номер абонента", placeholders.get("Номер абонента")),
                () -> assertEquals("Сумма", placeholders.get("Сумма домашнего интернета")),
                () -> assertEquals("E-mail для отправки чека", placeholders.get("E-mail домашнего интернета"))
        );
    }

    @Test
    public void testInstallmentPlaceholders() {
        paymentBlock.selectService("Рассрочка");
        Map<String, String> placeholders = paymentBlock.getFieldPlaceholders();

        assertAll(
                () -> assertEquals("Номер счета на 44", placeholders.get("Номер счета на 44")),
                () -> assertEquals("Сумма", placeholders.get("Сумма рассрочки")),
                () -> assertEquals("E-mail для отправки чека", placeholders.get("E-mail рассрочки"))
        );
    }

    @Test
    public void testDebtPlaceholders() {
        paymentBlock.selectService("Задолженность");
        Map<String, String> placeholders = paymentBlock.getFieldPlaceholders();

        assertAll(
                () -> assertEquals("Номер счета на 2073", placeholders.get("Номер счета на 2073")),
                () -> assertEquals("Сумма", placeholders.get("Сумма задолженности")),
                () -> assertEquals("E-mail для отправки чека", placeholders.get("E-mail задолженности"))
        );
    }

    @Test
    public void testPaymentPriceTitle() {
        paymentBlock.fillPaymentForm(PHONE, AMOUNT, EMAIL);
        paymentBlock.clickContinueButton();

        String expectedTitle = AMOUNT + " BYN";
        String actualTitle = bepaidIframe.getPaymentAmount();

        assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому");
    }

    @Test
    public void testPaymentPriceButton() {
        paymentBlock.fillPaymentForm(PHONE, AMOUNT, EMAIL);
        paymentBlock.clickContinueButton();

        String expectedTitle = "Оплатить " + AMOUNT + " BYN";
        String actualTitle = bepaidIframe.getButtonAmount();

        assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому");
    }

    @Test
    public void testUserNumberSpan() {
        paymentBlock.fillPaymentForm(PHONE, AMOUNT, EMAIL);
        paymentBlock.clickContinueButton();

        String expectedTitle = "375" + PHONE;
        String actualTitle = bepaidIframe.getUserNumberSpan();

        assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому");
    }

    @Test
    public void testBepaidPlaceholders() {
        paymentBlock.fillPaymentForm(PHONE, AMOUNT, EMAIL);
        paymentBlock.clickContinueButton();

        Map<String, String> labels = bepaidIframe.getBepaidPlaceholders();

        assertAll(
                () -> assertEquals("Номер карты", labels.get("Номер карты")),
                () -> assertEquals("Срок действия", labels.get("Срок действия")),
                () -> assertEquals("Имя и фамилия на карте", labels.get("ФИО на карте")),
                () -> assertEquals("CVC", labels.get("CVC"))
        );
    }

    @Test
    public void checkBepaidPaymentIcons() {
        paymentBlock.fillPaymentForm(PHONE, AMOUNT, EMAIL);
        paymentBlock.clickContinueButton();
        var icons = bepaidIframe.getBepaidPaymentIcons();

        for (var icon : icons) {
            System.out.println(icon.getAttribute("class"));
            assertTrue(icon.isDisplayed(), "Иконка не отображается");
        }
    }

    @AfterEach
    public void tearDown() {
        actions.pause(500)
                .build()
                .perform();
        driver.quit();
    }
}
