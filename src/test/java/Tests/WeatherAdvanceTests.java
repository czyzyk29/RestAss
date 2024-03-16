package Tests;

import Base.BaseTest;
import Specification.WeatherRequest;
import Specification.WeatherResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class WeatherAdvanceTests extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/cities.csv")
    @Tag("regression")
    @Tag("OP-123")
    @DisplayName("Check response for seelcted city with weather")
    public void sholdGetWeatherByCity(String city, String country, int id){

        given().spec(WeatherRequest.getWeatherRequestSpecification(id))
                .when().get()
                .then().spec(WeatherResponse.getWeatherResponseSpecification(city, country, id));

    }
}
