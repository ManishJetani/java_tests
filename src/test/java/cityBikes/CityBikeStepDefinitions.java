package cityBikes;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class CityBikeStepDefinitions {

    RequestSpecification request;
    Response response;

    @Given("user is navigates to base url")
    public void userIsNavigatesToBaseUrl() {
        RestAssured.baseURI = "http://api.citybik.es/v2/networks";
        request = given();
    }

    @And("makes get request")
    public void makesGetRequest() {
        response = request.when().get();
    }

    @Then("see status code {int}")
    public void seeStatusCode(int expectedStatusCode) {
        Assert.assertThat(response.statusCode(), Is.is(expectedStatusCode));
    }

    @And("query with city {string}")
    public void queryWithCity(String cityId) {
        response = request.basePath(cityId)
                .when()
                .get()
                .then()
                .extract()
                .response();

    }

    @Then("receive country {string} latitude <{double}> and longitude <{double}>")
    public void receive_country_latitude_and_longitude(String expectedCountry, Double expectedLatitude, Double expectedLongitude) {

        Assert.assertThat(response.getStatusCode(), Is.is(200));

        JsonPath jsonPath = response.getBody().jsonPath();
        //verify country of frankfurt is Germany
        Assert.assertThat(jsonPath.get("network.location.country"), Is.is(expectedCountry));
        //verify latitude
        Assert.assertThat(jsonPath.get("network.location.latitude"), Is.is(expectedLatitude.floatValue()));
        //verify longitude
        Assert.assertThat(jsonPath.get("network.location.longitude"), Is.is(expectedLongitude.floatValue()));
    }

    @And("applys filter for field {string} and {string}")
    public void applysFilterForFieldAnd(String arg0, String arg1) {
        response = request.queryParam("fields", String.format("%s,%s", arg0, arg1))
                    .when()
                    .get()
                    .then()
                    .extract()
                    .response();
    }

    @Then("should only see these fields in result")
    public void shouldOnlySeeTheseFieldsInResult() {
//        response.getBody().prettyPrint();
        Assert.assertThat(response.getBody().jsonPath().get("networks[1]").toString(), Is.is("{href=/v2/networks/bycyklen, id=bycyklen}"));
    }
}
