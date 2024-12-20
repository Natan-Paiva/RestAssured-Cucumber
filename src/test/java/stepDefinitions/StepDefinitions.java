package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

import java.io.IOException;
import static io.restassured.RestAssured.given;

public class StepDefinitions extends Utils {
    RequestSpecification res;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String placeId;

    @Given("Add Place payload {string} {string} {string}")
    public void addPlacePayload(String name, String language, String address) throws IOException {
        res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
    }

    @When("user calls {string} wiht {string} http request")
    public void userCallsWihtPostHttpRequest(String resource, String method) {
        APIResources resourceAPI = APIResources.valueOf(resource);

        if(method.equalsIgnoreCase("post")) {
            response = res.when().post(resourceAPI.getResource());
        }else if(method.equalsIgnoreCase("get")) {
            response = res.when().get(resourceAPI.getResource());
        }
    }

    @Then("the API call is success with status code {int}")
    public void theAPICallIsSuccessWithStatusCode(int code) {
        assertEquals(response.getStatusCode(),code);
    }

    @And("{string} in response is {string}")
    public void inResponseIs(String key, String value) {
        assertEquals(getJsonPath(response, key), value);
    }

    @And("verify place_Id created maps to {string} using {string}")
    public void verifyPlace_IdCreatedMapsToUsing(String name, String resource) throws IOException {
        placeId = getJsonPath(response,"place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", placeId);
        userCallsWihtPostHttpRequest(resource, "GET");
        String resName = getJsonPath(response, "name");
        assertEquals(name, resName);
    }

    @Given("DeletePlace Payload")
    public void deleteplacePayload() throws IOException {
        res = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
    }
}
