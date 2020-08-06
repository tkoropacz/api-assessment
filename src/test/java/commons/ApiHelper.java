package commons;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    private RequestSpecification requestSpecification;
    private Response response;

    private void setBaseUri() {
        requestSpecification = given().baseUri(System.getProperty("baseUri"));
    }

    protected void setResourcePath(String resourcePath) {
        setBaseUri();
        requestSpecification.basePath(String.format("/%s", resourcePath));
    }

    protected void setParameter(String parameterName, String parameterValue) {
        requestSpecification.param(parameterName, parameterValue);
    }

    protected void sendGetRequest() {
        response = requestSpecification.get();
    }

    protected int getResponseStatusCode() {
        return response.getStatusCode();
    }

    protected String getResponseBody() {
        return response.getBody().asString();
    }

    protected void validateResponseBodyElement(String element, Matcher<?> matcher) {
        response.then().body(element, matcher);
    }
}
