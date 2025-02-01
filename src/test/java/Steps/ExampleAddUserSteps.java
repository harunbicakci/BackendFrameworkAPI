package Steps;

import io.cucumber.datatable.internal.difflib.StringUtills;
import Utils.APIConstants;
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

import javax.sound.midi.Soundbank;

import java.util.HashMap;
import java.util.Map;

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
        System.out.println("1- Response directly is --> " + response);
        System.out.println("2- Response getStatusLine() --> " + response.statusLine());
        System.out.println("3- Response asString --> " + response.asString());
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

//        get username from JsonPath
        String usernameFromResponse = jsnPath.get("username");
        System.out.println("PRINT OUT: Username from Response by using JsonPath.get(-username-) --> " + usernameFromResponse);

        //    assertEquals(str, jsnPathStr);
    }

    @Then("I store the new user in database")
    public void I_store_the_new_user_in_database(){

//        newUserID.


    }

//-----------------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------------------

//  1- Create JSON request data, create Map
    Map<String, Object> requestData = new HashMap<>();
    requestData.put("name", "Kobe Bryant");
    requestData.put("email", "kobe0111@gmail.com");
    requestData.put("age", 30);

//  2- Post Request
    public static Response sendPostRequest(String endpoint, Map<String, Object> requestData) {
        String requestBody = createJsonRequestBody(requestData);

        return RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .post(endpoint);
    }

//  3- Method to create JSON request body from Map
    public static String createJsonRequestBody(Map<String, Object> data){
        JSONObject jsonObject = new JSONObject();
        for(Map.Entry<String, Object> entry : data.entrySet()){
            jsonObject.put(entry.getKey(), entry.getValue());
        }

        return jsonObject.toString();
    }

}
