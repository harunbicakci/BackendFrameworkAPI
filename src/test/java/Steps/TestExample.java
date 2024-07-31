package Steps;

import Utils.APIConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.Request;

import static io.restassured.RestAssured.given;

public class TestExample {

    static String userID001;

    @Test
    public void createNewUser() {

        String requestBody = "{\r\n"
                + " \"userName\": \"Dave964\",\r\n"
                + " \"password\": \"David@123\"\r\n"
                + "}";

        int expStatusCode = 201;

    //  Create and send the Request URL and Body
        RestAssured.baseURI = APIConstants.BASE_URI;
    //  save Response
        Response response = given()
                                .contentType(ContentType.JSON)
                                .body(requestBody)
                            .when()
                                .post(APIConstants.CREATE_NEW_ACCOUNT_ENDPOINT)
                                .prettyPeek();

        response.then()
                    .assertThat().statusCode(expStatusCode);
    //  Create JsonPath for response and get Value with get("key") method entering key
        JsonPath responseBody = response.jsonPath();
        System.out.println("Response Body is --> " + responseBody);


        userID001 = responseBody.get("userID");
        System.out.println("New user is created and the UserID is --> " + userID001);


    }
}
