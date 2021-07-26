package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.PSSwaggerMainPage;
import utilities.Configuration;
import utilities.Driver;

public class PetStoreUiSteps {

    WebDriver driver = Driver.getDriver();
    PSSwaggerMainPage psSwaggerMainPage = new PSSwaggerMainPage();

    @Given("User navigates to pet store swagger page")
    public void user_navigates_to_pet_store_swagger_page() {
        driver.get(Configuration.getProperty("PetStoreSwaggerURL"));
    }

    @When("User clicks on the post pet image section to expand")
    public void user_clicks_on_the_post_pet_image_section_to_expand() {
        psSwaggerMainPage.postPetImageSection.click();
    }

    @Then("User validates the post pet image section is expanded")
    public void user_validates_the_post_pet_image_section_is_expanded() {
        String expanded = psSwaggerMainPage.postPetImageSection.getAttribute("aria-expanded");
        Assert.assertEquals("Section expanded","true", expanded);
    }

    @When("User clicks on the post pet image section to collapse")
    public void user_clicks_on_the_post_pet_image_section_to_collapse() {
        psSwaggerMainPage.postPetImageSection.click();
    }

    @Then("User validates the post pet image section is collapsed")
    public void user_validates_the_post_pet_image_section_is_collapsed() {
        String expanded = psSwaggerMainPage.postPetImageSection.getAttribute("aria-expanded");
        Assert.assertEquals("Section collapsed","false", expanded);
    }

}
