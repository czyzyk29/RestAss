package newFramework.client;

import io.restassured.builder.RequestSpecBuilder;
import newFramework.requests.GetMockedStudent;
import newFramework.requests.GetStudent;
import newFramework.requests.PostMockedStudent;
import newFramework.requests.PostStudent;
import org.example.Student;

import java.util.function.Supplier;

public class ApiClient {

    private final Supplier<RequestSpecBuilder> requestSpecBuilderSupplier;

    public ApiClient(Supplier<RequestSpecBuilder> requestSpecBuilderSupplier) {
        this.requestSpecBuilderSupplier = requestSpecBuilderSupplier;
    }
    public GetStudent seeDetailsOfNewStudent(String studentId){
        return new GetStudent(studentId,requestSpecBuilderSupplier.get());
    }
    public PostStudent saveRealStudent(Student student){
        return new PostStudent(student,requestSpecBuilderSupplier.get());
    }
    public GetMockedStudent seeDetailsOfNewStudentMocked(String first_name){
        return new GetMockedStudent(first_name,requestSpecBuilderSupplier.get());
    }
    public PostMockedStudent saveRealStudentMocked(String first_name){
        return new PostMockedStudent(first_name, requestSpecBuilderSupplier.get());
    }

}
