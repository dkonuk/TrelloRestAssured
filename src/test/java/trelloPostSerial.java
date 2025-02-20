import io.restassured.RestAssured;
import files.trelloBoard;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class trelloPostSerial {
    private static final String BASE_URL = "https://api.trello.com";
    private static final String API_KEY = "518cdabe0cf8b08e8800b546ede0fa7d";
    private static final String TOKEN = "ATTA402d970cd4bf803e6ba5ba748e63cca13d0630d5bba48b4933adcb56aa1e46f25AD03E04";
    private String board_id;

    trelloBoard newBoard;
    String name = "My Board From API" + System.currentTimeMillis();
    String desc = "My Board Description";
    String prefs_background = "purple";

    @Test
    public void createBoard() {
        RestAssured.baseURI = BASE_URL;
        String contentType = ContentType.JSON.toString();


        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");

        trelloBoard newBoard = trelloBoard.builder()
                        .name(name)
                        .desc(desc)
                        .prefs_background(prefs_background)
                        .build();

        Response response = RestAssured.given()
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .contentType(contentType)
                .header("Content-Type", "application/json")
                .body(newBoard)
                .when()
                .post("/1/boards")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        board_id = response.jsonPath().getString("id");
        System.out.println(board_id);


    }@AfterMethod
    public void deleteBoard() {
        RestAssured.baseURI = "https://api.trello.com";
        given().queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .when()
                .delete("1/boards/" + board_id)
                .then().assertThat()
                .statusCode(200);
    }

}
