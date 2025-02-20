
import io.restassured.RestAssured;
import files.payload;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {

    public static void main(String[] args) {

        //given all input details
        //when - Submit the API
        //Then - validate the response
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given()
                .log()
                .all()
                .queryParam("key", "qaclick123")
                .header("Content-Type","application/json")
                .body(payload.AddPlace())
                .when()
                .post("maps/api/place/add/json")
                .then()
                .assertThat().statusCode(200)
                //.body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)")
                .extract()
                .response()
                .asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");

        System.out.println(placeId);

    }
}

