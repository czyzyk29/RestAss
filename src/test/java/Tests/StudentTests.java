package Tests;

import DataStore.DataStore;
import DataStore.StudentResponse;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.example.Student;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

public class StudentTests {

    String requestBody = """
        {
            "first_name": "Michal",
            "middle_name": "empty",
            "last_name": "Czy",
            "date_of_birth": "01/03/1988"
        }
        """;

    Student student;

    @Test
    public void shouldPostNewStudent(){

        DataStore.studentId = given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(requestBody)
                .post()
                .then()
                .statusCode(201)
                .log()
                .all()
                .extract()
                .jsonPath()
                .get("id");

        given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .pathParam("studentId",DataStore.studentId)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .get("/{studentId}")
                .then()
                .statusCode(200)
                .log()
                .all();

    }

    @Test
    public void shouldGetNewStudent(){

        String studentId = "10093359";

        given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .pathParam("studentId",studentId)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .get("/{studentId}")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void shouldDeleteNewStudent(){

        String studentId = "10093359";

        given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .pathParam("studentId",studentId)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .delete("/{studentId}")
                .then()
                .statusCode(200)
                .log()
                .all();
    }


    @Test
    public void shouldPostNewStudentAsObject(){

        //specjalna klasa obiektów do budowania studenta

        student = new Student("michal","joann","czy","10/10/2010");


        Student student2 = Student.builder()
                .date_of_birth("21/12/2000")
                .first_name("marian")
                .last_name("lastname")
                .middle_name("middle")
                .build();

        given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .header("age","18")
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(student2)
                .post()
                .then()
                .statusCode(201)
                .log()
                .all();

    }

    @Test
    public void shouldGetNewStudentAndExtractAsTypeRef(){

        String studentId = "10093438";

        StudentResponse actualStudent = given()
                .baseUri("https://thetestingworldapi.com")
                .basePath("/api/studentsDetails")
                .pathParam("studentId",studentId)
                .contentType(ContentType.JSON)
                .log()
                .all()
                .get("/{studentId}")
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract()
                .as(new TypeRef<StudentResponse>(){});

        System.out.println("student" + actualStudent);

    }
}
