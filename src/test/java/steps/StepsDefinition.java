package steps;

import commons.ApiHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StepsDefinition extends ApiHelper {

    @Given("user creates request with resource path {string}")
    public void userCreatesRequestWithGivenPath(String path) {
        setResourcePath(path);
    }

    @And("with base parameter {string}")
    public void withBaseParameter(String baseValue) {
        setParameter("base", baseValue);
    }

    @And("with symbols parameter {string}")
    public void withSymbolsParameter(String symbolsValue) {
        setParameter("symbols", symbolsValue);
    }

    @When("user sends get request")
    public void userSendsGetRequest(){
        sendGetRequest();
    }

    @Then("response status code should be {int}")
    public void responseStatusCodeShouldBe(int responseStatusCode){
        assertThat(responseStatusCode, equalTo(getResponseStatusCode()));
    }

    @Then("response body should contain error message {string}")
    public void responseBodyShouldContain(String expectedString){
        assertThat(getResponseBody(), containsString(expectedString));
    }

    @And("base should be set to {string}")
    public void baseShouldBeSetTo(String expectedBase){
        validateResponseBodyElement("base", equalTo(expectedBase));
    }

    @And("rates count should be {int}")
    public void ratesCountShouldBe(int expectedRatesCount){
        validateResponseBodyElement("rates.size()", equalTo(expectedRatesCount));
    }
}
