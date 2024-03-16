package Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RequestResponseSpecificationTests {

    @Test
    public void shouldGetWeatherForLocationWithResponseValidationA2(){
        //test use reqSpecification Spec
        given().spec(getRequestSpecification())
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


    @Test
    public void shouldGetWeatherForLocationWithResponseValidationAddhock(){

        //test use reqSpecification Spec
        given().spec(getRequestSpecification())
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

    private RequestSpecification getRequestSpecification(){
        return  given()
                .param("appid", "89a2ed8a594cc497a6273490e7ca59dd")
                .param("q", "London,UK")
                .log()
                .all();
    }

    private ResponseSpecification getResponseSpecification(){

        ResponseSpecification responseSpecification = RestAssured.expect();
        responseSpecification.log().all();
        responseSpecification.contentType(ContentType.JSON);
        responseSpecification.statusCode(200);
        responseSpecification.body("wind.speed", greaterThan(4.0f));
        responseSpecification.body("name",equalTo("London"));

        return responseSpecification;
    }


    @Test
    public void shouldGetWeatherForLocationWithResponseValidationWithResponseSpecificationRequestspecification(){
        //test use reqSpecification Spec

        //uzywamy obu req i resp
        given().spec(getRequestSpecification())
                .when()
                .get("https://samples.openweathermap.org/data/2.5/weather")
                .then()
                .log()
                .all()
                .spec(getResponseSpecification());

    }


}
