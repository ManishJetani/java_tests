package pets;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PetsTest {
    @BeforeClass
    public static void Setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/";
    }

    @Test
    public void getStoreInventory() {
        Response response = given()
                .when()
                .get("store/inventory")
                .then()
                .assertThat()
                .extract()
                .response();

        response.prettyPrint();
        Assert.assertThat(response.getStatusCode(), Is.is(200));
    }

    @Test
    public void getOrdersInventory() {
        Response response = given()
                .when()
                .get("store/order/1")
                .then()
                .assertThat()
                .extract()
                .response();

        response.prettyPrint();
        Assert.assertThat(response.getStatusCode(), Is.is(200));
    }
}
