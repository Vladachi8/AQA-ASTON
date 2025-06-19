package lesson2_10;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage extends BasePage {
    private final Actions actions;

    @FindBy(xpath = "//div[@class='cookie__buttons']/button[@id='cookie-agree']")
    private WebElement cookieAcceptButton;

    public MainPage(WebDriver driver, Actions actions) {
        super(driver);
        this.actions = actions;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));

            actions.moveToElement(cookieAcceptButton)
                    .click()
                    .build()
                    .perform();
        } catch (Exception e) {
            System.out.println("Окно куки не найдено");
        }
    }
}