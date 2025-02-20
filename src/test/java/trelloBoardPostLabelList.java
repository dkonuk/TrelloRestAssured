import config.TrelloCredentials;
import files.trelloBoard;
import files.trelloLabelOnBoard;
import files.trelloListOnBoard;
import config.TrelloConfig;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.Random;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;



public class trelloBoardPostLabelList {
    private static final String BASE_URL = "https://api.trello.com";
    private static final String API_KEY = TrelloCredentials.getApiKey();
    private static final String TOKEN = TrelloCredentials.getToken();
    private String board_id;

    @Test
    public void createBoard() {
        baseURI = TrelloConfig.getBaseUrl();
        String contentType = ContentType.JSON.toString();

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");

        Random random = new Random();
        int boardNumber = random.nextInt(100);

        trelloBoard newBoard;
        String name = "Board Created Using API" + boardNumber;
        String desc = "This board is created using RESTAssured Serialization";
        String prefs_background = "purple";

        newBoard = trelloBoard.builder()
                .name(name)
                .desc(desc)
                .prefs_background(prefs_background)
                .build();

        Response createBoardResponse = RestAssured.given()
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .contentType(contentType)
                .body(newBoard)
                .when()
                .post("/1/boards")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        board_id = createBoardResponse.jsonPath().getString("id");
    }

    @Test(dependsOnMethods = {"createBoard"})
    public void addLabeltoBoard() {
        RestAssured.baseURI = BASE_URL;
        String contentType = ContentType.JSON.toString();

        trelloLabelOnBoard newLabel;
        String name = "Label Created Using API";
        String color = "blue";

        newLabel = trelloLabelOnBoard.builder()
                .idBoard(board_id)
                .name(name)
                .color(color)
                .build();
        Response createLabelOnBoardResponse = RestAssured.given()
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .contentType(contentType)
                .body(newLabel)
                .when()
                .post("/1/labels")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all()
                .extract().response();

    }

    @Test(dependsOnMethods = {"addLabeltoBoard"})
    public void addListtoBoard() {
        RestAssured.baseURI = BASE_URL;
        String contentType = ContentType.JSON.toString();

        trelloListOnBoard newList;
        String name = "List Created Using API";
        String pos = "bottom";

        newList = trelloListOnBoard.builder()
                .idBoard(board_id)
                .name(name)
                .pos(pos)
                .build();

        Response createListOnBoardResponse = RestAssured.given()
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .contentType(contentType)
                .body(newList)
                .when()
                .post("/1/lists")
                .then()
                .assertThat()
                .statusCode(200)
                //.log().all()
                .extract().response();

    }
    @AfterClass(enabled = true)
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
