package Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.response.Response;

import java.lang.reflect.Type;
import java.util.Map;

public class APIUtils {


    // Gson converst Java Objects into Json
    private static final Gson gson = new Gson();

//    Convert JSON String to Map
//    @param jsonString JSON String to
//    @return Map representation of the JSON String
    public static Map<String, Object> jsonToMap(String jsonString) {
        Type type = new TypeToken<Map<String, Object>>() {}.getType();

        return gson.fromJson(jsonString, type);
    }

//    Convert Map
//    @param map Map to convert
//    @return JSON String ewpresentation of the map
    public static String mapToJson(Map<String, String> map) {

        return gson.toJson(map);
    }

//    Extract response as Map
//    @param response RestAssured response
//    @return Map representation of the response body
    public static Map<String, Object> extractResponseAsMap(Response response) {

        return jsonToMap(response.getBody().asString());
    }



}
