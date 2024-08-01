package Steps;

import Utils.APIConstants;
import io.cucumber.datatable.internal.difflib.StringUtills;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class ExampleAddUserSteps {

    public int statusCode;
    public RequestSpecification httpRequest;
    public Response response;
    public int responseCode;
    public ResponseBody body;
    public JSONObject requestParams;
    public String newUserID;

    @Given("I hit the url endpoint")
    public void I_hit_the_url_endpoint() {

        RestAssured.baseURI = APIConstants.BASE_URI;
    }

    @When("New user profile post request created with header and body for {string} and {string}")
    public void New_user_profile_post_request_created_with_header_and_body_for_and(String user, String pass) {

        httpRequest = RestAssured.given();
        requestParams = new JSONObject();
        requestParams.put("userName", user);
        requestParams.put("password", pass);

        httpRequest.body(requestParams.toString());
        response = httpRequest.contentType(ContentType.JSON).post(APIConstants.CREATE_NEW_ACCOUNT_ENDPOINT);
        body = response.getBody();
        System.out.println("Response getStatusLine() --> " + response.statusLine());
        System.out.println("Response asString --> " + response.asString());
    }

    @Then("I validate status code {int}")
    public void I_validate_status_code(Integer statusCode){

        responseCode = response.getStatusCode();
        assertEquals(responseCode,201);
        System.out.println("PRINT OUT: Status Code is --> " + responseCode);
        System.out.println("------------------------------------------------------------------");
    }

    @Then("I validate body contains {string}")
    public void I_validate_body_contains(String str){

        body = response.getBody();
        String responseBody = body.asString();

        System.out.println("PRINT OUT: Response Body asSTRING() method is --> " + responseBody);
        System.out.println("------------------------------------------------------------------");

//        #From one website for JSONString
//        String JSONstring ="{\r\n" +
//                "  \"Name\": \"Anchita\",\r\n" +
//                "  \"lastName\": \"sharma\"\r\n" +
//                "}";
//        JSONPath JSONPath = JSONPath.from(JSONstring);
//        String Name = JSONPath.getString("Name");
//        String lastName = JSONPath.getString("lastName");

        JsonPath jsnPath = response.jsonPath();
        newUserID = jsnPath.get("userID");
        System.out.println("PRINT OUT: JSONPath.get(userID) method is --> " + newUserID);
        System.out.println("------------------------------------------------------------------");
        String jsnPathStr = jsnPath.get("userID").toString();
        System.out.println("PRINT OUT: JSONPath.toString() method is --> " + jsnPathStr);
        System.out.println("------------------------------------------------------------------");

    //    assertEquals(str, jsnPathStr);
    }

    @Then("I store the new user in database")
    public void I_store_the_new_user_in_database(){

//        newUserID.


    }
}
