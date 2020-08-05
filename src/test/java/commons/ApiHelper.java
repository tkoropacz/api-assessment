package commons;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    private RequestSpecification requestSpecification;
    private Response response;

    private void setBaseUri() {
        requestSpecification = given().baseUri("https://api.ratesapi.io/api");
    }

    public void setResourcePath(String resourcePath) {
        setBaseUri();
        requestSpecification.basePath(String.format("/%s", resourcePath));
    }

    public void setParameter(String parameterName, String parameterValue) {
        requestSpecification.param(parameterName, parameterValue);
    }

    public void sendGetRequest() {
        response = requestSpecification.get();
    }

    public int getResponseStatusCode() {
        return response.getStatusCode();
    }

    public void validateResponseBodyElement(String element, Matcher<?> matcher) {
        response.then().body(element, matcher);
    }
}
