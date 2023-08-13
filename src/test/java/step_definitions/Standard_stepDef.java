package step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import support.SupportFunctions;
import dto.Owner;
import dto.Pet;
import support.MyConfig;
import com.jayway.restassured.response.Response;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;


public class Standard_stepDef {

    private static String resource;


    @Given("^the resource of the API is \"([^\"]*)\"$")
    public void theResourceOfTheGetOneEmployeeAPIIs(String resource)  {
        Standard_stepDef.resource = resource;
        System.out.printf("Resource : %s%n", Standard_stepDef.resource);
    }

    public static String getResource() {
        return resource;
    }

    public static void setResource(String resource) {
        Standard_stepDef.resource = resource;
    }

    @Then("^the response code should be \"([^\"]*)\"$")
    public void theResponseCodeShouldBe(String responseCode)  {
        System.out.println("Returned response code : " + SupportFunctions.getResponseCode());
        Assert.assertEquals("Response code not as expected | ",
                responseCode,
                SupportFunctions.getResponseCode()
        );
    }








}
