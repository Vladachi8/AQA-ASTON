package lesson2_10;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BepaidIframe extends BasePage {
    private final Actions actions;

    @FindBy(xpath = "//iframe[@class='bepaid-iframe']")
    private WebElement paymentIframe;

    @FindBy(xpath = "//div[@class='pay-description__cost']/span")
    private static WebElement costSpan;

    @FindBy(xpath = "//div[@class='card-page__card']/button")
    private WebElement payButton;

    @FindBy(xpath = "//div[@class='pay-description__text']/span")
    private WebElement numberSpan;

    @FindBy(xpath = "//label[@class='ng-tns-c2312288139-1 ng-star-inserted']")
    private WebElement numberCardInput;

    @FindBy(xpath = "//label[@class='ng-tns-c2312288139-4 ng-star-inserted']")
    private WebElement validityPeriodLabel;

    @FindBy(xpath = "//label[@class='ng-tns-c2312288139-3 ng-star-inserted']")
    private WebElement fioLabel;

    @FindBy(xpath = "//label[@class='ng-tns-c2312288139-5 ng-star-inserted']")
    private WebElement cvcLabel;

    @FindBy(xpath = "//div[@class='cards-brands cards-brands__container ng-tns-c891095944-0 ng-trigger ng-trigger-brandsState ng-star-inserted']/img")
    private List<WebElement> cardIcons;

    @FindBy(xpath = "//div[@class='cards-brands cards-brands_random ng-tns-c891095944-0 ng-star-inserted']/img")
    private List<WebElement> cardMirIcons;

    @FindBy(xpath = "//img[@class='ng-tns-c891095944-0 ng-trigger ng-trigger-randomCardState ng-star-inserted']:not([style*='display: none'])")
    private List<WebElement> visibleCardMirIcons;

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
        switchToPaymentIframe();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(payButton));
        return payButton.getText();
    }

    public String getUserNumberSpan() {
        switchToPaymentIframe();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(numberSpan));
        return numberSpan.getText().replace("Оплата: Услуги связи Номер:", "").trim();
    }

    public Map<String, String> getBepaidPlaceholders() {
        Map<String, String> labels = new HashMap<>();
        try {
            switchToPaymentIframe();
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(numberCardInput));

            labels.put("Номер карты", numberCardInput.getText());
            labels.put("Срок действия", validityPeriodLabel.getText());
            labels.put("ФИО на карте", fioLabel.getText());
            labels.put("CVC", cvcLabel.getText());

        } finally {
            driver.switchTo().defaultContent();
        }

        return labels;
    }

    public List<WebElement> getBepaidPaymentIcons() {
        switchToPaymentIframe();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(numberCardInput));
        return cardIcons;
    }
}