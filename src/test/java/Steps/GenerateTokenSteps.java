package Steps;

import Utils.APIConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsString;

import io.restassured.http.ContentType;

public class GenerateTokenSteps {

    RequestSpecification request;
    Response response;

    @Given("I create a generate token request")
    public void i_create_a_generate_token_request() {
        RestAssured.baseURI = APIConstants.BASE_URI;
        request = RestAssured.given();
    }

    @Given("I provide the header information")
    public void i_provide_the_header_information() {
  //      request.contentType(APIConstants.CONTENT_TYPE);
        request.contentType(ContentType.JSON);
    //  request.header("Content-Type", "application/json");
    }

    @Given("I provide the body information")
    public void i_provide_the_body_information() {
        String payload = "{\r\n"
                + "  \"userName\": \"Neotech001\",\r\n"
                + "  \"password\": \"Neotech2024!\"\r\n"
                + "}";

        request.body(payload);
    }

    @When("I make a POST call to generate token endpoint")
    public void i_make_a_post_call_to_generate_token_endpoint() {
        response = request.post(APIConstants.GENERATE_TOKEN_ENDPOINT);
    }

    @Then("I validate the status code is {int}")
    public void i_validate_the_status_code_is(Integer status) {
        response.then().assertThat().statusCode(status);
        System.out.println("The actual status code is: " + status);
    }

    @Then("I validate that body contains {string}")
    public void i_validate_that_body_contains(String success) {
        response.then().assertThat().body(containsString(success));
    //  response.then().assertThat().body("status", containsString(success);
    }

    @Then("I validate that the value of the {string} is {string}")
    public void i_validate_that_the_value_of_the_is(String key, String value) {
    // this is checking that "result" key in the body has the value exactly
    // as we passed it from the feature file
        response.then().assertThat().body(key, equalTo(value));

    // lets also print the request
        response.prettyPeek();

    }
}
