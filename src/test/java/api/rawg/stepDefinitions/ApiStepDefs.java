package api.rawg.stepDefinitions;

import api.rawg.util.ApiUtil;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.CoreMatchers.*;
import utilities.ConfigReader;

public class ApiStepDefs {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;


    @Given("user sends get requests to {string} endpoint")
    public void user_sends_get_requests_to_endpoint(String path) {
        requestSpecification = ApiUtil.requestSpecification();
        requestSpecification.pathParam("first",path).
                             queryParam("key", ConfigReader.getProperty("apiKey"));
        response = RestAssured.given().spec(requestSpecification).when().get("{first}");
    }
    @Then("user verifies response has correct output")
    public void user_verifies_response_has_correct_output() {
        responseSpecification = ApiUtil.responseSpecification();
        response.then().spec(responseSpecification);
    }


    @Then("user verifies slug name contains {string}")
    public void userVerifiesSlugNameContains(String games_pk){
        responseSpecification = ApiUtil.responseSpecification();
        response.then().spec(responseSpecification);
        response.then().body("results[0].slug",containsString(games_pk));
    }

    @Given("user sends get requests to {string} {string} {string} endpoint")
    public void userSendsGetRequestsToEndpoint(String path1, String path2, String path3) {
        requestSpecification = ApiUtil.requestSpecification();
        requestSpecification.pathParams("first",path1,"second",path2,"third",path3).
                queryParam("key", ConfigReader.getProperty("apiKey"));
        response = RestAssured.given().spec(requestSpecification).when().get("{first}/{second}/{third}");
    }
}
