package lesson2_10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentBlock extends BasePage {
    private final Actions actions;

    @FindBy(xpath = "//div[@class='pay__wrapper']/h2")
    private WebElement blockTitle;

    @FindBys(@FindBy(xpath = "//div[@class='pay__partners']/ul//img"))
    private List<WebElement> paymentIcons;

    @FindBy(xpath = "//div[@class='pay__wrapper']//a[contains(., 'Подробнее о сервисе')]")
    private WebElement detailsLink;

    @FindBy(id = "connection-phone")
    private WebElement phoneInput;

    @FindBy(id = "connection-sum")
    private WebElement amountInput;

    @FindBy(id = "connection-email")
    private WebElement emailInput;

    @FindBy(id = "internet-phone")
    private WebElement subscriberPhoneInput;

    @FindBy(id = "internet-sum")
    private WebElement internetAmountInput;

    @FindBy(id = "internet-email")
    private WebElement internetEmailInput;

    @FindBy(id = "score-instalment")
    private WebElement scoreInstalmentInput;

    @FindBy(id = "instalment-sum")
    private WebElement instalmentSumInput;

    @FindBy(id = "instalment-email")
    private WebElement instalmentEmailInput;

    @FindBy(id = "score-arrears")
    private WebElement scoreArrearsInput;

    @FindBy(id = "arrears-sum")
    private WebElement arrearsSumInput;

    @FindBy(id = "arrears-email")
    private WebElement arrearsEmailInput;

    @FindBy(xpath = "//div[@class='pay__wrapper']//button[contains(., 'Продолжить')]")
    private WebElement continueButton;

    @FindBy(xpath = "//iframe[@class='bepaid-iframe']")
    private WebElement paymentIframe;

    @FindBy(xpath = "//div[@class='app-wrapper__content']")
    private WebElement paymentPopup;

    @FindBy(xpath = "//div[@class='select__wrapper']/button")
    private WebElement servicesDropdown;

    public PaymentBlock(WebDriver driver, Actions actions) {
        super(driver);
        this.actions = actions;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public String getBlockTitleText() {
        return blockTitle.getText();
    }

    public List<WebElement> getPaymentIcons() {
        return paymentIcons;
    }

    public void clickDetailsLink() {
        detailsLink.click();
    }

    public String getDetailsLinkHref() {
        return detailsLink.getAttribute("href");
    }

    public void fillPaymentForm(String phone, String amount, String email) {
        phoneInput.sendKeys(phone);
        amountInput.sendKeys(amount);
        emailInput.sendKeys(email);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public boolean isContinueButtonEnabled() {
        return continueButton.isEnabled();
    }

    public boolean isPaymentPopupDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentIframe));
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(paymentPopup));
            return true;
        } catch (Exception e) {
            System.err.println("Ошибка при проверке попапа: " + e.getMessage());
            return false;
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public void selectService(String serviceName) {
        actions.moveToElement(servicesDropdown)
                .click()
                .build()
                .perform();

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format("//ul[@class='select__list']/li[contains(., '%s')]", serviceName))));

        actions.moveToElement(option)
                .click()
                .perform();
    }

    public Map<String, String> getFieldPlaceholders() {
        Map<String, String> placeholders = new HashMap<>();

        placeholders.put("Телефон", phoneInput.getAttribute("placeholder"));
        placeholders.put("Сумма", amountInput.getAttribute("placeholder"));
        placeholders.put("Email", emailInput.getAttribute("placeholder"));

        placeholders.put("Номер абонента", subscriberPhoneInput.getAttribute("placeholder"));
        placeholders.put("Сумма домашнего интернета", internetAmountInput.getAttribute("placeholder"));
        placeholders.put("E-mail домашнего интернета", internetEmailInput.getAttribute("placeholder"));

        placeholders.put("Номер счета на 44", scoreInstalmentInput.getAttribute("placeholder"));
        placeholders.put("Сумма рассрочки", instalmentSumInput.getAttribute("placeholder"));
        placeholders.put("E-mail рассрочки", instalmentEmailInput.getAttribute("placeholder"));

        placeholders.put("Номер счета на 2073", scoreArrearsInput.getAttribute("placeholder"));
        placeholders.put("Сумма задолженности", arrearsSumInput.getAttribute("placeholder"));
        placeholders.put("E-mail задолженности", arrearsEmailInput.getAttribute("placeholder"));

        return placeholders;
    }
}