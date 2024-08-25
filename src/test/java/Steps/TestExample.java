package Steps;

import Utils.APIConstants;
import Utils.APIUtils;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.Request;

import java.util.Map;
import java.util.TreeMap;

import static io.restassured.RestAssured.given;

public class TestExample {

    static String userID001;

    @Test
    public void createNewUser() {

        String requestBody = "{\r\n"
                + " \"userName\": \"Dave964\",\r\n"
                + " \"password\": \"David@123\"\r\n"
                + "}";

        String username1 = "ElionSabah01";
        String password1 = "David@123";
        Map <String, String> map1 = new TreeMap<>();
        map1.put("userName", username1);
        map1.put("password", password1);

//        Object requestBody1 = APIUtils.mapToJson(map1);


        int expStatusCode = 201;

    //  Create and send the Request URL and Body
        RestAssured.baseURI = APIConstants.BASE_URI;
    //  save Response
        Response response = given()
                                .contentType(ContentType.JSON)
//                                .body(requestBody)
                                  .body(APIUtils.mapToJson(map1))
                            .when()
                                .post(APIConstants.CREATE_NEW_ACCOUNT_ENDPOINT)
                                .prettyPeek();

        response.then()
                    .assertThat().statusCode(expStatusCode);
    //  Create JsonPath for response and get Value with get("key") method entering key

        JsonPath responseBody = response.jsonPath();
        System.out.println("Response Body is --> " + APIUtils.extractResponseAsMap(response));


        userID001 = responseBody.get("userID");
        System.out.println("New user is created and the UserID is --> " + userID001);


    }
}
