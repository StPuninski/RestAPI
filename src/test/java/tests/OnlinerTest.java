package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class OnlinerTest {

    @Test

    public void validateSignIn() {

        //https://r.onliner.by/sdapi/user.api/login
        //{"login":"","password":""}

        given()
                  .body("{\"login\":\"\",\"password\":\"\"}")
                .header("Content-Type", "application/json")
                .log().all()

                .when()
                  .post("https://r.onliner.by/sdapi/user.api/login")
        .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].message", equalTo("Неверный логин или пароль"))
                .body("errors[0].key", equalTo("invalid_login_or_password"));


    }
}
