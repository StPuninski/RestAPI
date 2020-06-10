package tests;

import adapters.UsersAdapter;
import groovyjarjarpicocli.CommandLine;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.JobUser;
import models.UsersList;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.testng.Assert.assertEquals;

public class ReqresTests extends BaseTest{

    @Test
    public void testList() throws FileNotFoundException {
        UsersList expectedList;
        expectedList = gson.fromJson(new FileReader("src/test/java/resources/expectedList.json"), UsersList.class);

        UsersList list = new UsersAdapter().get(1);
        assertEquals(list, expectedList);
    }
    
    @Test
    public void createUser() {
        JobUser user = new UsersAdapter().post(new JobUser("name", "job", "", ""));
        System.out.println(user);
    }

    @Test
    public void getUser() {
        String expected = "{\"data\":{\"id\":2,\"email\":\"janet.weaver@reqres.in\",\"first_name\":\"Janet\",\"last_name\":\"Weaver\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg\"}," +
                "\"ad\":{\"company\":\"StatusCode Weekly\",\"url\":\"http://statuscode.org/\",\"text\":\"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things.\"}}";
//          https://reqres.in/api/users/2
        Response response = when()
                .get("https://reqres.in/api/users/2")
        .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response();
        assertEquals(expected, response.asString().trim());
    }


// HomeMade
    @Test
    public void GetListUsers(){
        String expected = "{\"page\":2,\"per_page\":6,\"total\":12,\"total_pages\":2,\"data\":[{\"id\":7,\"email\":\"michael.lawson@reqres.in\",\"first_name\":\"Michael\",\"last_name\":\"Lawson\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg\"},{\"id\":8,\"email\":\"lindsay.ferguson@reqres.in\",\"first_name\":\"Lindsay\",\"last_name\":\"Ferguson\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/araa3185/128.jpg\"},{\"id\":9,\"email\":\"tobias.funke@reqres.in\",\"first_name\":\"Tobias\",\"last_name\":\"Funke\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/vivekprvr/128.jpg\"},{\"id\":10,\"email\":\"byron.fields@reqres.in\",\"first_name\":\"Byron\",\"last_name\":\"Fields\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/russoedu/128.jpg\"},{\"id\":11,\"email\":\"george.edwards@reqres.in\",\"first_name\":\"George\",\"last_name\":\"Edwards\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/mrmoiree/128.jpg\"},{\"id\":12,\"email\":\"rachel.howell@reqres.in\",\"first_name\":\"Rachel\",\"last_name\":\"Howell\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/hebertialmeida/128.jpg\"}],\"ad\":{\"company\":\"StatusCode Weekly\",\"url\":\"http://statuscode.org/\",\"text\":\"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things.\"}}";
//        https://reqres.in/api/users?page=2
        Response response = when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response();
        assertEquals(expected, response.asString().trim());
    }




    @Test
    public void GetSingleUserNotFound() {
        String expected = "{}";
//        https://reqres.in/api/users/23
        Response response = when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().all()
                .statusCode(404)
                .contentType(ContentType.JSON).extract().response();
        assertEquals(expected, response.asString().trim());
    }

    @Test
    public void GetListResource(){
        String expected = "{\"page\":1,\"per_page\":6,\"total\":12,\"total_pages\":2,\"data\":[{\"id\":1,\"name\":\"cerulean\",\"year\":2000,\"color\":\"#98B2D1\",\"pantone_value\":\"15-4020\"},{\"id\":2,\"name\":\"fuchsia rose\",\"year\":2001,\"color\":\"#C74375\",\"pantone_value\":\"17-2031\"},{\"id\":3,\"name\":\"true red\",\"year\":2002,\"color\":\"#BF1932\",\"pantone_value\":\"19-1664\"},{\"id\":4,\"name\":\"aqua sky\",\"year\":2003,\"color\":\"#7BC4C4\",\"pantone_value\":\"14-4811\"},{\"id\":5,\"name\":\"tigerlily\",\"year\":2004,\"color\":\"#E2583E\",\"pantone_value\":\"17-1456\"},{\"id\":6,\"name\":\"blue turquoise\",\"year\":2005,\"color\":\"#53B0AE\",\"pantone_value\":\"15-5217\"}],\"ad\":{\"company\":\"StatusCode Weekly\",\"url\":\"http://statuscode.org/\",\"text\":\"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things.\"}}";
//        https://reqres.in/api/unknown
        Response response = when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response();
        assertEquals(expected, response.asString().trim());
    }

}

