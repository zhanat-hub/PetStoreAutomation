package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class PSSwaggerMainPage {

    /**
     * This class is used to find and initialize the
     * elements of the webpages for re-usability purposes.
     */

    public PSSwaggerMainPage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//div[@id='operations-pet-uploadFile']//button[@class='opblock-summary-control']")
    public WebElement postPetImageSection;
}
