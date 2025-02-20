import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class gitHubApi {
    public static String repoName = "Api-Testing-Repo-With-RestAssured";

    @Test
    public void createRepository() {
        RestAssured.baseURI = "https://api.github.com";
        String bearerToken = "github_token";

        Response response = given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + bearerToken)
                .body("{\n" +
        "    \"name\": \"" + repoName + "\",\n" +
        "    \"description\": \"This repo is created to test api via Postman\",\n" +
        "    \"private\": false\n" +
        "}")
                .when()
                .post("/user/repos")
                .then().assertThat()
                .statusCode(201).and()
                .contentType(ContentType.JSON)
                .and()
                .body("name", equalTo(repoName))
                .extract().response();

        String jsonResponse = response.asString();

        JsonPath responseBody = new JsonPath(jsonResponse);
        System.out.println("Node Id : " + responseBody.get("node_id"));
        System.out.println("Repo Name : " + responseBody.get("name"));
    }
    @AfterMethod
    public void cleanUp() {
        RestAssured.baseURI = "https://api.github.com";
        String bearerToken = "github_token";
        given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .delete("/repos/" + "dkonuk" + "/" + repoName)
                .then().assertThat()
                .statusCode(204);

    }
}
