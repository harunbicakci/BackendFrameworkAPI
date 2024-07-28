package Steps;

import Utils.APIConstants;
import Utils.APIUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsString;

public class AddUserSteps {

    RequestSpecification request;
    Response response;


    @Given("I create a create new user request")
    public void i_create_a_create_new_user_request() {
        RestAssured.baseURI = APIConstants.BASE_URI;
        request = RestAssured.given();
    }

    @Given("I provide the header information new")
    public void i_provide_the_header_information_new() {
        //      request.contentType(APIConstants.CONTENT_TYPE);
        request.contentType(ContentType.JSON);
        //  request.header("Content-Type", "application/json");
    }

    @Given("I provide the body information for new user")
    public void i_provide_the_body_information_for_new_user(io.cucumber.datatable.DataTable dataTable) {

        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.


        Map<String, String> newUserMap = dataTable.asMap(String.class, String.class);
        String uName = newUserMap.get("username");

        System.out.println("newUserMap is --> " + newUserMap);


//        Map<String, Object> newUserMap = new HashMap<>();
//        newUserMap.put("userName", "username");
//        newUserMap.put("password", "password");
//
//        System.out.println("newUserMap is --> " + newUserMap);
//
//        request.body(APIUtils.mapToJson(newUserMap));


    }

    @When("I make a POST call to create new user endpoint")
    public void i_make_a_post_call_to_create_new_user_endpoint() {
        response = request.post(APIConstants.CREATE_NEW_ACCOUNT_ENDPOINT);
    }

    @Then("validate the status code is {int}")
    public void validate_the_status_code_is(Integer status) {
        response.then().assertThat().statusCode(status);
        System.out.println("The actual status code is: " + status);
    }

    @Then("validate that body contains {string}")
    public void validate_that_body_contains(String success) {
        response.then().assertThat().body(containsString(success));
        //  response.then().assertThat().body("status", containsString(success);
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
