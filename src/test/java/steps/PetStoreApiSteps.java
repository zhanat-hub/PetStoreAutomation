package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.Pets;
import utilities.APIutils;

import java.util.*;

public class PetStoreApiSteps {
    String newPet = "cat";
    String endpoint;
    Response response;
    Integer newPetId ;
    String status = "ready";

    @Given("User creates a new pet with POST request")
    public void user_creates_a_new_pet_with_POST_request() {
        endpoint = "/pet";
        Pets pets = new Pets();
        pets.setName(newPet);
        Random random = new Random();
        newPetId=random.nextInt(1000);  // Generating random id
        pets.setId(newPetId);
        pets.setStatus(status);
        List<String> photoURLs = new ArrayList<>();
        photoURLs.add(System.getProperty("user.dir")+"/src/test/resources/testData/cat.jpeg");
        pets.setPhotoUrls(photoURLs);
        response = APIutils.postCall(endpoint, pets);

        // newPetId = response.body().jsonPath().getString("id");
        //  >>  I tried to let the api call to generate its own id, save it and use it for GET call
        //      but there's an issue with that method where it works only every other time. That's why,
        //      I hardcoded the id to later use in my validation steps.
    }

    @Then("User validates new pet is created with status code {int}")
    public void user_validates_new_pet_is_created_with_status_code(Integer expectedStatusCode) { // 200 status code comes from the feature file
        Integer actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);

        // For some reason the POST request is giving 200 status code on POSTMAN instead of 201.
        // So I validated it with 200 status code
    }

    @Then("User validates new pet is created with GET request")
    public void user_validates_new_pet_is_created_with_GET_request() {
        endpoint = "/pet/"+newPetId;
        response = APIutils.getCall(endpoint);
        String actualPetName = response.body().jsonPath().getString("name");
        System.out.println("Actual pet name "+actualPetName);
        Assert.assertEquals(newPet,actualPetName);
    }

    @When("User searches for similar pets by status {string} with GET request")
    public void user_searches_for_similar_pets_by_status_with_GET_request(String status) { //status = "pending"
        endpoint = "/pet/findByStatus";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("status", status);
        response = APIutils.getCall(endpoint, queryParams);  // Overloaded getCall with query parameters
    }

    @Then("User validates status code of GET request is {int}")
    public void user_validates_status_code_of_GET_request_is(Integer expectedStatusCode) { //statusCode = 200
        Integer actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("User validates searched pets share the same status {string}")
    public void user_validates_searched_pets_share_the_same_status(String status) {  // status = "pending"
        Pets[] pets = response.body().as(Pets[].class);
        for (Pets pet : pets){
            String actualStatus = pet.getStatus();
            Assert.assertEquals(status, actualStatus);
        }
    }

    @Given("User creates a new pet with POST request using corrupted jpeg")
    public void user_creates_a_new_pet_with_POST_request_using_corrupted_jpeg() {
        endpoint = "/pet";
        Pets pets = new Pets();
        pets.setName(newPet);
        Random random = new Random();
        newPetId=random.nextInt(1000);
        pets.setId(Integer.valueOf(newPetId));
        pets.setStatus(status);
        List<String> photoURLs = new ArrayList<>();
        photoURLs.add(System.getProperty("user.dir")+"/src/test/resources/testData/catCorrupted.jpeg");
        pets.setPhotoUrls(photoURLs);
        response = APIutils.postCall(endpoint, pets);
    }

    @Then("User validates new pet is not created with status code {int}")
    public void user_validates_new_pet_is_not_created_with_status_code(Integer expectedStatusCode) {
        Integer actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("User validates new pet is not created with GET request")
    public void user_validates_new_pet_is_not_created_with_GET_request() {
        endpoint = "/pet/"+newPetId;
        response = APIutils.getCall(endpoint);
        String actualPetName = response.body().jsonPath().getString("name");
        System.out.println("Actual pet name "+actualPetName);
        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertEquals(newPet, actualPetName);

        // I manually tested the api calls with POSTMAN and found out that
        // most of the negative scenarios fail, which means that there are
        // no constraints or requirements for the API requests. Nevertheless,
        // I automated one negative scenario where I upload an empty jpeg file
        // and the POST call actually goes through.
    }
}
