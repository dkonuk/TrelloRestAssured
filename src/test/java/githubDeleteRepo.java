import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class  githubDeleteRepo {
    //String repoName = "test_automation_odev7";


    @Test
    public void delete() {
        RestAssured.baseURI = "https://api.github.com";
        String bearerToken = "github_token";
        given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + bearerToken)
                .when()
                .delete("/repos/" + "dkonuk" + "/" + gitHubApi.repoName)
                .then().assertThat()
                .statusCode(204);
    }
}
