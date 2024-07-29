package Steps;

import Utils.APIConstants;
import Utils.APIUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsString;

public class AddUserSteps {

    RequestSpecification request;
    Response response;
    JsonPath responseBody;
    String newUserID001;

    @Given("I create a create new user request")
    public void i_create_a_create_new_user_request() {
        RestAssured.baseURI = APIConstants.BASE_URI;
//        request = RestAssured.given();
    }

    @Given("I provide the header information new")
    public void i_provide_the_header_information_new() {
        //      request.contentType(APIConstants.CONTENT_TYPE);

        //  request.header("Content-Type", "application/json");
    }

    @Given("I provide the body information for new user {string} and {string}")
    public void i_provide_the_body_information_for_new_user_and(String user, String pass) {

        JSONObject bodyJson = new JSONObject();
        bodyJson.put("userName", user);
        bodyJson.put("password", pass);
        System.out.println("PRINT OUT: JSON request body is --> " + bodyJson);

        response = (Response) given()
                    .contentType(ContentType.JSON)
                    .body(bodyJson.toString());

    }

    @When("I make a POST call to create new user endpoint")
    public void i_make_a_post_call_to_create_new_user_endpoint() {
        response = when()
                     .post(APIConstants.CREATE_NEW_ACCOUNT_ENDPOINT)
                     .prettyPeek();

    }

    @Then("validate the status code is {int}")
    public void validate_the_status_code_is(Integer status) {

        response.then().assertThat().statusCode(status);
                // .contentType(ContentType.JSON);

        int actualStatusCode = response.getStatusCode();
        System.out.println("PRINT OUT: Actual Status Code is --> " + actualStatusCode);
    }

    @Then("validate that body contains {string}")
    public void validate_that_body_contains(String created) {

        responseBody = response.jsonPath();
        newUserID001 = responseBody.get("userID");
        System.out.println("PRINT OUT: New users UserID is --> " + newUserID001);

        response.then().assertThat().body(containsString(created));
        responseBody.toString().contains("Created");
        //  response.then().assertThat().body("status", containsString(success);
        //  JsonPath actualResponseBody = response.jsonPath();
        //  returns all the body
    }

    @Then("validate that the value of the {string} is {string}")
    public void validate_that_the_value_of_the_is(String key, String value) {
        // this is checking that "result" key in the body has the value exactly
        // as we passed it from the feature file
        response.then().assertThat().body(key, equalTo(value));

        // lets also print the request
        response.prettyPeek();

    }


}
