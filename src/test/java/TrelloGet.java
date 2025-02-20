import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import config.TrelloCredentials;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class TrelloGet  {

    public static String baseUri = "https://api.trello.com/1/boards/";
    public static String apiKey = TrelloCredentials.getApiKey();
    public static String token = TrelloCredentials.getToken();
    public static String boardId = "2zPgWRgQ";


    public static void main(String[] args) {
        RestAssured.baseURI = baseUri;

        given().
                param("key", apiKey)
                .param("token", token)
                .when()
                .get(boardId)
                .then()
                .assertThat().statusCode(200).and()
                .contentType(ContentType.JSON)
                .and()
                //.body("desc", equalTo("This is for testing purposes"));
                .log().all();




    }
}
