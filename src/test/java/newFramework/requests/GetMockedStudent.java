package newFramework.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import newFramework.client.ExecutableRequest;

import static io.restassured.RestAssured.given;

public class GetMockedStudent implements ExecutableRequest {

    private final RequestSpecBuilder requestSpecBuilder;

    public GetMockedStudent(String name, RequestSpecBuilder requestSpecBuilder){
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.setContentType(ContentType.JSON);
        this.requestSpecBuilder.addPathParam("first_name",name);
    }

    @Override
    public Response execute() {
        return given()
                .spec(requestSpecBuilder.build())
                .when()
                .get("/api/studentsDetails/{first_name}");
    }
}
