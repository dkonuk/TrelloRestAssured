import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class trelloDeleteBoard {
    private String boardId;

    @BeforeMethod
    public void boardIdGetter(){
        String shortBoardId = "2yClZY1q";
        RestAssured.baseURI = "https://api.trello.com";
        Response response = given().queryParam("key", TrelloGet.apiKey)
                .queryParam("token", TrelloGet.token)
                .when()
                .get("1/boards/" + shortBoardId)
                .then().assertThat()
                .statusCode(200).and()
                .contentType(ContentType.JSON)
                .and()
                .body("shortUrl", containsString(shortBoardId))
                .extract().response();
        boardId = response.jsonPath().getString("id");
    }

    @Test
    public void delete() {
        RestAssured.baseURI = "https://api.trello.com";
        given().queryParam("key", TrelloGet.apiKey)
                .queryParam("token", TrelloGet.token)
                .when()
                .delete("1/boards/" + boardId)
                .then().assertThat()
                .statusCode(200);

    }
}
