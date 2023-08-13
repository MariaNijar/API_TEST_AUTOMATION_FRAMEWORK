package step_definitions;

import com.jayway.restassured.response.ResponseBody;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dto.Owner;
import dto.Pet;
import dto.Pets;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import support.MyConfig;
import support.SupportFunctions;
import com.jayway.restassured.response.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class PetsStepDef {

    /*private static ResponseBody body;

    @When("^I want to know all the pets in the clinic$")
    public void i_want_to_know_all_the_pets_in_the_clinic() throws Throwable {
        body = SupportFunctions.get(MyConfig.Endpoint + "api/pets");
        System.out.println(body.asString());

    }


    @Then("^I should receive 13 pets$")
    public void i_should_receive_pets() throws Throwable {
        Pet[] petsDTO = SupportFunctions.convertResponseArray(Pet[].class);
        int amountOfPets = petsDTO.length;
        Assert.assertEquals("the amount of pets is 13 | ",13,amountOfPets);
    }*/

    private static Response response;
    private Owner owner;
    private Pet newPet;
    private String ownerJson;
    private Response petCategoryResponse;
    private Response expectedPetCategoriesResponse;
    private Response actualPetCategoriesResponse;

    @When("^I want to know all the pets in the clinic$")
    public void i_want_to_know_all_the_pets_in_the_clinic() throws Throwable {
        response = SupportFunctions.get(MyConfig.Endpoint + "api/pets");
        System.out.println(response.getBody().asString());
    }

    @Then("^I should receive 13 pets$")
    public void i_should_receive_pets() throws Throwable {
        Pet[] petsDTO = SupportFunctions.convertResponseArray(Pet[].class);
        int amountOfPets = petsDTO.length;
        int statusCode = response.getStatusCode(); // Access the status code

        Assert.assertEquals("Response code not as expected | ", 200, statusCode);
        Assert.assertEquals("the amount of pets is 13 | ", 13, amountOfPets);
    }

    @Given("^I have the details of an owner$")
    public void i_have_the_details_of_an_owner() throws Throwable {
        String ownerId = "1"; // Replace with the actual owner ID you want to retrieve
        ownerJson = SupportFunctions.get(MyConfig.Endpoint + "api/owners/" + ownerId)
                .asString();
        assertNotNull(ownerJson);
    }

    @When("^I add a new pet$")
    public void i_add_a_new_pet() throws Throwable {
        String newPetJson = "{ " +
                "\"name\": \"Buddy\", " +
                "\"birthDate\": \"2022-01-15\", " +
                "\"type\": {" +
                "\"id\": 1, " + // Assuming 1 is the ID for pet type (e.g., cat)
                "\"name\": \"cat\"" +
                "}, " +
                "\"owner\": {" +
                "\"id\": " + owner.getId() + ", " +
                "\"firstName\": \"" + owner.getFirstName() + "\", " +
                "\"lastName\": \"" + owner.getLastName() + "\", " +
                "\"address\": \"" + owner.getAddress() + "\", " +
                "\"city\": \"" + owner.getCity() + "\", " +
                "\"telephone\": \"" + owner.getTelephone() + "\"" +
                "}}";

        String responseJson = SupportFunctions.post(MyConfig.Endpoint + "api/pets", newPetJson)
                .asString();
        newPet = SupportFunctions.convertResponse(Pet.class);
    }

    @Then("^the pet is added successfully$")
    public void the_pet_is_added_successfully() {
        assertNotNull("New pet ID should not be null", newPet.getId());
    }




}
