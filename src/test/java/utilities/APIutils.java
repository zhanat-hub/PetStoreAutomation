package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIutils {

    /**
     * Static methods
     *      .setHeaders() -> returns Map
     *      .getCall(String endpoint);  ->  returns RESPONSE
     *      .postCall(String endpoint, Object body);    ->  returns RESPONSE
     * 	    .putCall(String endpoint, Object body);     -> returns RESPONSE
     * 	    .deleteCall(String endpoint);   -> returns RESPONSE
     */

    /**
     * .setHeaders();
     * @return
     */

    public static Map<String, Object> setHeaders(){
        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        return headers;
    }

    /**
     * getCall();
     * @param endpoint
     * @return
     */

    public static Response getCall(String endpoint){
        Response response = given().baseUri(Configuration.getProperty("ApiBaseURL"))
                .and().headers(setHeaders())
                .when().get(endpoint);
        response.then().log().all();
        return response;
    }

    public static Response getCall(String endpoint, Map<String, Object> queryParams){
        Response response = given().baseUri(Configuration.getProperty("ApiBaseURL"))
                .and().headers(setHeaders())
                .and().queryParams(queryParams)
                .when().get(endpoint);
        response.then().log().all();
        return response;
    }

    /**
     * postCall();
     * @param endpoint
     * @param body
     * @return
     */
    public static Response postCall(String endpoint, Object body){
        Response response = given().baseUri(Configuration.getProperty("ApiBaseURL"))
                .and().headers(setHeaders())
                .when().body(body)
                .and().post(endpoint);
        response.then().log().all();
        return response;
    }

    /**
     * putCall();
     * @param endpoint
     * @param body
     * @return
     */
    public static Response putCall(String endpoint, Object body){
        Response response = given().baseUri(Configuration.getProperty("ApiBaseURL"))
                .and().headers(setHeaders())
                .when().body(body)
                .and().put(endpoint);
        response.then().log().all();
        return response;
    }

    /**
     * deleteCall();
     * @param endpoint
     * @return
     */
    public static Response deleteCall(String endpoint){
        Response response = given().baseUri(Configuration.getProperty("ApiBaseURL"))
                .and().headers(setHeaders())
                .when().delete(endpoint);
        response.then().log().all();
        return response;
    }
}
