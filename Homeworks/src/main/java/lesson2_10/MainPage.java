package lesson2_10;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    @FindBy(xpath = "//div[@class='cookie__buttons']/button[@id='cookie-agree']")
    private WebElement cookieAcceptButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void acceptCookies() {
        try {
            cookieAcceptButton.click();
        } catch (Exception e) {
            System.out.println("Куки не предлагались");
        }
    }
}