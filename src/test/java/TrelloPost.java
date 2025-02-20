import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class TrelloPost {
    private String board_id;

    @Test
    public void createBoard() {
        String board_name = "My Board From API" + System.currentTimeMillis();
        RestAssured.baseURI = "https://api.trello.com";

        Response response = given().queryParam("key", TrelloGet.apiKey)
                .queryParam("token", TrelloGet.token)
                .queryParam("name", board_name)
                .header("Content-Type", "application/json")
                .when()
                .post("1/boards")
                .then().assertThat()
                .statusCode(200).and()
                .contentType(ContentType.JSON)
                .and()
                .body("name", equalTo(board_name))
                .extract().response();

        board_id = response.jsonPath().getString("id");
        System.out.println(board_id);

    }
    @AfterMethod
    public void deleteBoard() {
        RestAssured.baseURI = "https://api.trello.com";
        given().queryParam("key", TrelloGet.apiKey)
                .queryParam("token", TrelloGet.token)
                .when()
                .delete("1/boards/" + board_id)
                .then().assertThat()
                .statusCode(200);
    }

}
