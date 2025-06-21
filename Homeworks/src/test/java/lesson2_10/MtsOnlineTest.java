package lesson2_10;

import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.Map;
import io.qameta.allure.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.jupiter.api.extension.ExtensionContext;

@ExtendWith(AllureReportExtension.class)
public class MtsOnlineTest {
    private static WebDriver driver;
    private static MainPage mainPage;
    private static PaymentBlock paymentBlock;
    private static BepaidIframe bepaidIframe;
    private Actions actions;
    private static AllureReportExtension allureExtension;

    private final String PHONE = "297777777";
    private final String AMOUNT = "1.00";
    private final String EMAIL = "RibkaKus@gmail.com";

    @BeforeAll
    @Step("Настройка драйвера")
    @DisplayName("Инициализация WebDriverManager")
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    @Step("Подготовка тестового окружения")
    @DisplayName("Инициализация теста")
    public void setup() {
        driver = new ChromeDriver();
        new AllureReportExtension(driver);
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
    @DisplayName("Проверка заголовка платежного блока")
    @Description("Тест проверяет, что заголовок блока соответствует ожидаемому")
    @Story("Пользователь видит корректный заголовок блока")
    @Severity(SeverityLevel.TRIVIAL)
    public void testBlockTitle() {
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        String actualTitle = paymentBlock.getBlockTitleText();

        Allure.addAttachment("Заголовок", "text/plain", actualTitle);
        Allure.step("Проверка заголовка", () -> {
            assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому");
        });
    }

    @Test
    @DisplayName("Проверка иконок платежных систем")
    @Description("Тест проверяет отображение иконок платежных систем")
    @Story("Пользователь видит все иконки платежных систем")
    @Severity(SeverityLevel.NORMAL)
    public void checkPaymentIcons() {
        var icons = paymentBlock.getPaymentIcons();

        Allure.addAttachment("Количество иконок", "text/plain", "Найдено: " + icons.size());
        Allure.step("Проверка отображения иконок", () -> {
            for (var icon : icons) {
                System.out.println(icon.getAttribute("alt"));
                assertTrue(icon.isDisplayed(), "Иконка не отображается");
            }
        });
    }

    @Test
    @DisplayName("Проверка ссылки платежного блока")
    @Description("Тест проверяет, что ссылка блока ведет на ожидаемый ресурс")
    @Story("Пользователь может перейти по ссылке 'Подробнее'")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkLink() {
        String href = paymentBlock.getDetailsLinkHref();
        Allure.addAttachment("Ссылка", "text/plain", href);
        Allure.step("Проверка ссылки", () -> {
            assertTrue(href.contains("mts.by"), "Ссылка ведет на внешний ресурс");
        });

        paymentBlock.clickDetailsLink();
        String pageTitle = driver.getTitle();
        Allure.addAttachment("Заголовок страницы", "text/plain", pageTitle);
        Allure.step("Проверка открытой страницы", () -> {
            assertTrue(driver.getTitle().contains("Порядок оплаты и безопасность интернет платежей"),
                    "Не та страница");
        });
    }

    @Test
    @DisplayName("Проверка всплывающего окна")
    @Description("Тест проверяет, что после введения валидных данных, отображается всплывающее окно")
    @Story("Пользователь видит всплывающее окно оплаты")
    @Severity(SeverityLevel.CRITICAL)
    public void testPaymentForm() {
        paymentBlock.fillPaymentForm(PHONE, AMOUNT, EMAIL);
        Allure.step("Проверка активности кнопки 'Продолжить'", () -> {
            assertTrue(paymentBlock.isContinueButtonEnabled(), "Кнопка должна быть активна после заполнения формы");
        });
        paymentBlock.clickContinueButton();
        Allure.step("Проверка отображения попапа оплаты", () -> {
            assertTrue(paymentBlock.isPaymentPopupDisplayed(),
                    "Попап оплаты должен отображаться после нажатия кнопки 'Продолжить'");
        });
    }

    @Test
    @DisplayName("Проверка надписей в полях для пункта 'Услуги связи' платежного блока")
    @Description("Тест проверяет, что надписи в полях соответствуют спецификации")
    @Story("Пользователь видит надписи в полях для пункта 'Услуги связи' в соответствии с требованиями")
    @Severity(SeverityLevel.NORMAL)
    public void testCommunicationServicesPlaceholders() {
        paymentBlock.selectService("Услуги связи");
        Map<String, String> placeholders = paymentBlock.getFieldPlaceholders();
        Allure.addAttachment("Плейсхолдеры", "text/plain", placeholders.toString());

        Allure.step("Проверка плейсхолдеров", () -> {
            assertAll(
                    () -> assertEquals("Номер телефона", placeholders.get("Телефон"),
                            "Неверный плейсхолдер для телефона"),
                    () -> assertEquals("Сумма", placeholders.get("Сумма"),
                            "Неверный плейсхолдер для суммы"),
                    () -> assertEquals("E-mail для отправки чека", placeholders.get("Email"),
                            "Неверный плейсхолдер для email")
            );
        });
    }

    @Test
    @DisplayName("Проверка надписей в полях для пункта 'Домашний интернет' платежного блока")
    @Description("Тест проверяет, что надписи в полях соответствуют спецификации")
    @Story("Пользователь видит надписи в полях для пункта 'Домашний интернет' в соответствии с требованиями")
    @Severity(SeverityLevel.NORMAL)
    public void testHomeInternetPlaceholders() {
        paymentBlock.selectService("Домашний интернет");
        Map<String, String> placeholders = paymentBlock.getFieldPlaceholders();
        Allure.addAttachment("Плейсхолдеры", "text/plain", placeholders.toString());

        Allure.step("Проверка плейсхолдеров", () -> {
            assertAll(
                    () -> assertEquals("Номер абонента", placeholders.get("Номер абонента")),
                    () -> assertEquals("Сумма", placeholders.get("Сумма домашнего интернета")),
                    () -> assertEquals("E-mail для отправки чека", placeholders.get("E-mail домашнего интернета"))
            );
        });
    }

    @Test
    @DisplayName("Проверка надписей в полях для пункта 'Рассрочка' платежного блока'")
    @Description("Тест проверяет, что надписи в полях соответствуют спецификации")
    @Story("Пользователь видит надписи в полях для пункта 'Рассрочка' в соответствии с требованиями")
    @Severity(SeverityLevel.NORMAL)
    public void testInstallmentPlaceholders() {
        paymentBlock.selectService("Рассрочка");
        Map<String, String> placeholders = paymentBlock.getFieldPlaceholders();
        Allure.addAttachment("Плейсхолдеры", "text/plain", placeholders.toString());

        Allure.step("Проверка плейсхолдеров", () -> {
            assertAll(
                    () -> assertEquals("Номер счета на 44", placeholders.get("Номер счета на 44")),
                    () -> assertEquals("Сумма", placeholders.get("Сумма рассрочки")),
                    () -> assertEquals("E-mail для отправки чека", placeholders.get("E-mail рассрочки"))
            );
        });
    }

    @Test
    @DisplayName("Проверка надписей в полях для пункта 'Задолженность' платежного блока")
    @Description("Тест проверяет, что надписи в полях соответствуют спецификации")
    @Story("Пользователь видит надписи в полях для пункта 'Задолженность' в соответствии с требованиями")
    @Severity(SeverityLevel.NORMAL)
    public void testDebtPlaceholders() {
        paymentBlock.selectService("Задолженность");
        Map<String, String> placeholders = paymentBlock.getFieldPlaceholders();
        Allure.addAttachment("Плейсхолдеры", "text/plain", placeholders.toString());

        Allure.step("Проверка плейсхолдеров", () -> {
            assertAll(
                    () -> assertEquals("Номер счета на 2073", placeholders.get("Номер счета на 2073")),
                    () -> assertEquals("Сумма", placeholders.get("Сумма задолженности")),
                    () -> assertEquals("E-mail для отправки чека", placeholders.get("E-mail задолженности"))
            );
        });
    }

    @Test
    @DisplayName("Проверка цены указанной при заполнении на титульнике окна оплаты")
    @Description("Тест проверяет, что надпись на титульнике соответствуют введенной пользователем ранее")
    @Story("Пользователь видит цену в титульнике в соответствии с введенной")
    @Severity(SeverityLevel.NORMAL)
    public void testPaymentPriceTitle() {
        paymentBlock.fillPaymentForm(PHONE, AMOUNT, EMAIL);
        paymentBlock.clickContinueButton();

        String expectedTitle = AMOUNT + " BYN";
        String actualTitle = bepaidIframe.getPaymentAmount();
        Allure.addAttachment("Цена в заголовке", "text/plain", actualTitle);

        Allure.step("Проверка цены в заголовке", () -> {
            assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому");
        });
    }

    @Test
    @DisplayName("Проверка цены указанной при заполнении на кнопке оплаты окна оплаты")
    @Description("Тест проверяет, что надпись на кнопке оплаты соответствуют введенной пользователем ранее")
    @Story("Пользователь видит цену в кнопке в соответствии с введенной")
    @Severity(SeverityLevel.NORMAL)
    public void testPaymentPriceButton() {
        paymentBlock.fillPaymentForm(PHONE, AMOUNT, EMAIL);
        paymentBlock.clickContinueButton();

        String expectedTitle = "Оплатить " + AMOUNT + " BYN";
        String actualTitle = bepaidIframe.getButtonAmount();
        Allure.addAttachment("Цена на кнопке", "text/plain", actualTitle);

        Allure.step("Проверка цены на кнопке", () -> {
            assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому");
        });
    }

    @Test
    @DisplayName("Проверка номера указанного при заполнении в окне оплаты")
    @Description("Тест проверяет, что номер соответствуют введенному пользователем ранее")
    @Story("Пользователь видит номер в соответствии с введенным")
    @Severity(SeverityLevel.NORMAL)
    public void testUserNumberSpan() {
        paymentBlock.fillPaymentForm(PHONE, AMOUNT, EMAIL);
        paymentBlock.clickContinueButton();

        String expectedTitle = "375" + PHONE;
        String actualTitle = bepaidIframe.getUserNumberSpan();
        Allure.addAttachment("Ожидаемый номер", "text/plain", expectedTitle); // Добавлено
        Allure.addAttachment("Фактический номер", "text/plain", actualTitle); // Добавлено

        Allure.step("Проверка соответствия номера", () -> {
            assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому");
        });
    }

    @Test
    @DisplayName("Проверка надписей в полях для платежного блока")
    @Description("Тест проверяет, что надписи в полях соответствуют спецификации")
    @Story("Пользователь видит надписи в полях платежного блока в соответствии с требованиями")
    @Severity(SeverityLevel.NORMAL)
    public void testBepaidPlaceholders() {
        paymentBlock.fillPaymentForm(PHONE, AMOUNT, EMAIL);
        paymentBlock.clickContinueButton();

        Map<String, String> labels = bepaidIframe.getBepaidPlaceholders();
        Allure.addAttachment("Полученные надписи полей", "text/plain", labels.toString());

        Allure.step("Проверка надписей полей", () -> {
            assertAll(
                    () -> assertEquals("Номер карты", labels.get("Номер карты")),
                    () -> assertEquals("Срок действия", labels.get("Срок действия")),
                    () -> assertEquals("Имя и фамилия на карте", labels.get("ФИО на карте")),
                    () -> assertEquals("CVC", labels.get("CVC"))
            );
        });
    }

    @Test
    @DisplayName("Проверка иконок платежных систем блока оплаты")
    @Description("Тест проверяет отображение иконок платежных систем блока оплаты")
    @Story("Пользователь видит все иконки платежных систем блока оплаты")
    @Severity(SeverityLevel.NORMAL)
    public void checkBepaidPaymentIcons() {
        paymentBlock.fillPaymentForm(PHONE, AMOUNT, EMAIL);
        paymentBlock.clickContinueButton();
        var icons = bepaidIframe.getBepaidPaymentIcons();
        Allure.addAttachment("Количество иконок", "text/plain", "Найдено: " + icons.size());

        Allure.step("Проверка отображения иконок", () -> {
            for (var icon : icons) {
                assertTrue(icon.isDisplayed(), "Иконка не отображается");
            }
        });
    }

    @AfterEach
    @Step("Завершение теста")
    @DisplayName("Закрытие браузера")
    public void tearDown() {
        actions.pause(500)
                .build()
                .perform();
        driver.quit();
    }

    static class AllureReportExtension implements TestWatcher {
        public AllureReportExtension(WebDriver driver) {
        }

        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            new BasePage(driver).takeScreenshotOnFailure();
            Allure.step("Тест упал: " + context.getDisplayName(), () -> {
            });
        }
    }
}