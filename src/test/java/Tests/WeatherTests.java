package Tests;

import DataStore.DataStore;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class WeatherTests {


    private Cookies cookie;

    @BeforeEach
    public void setUp(){
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = "https://api.openweathermap.org";
        //RestAssured.basePath = "/data/2.5/weather";
        //RestAssured.port = 8810;
    }

    @Test
    public void shouldGetWeatherForLocation(){

        given()
                .param("appid","89a2ed8a594cc497a6273490e7ca59dd")
                .param("id","6695624")
                .log()
                .all()
                .when()
                .get("/data/2.5/forecast")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    //cookies
    @Test
    public void shouldGetWeatherForLocationWithCookies(){

        //before cookies
        cookie = shouldLoginToSystem();

        given()
                .cookies(cookie)
                .param("appid","89a2ed8a594cc497a6273490e7ca59dd")
                .param("id","6695624")
                .log()
                .all()
                .when()
                .get("/data/2.5/forecast")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    private Cookies shouldLoginToSystem() {

        String body = "payload";

        return RestAssured
                .given()
                .param("appid","89a2ed8a594cc497a6273490e7ca59dd")
                .param("id","6695624")
                .log()
                .all()
                .body(body)
                .when()
                .post("/api/test")
                .getDetailedCookies();
    }

    @Test
    public void shouldGetWeatherForLocationWithResponseValidation(){

        DataStore.windDegree = given()
                .param("appid", "89a2ed8a594cc497a6273490e7ca59dd")
                .param("q", "London,UK")
                .log()
                .all()
                .when()
                .get("https://samples.openweathermap.org/data/2.5/weather")
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract()
                .jsonPath()
                .get("wind.deg");

        System.out.println("WindDegree: " + DataStore.windDegree);

    }

    @Test
    public void shouldGetWeatherForLocationWithResponseValidationAddhock(){

        given()
                .param("appid", "89a2ed8a594cc497a6273490e7ca59dd")
                .param("q", "London,UK")
                .log()
                .all()
                .when()
                .get("https://samples.openweathermap.org/data/2.5/weather")
                .then()
                .statusCode(200)
                .log()
                .all()
                //.body("wind.speed", greaterThan(4.0f),"name",equalTo("London"))
                .body("wind.speed", greaterThan(4.0f))
                .body("name",equalTo("London"))
                .body("sys",hasEntry("country","GB"))
                .body("sys",not(empty()));

    }
}
