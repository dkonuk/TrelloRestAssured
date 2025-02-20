import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class verifyResponse {
    String baseUri = TrelloGet.baseUri;
        String apiKey = TrelloGet.apiKey;
        String token = TrelloGet.token;
        String boardId = TrelloGet.boardId;



    @Test
    public void VerifyResponseBody() {
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
                .body("desc", equalTo("This is for testing purposes"));
    }

    @Test
    public void VerifyResponseHeader() {
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
                .header("Connection", equalTo("keep-alive"));
    }




}
