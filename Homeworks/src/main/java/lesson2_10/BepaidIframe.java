package lesson2_10;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BepaidIframe extends BasePage {
    private final Actions actions;

    @FindBy(xpath = "//iframe[@class='bepaid-iframe']")
    private WebElement paymentIframe;

    @FindBy(xpath  = "//div[@class='pay-description__cost']/span")
    private static WebElement costSpan;

    @FindBy(xpath  = "//div[@class='card-page__card']/button")
    private WebElement payButton;

    @FindBy(xpath  = "//div[@class='pay-description__text']/span")
    private WebElement numberSpan;

    @FindBy(id  = "//input[@id='cc-number']")
    private WebElement numberCardInput;

    @FindBy(xpath  = "//label[@class='ng-tns-c2312288139-4 ng-star-inserted']")
    private WebElement validityPeriodLabel;

    @FindBy(xpath  = "//label[@class='ng-tns-c2312288139-3 ng-star-inserted']")
    private WebElement fioLabel;

    @FindBy(xpath  = "//label[@class='ng-tns-c2312288139-5 ng-star-inserted']")
    private WebElement cvcLabel;

    @FindBy(xpath  = "//div[@class='cards-brands cards-brands__container ng-tns-c891095944-0 ng-trigger ng-trigger-brandsState ng-star-inserted']")
    private WebElement cardIcons;

    public BepaidIframe(WebDriver driver, Actions actions) {
        super(driver);
        this.actions = actions;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void switchToPaymentIframe() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentIframe));
    }

    public String getPaymentAmount() {
        switchToPaymentIframe();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(costSpan));
        return costSpan.getText();
    }

    public String getButtonAmount() {
        return payButton.getText().replace("Оплатить", "").trim();
    }

//    public List<WebElement> getBepaidIframeIcons() {
//        return cardIcons;
//    }

}
