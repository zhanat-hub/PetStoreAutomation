package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.Driver;

import java.util.concurrent.TimeUnit;

public class Hooks {

    /**
     * This class is used for Before and After methods.
     *
     * If the test case has a "@ui" tag, it will set up a headless browser to save time
     * when executing front-end test cases.

     * @param scenario
     */


    @Before
    public void setUp(Scenario scenario){
        if (scenario.getSourceTagNames().contains("@ui")) {
            WebDriver driver = Driver.getDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        System.out.println("Before scenario method");
    }

    @After
    public void tearDown(Scenario scenario) throws InterruptedException {
        if (scenario.getSourceTagNames().contains("@ui")) {
            WebDriver driver = Driver.getDriver();
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png", scenario.getName());
            }
            Thread.sleep(2000);
            driver.quit();
        }
        System.out.println("After scenario method ");
    }
}
