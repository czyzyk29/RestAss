package newFramework.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import newFramework.client.ExecutableRequest;
import org.example.Student;
import org.example.StudentDto;

import static io.restassured.RestAssured.given;

public class PostStudent implements ExecutableRequest {

    private final RequestSpecBuilder requestSpecBuilder;

    public PostStudent(Student student, RequestSpecBuilder requestSpecBuilder){
        this.requestSpecBuilder = requestSpecBuilder;
        this.requestSpecBuilder.setContentType(ContentType.JSON);
        this.requestSpecBuilder.setBody(student);
    }

    @Override
    public Response execute() {
        return given()
                .spec(requestSpecBuilder.build())
                .when()
                .post("/api/studentsDetails");
    }

    public StudentDto saveAsDto(){
        return execute().then().extract().as(StudentDto.class);
    }
}
