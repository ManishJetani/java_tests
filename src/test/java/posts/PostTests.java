package posts;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostTests {

    @BeforeClass
    public static void Setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void getPostById() {
        Response response = given()
                .when()
                .get("posts/1")
                .then()
                .assertThat()
                .extract()
                .response();

        response.prettyPrint();
        Assert.assertThat(response.getStatusCode(), Is.is(200));
    }
}