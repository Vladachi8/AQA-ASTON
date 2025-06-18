package lesson2_10;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PaymentPage extends BasePage {
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

    @FindBy(xpath = "//div[@class='pay__wrapper']//button[contains(., 'Продолжить')]")
    private WebElement continueButton;

    @FindBy(xpath = "//iframe[@class='bepaid-iframe']")
    private WebElement paymentIframe;

    @FindBy(xpath = "//div[@class='app-wrapper__content']")
    private WebElement paymentPopup;

    public PaymentPage(WebDriver driver) {
        super(driver);
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
}


