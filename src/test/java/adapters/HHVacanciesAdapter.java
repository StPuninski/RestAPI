package adapters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.hh.Vacancies;

import static io.restassured.RestAssured.given;

public class HHVacanciesAdapter extends MainAdapter {

    public Vacancies get(String text) {

        Response response = given()
                .log().uri()
        .when()
                .get("https://api.hh.ru/vacancies?text={text}", text )

        .then()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response();

        return gson.fromJson(response.asString().trim(), Vacancies.class);

    }
}
