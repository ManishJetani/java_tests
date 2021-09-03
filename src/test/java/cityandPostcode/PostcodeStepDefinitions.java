package cityandPostcode;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class PostcodeStepDefinitions {
    RequestSpecification request;
    JsonPath jsonPath;
    Response response;

    @Given("api base url is {string}")
    public void apiBaseUrlIs(String baseUri) {
        request = given()
                .baseUri(baseUri);
    }

    @And("passes country {string} in path as first parameter")
    public void passesCountryInPathAsFirstParameter(String country) {
        request.pathParam("country", country);
    }

    @And("passes zipcode {string} in path as second parameter")
    public void passesZipcodeInPathAsSecondParameter(String zipCode) {
        request.pathParam("zipcode", zipCode);
    }

    @When("makes postcode get request with path {string}")
    public void makesPostcodeGetRequestWithPath(String path) {
        response = request
                .when()
                .get(path)
                .then()
                .extract()
                .response();
        jsonPath = response.getBody().jsonPath();
    }

    @Then("postcode service status code should be {int}")
    public void postcodeServiceStatusCodeShouldBe(int expectedStatusCode) {
        Assert.assertThat(response.getStatusCode(), Is.is(expectedStatusCode));
    }

    @And("response country should be {string}")
    public void responseCountryShouldBe(String expectedCountry) {
        Assert.assertThat(jsonPath.get("country").toString(), Is.is(expectedCountry));
    }

    @And("response country abbreviation should be {string}")
    public void responseCountryAbbreviationShouldBe(String expectedAbbreviation) {
        // when there is space in response field name use single quotes e.g. 'country name'
        Assert.assertThat(jsonPath.getString("'country abbreviation'"), Is.is(expectedAbbreviation));
    }

    @And("latitude should be {string}")
    public void latitudeShouldBe(String expectedLatitude) {
        // places[0] will represent first array item
        Assert.assertThat(jsonPath.getString("places[0].latitude"), Is.is(expectedLatitude));
    }

    @And("state should be {string}")
    public void stateShouldBe(String expectedSTate) {
        Assert.assertThat(jsonPath.getString("places[0].state"), Is.is(expectedSTate));
    }

    @And("place name should be {string}")
    public void placeNameShouldBe(String expectedPlaceName) {
        Assert.assertThat(jsonPath.getString("places[0].'place name'"), Is.is(expectedPlaceName));
    }

    @And("latitude should be {double}")
    public void latitudeShouldBe(double expectedLatitude) {
        Assert.assertThat(jsonPath.getDouble("places[0].latitude"), Is.is( expectedLatitude));
    }

    @And("longitude should be {double}")
    public void longitudeShouldBe(double expectedLongitude) {
        Assert.assertThat(jsonPath.getDouble("places[0].longitude"), Is.is(expectedLongitude));
    }
}
