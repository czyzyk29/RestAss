package newFramework.client;

import io.restassured.builder.RequestSpecBuilder;
import newFramework.requests.GetStudent;
import newFramework.requests.PostStudent;
import org.example.Student;

import java.util.function.Supplier;

public class ApiClient {

    private final Supplier<RequestSpecBuilder> requestSpecBuilderSupplier;

    public ApiClient(Supplier<RequestSpecBuilder> requestSpecBuilderSupplier) {
        this.requestSpecBuilderSupplier = requestSpecBuilderSupplier;
    }

    public GetStudent getRealStudent(String studentId){
        return new GetStudent(studentId,requestSpecBuilderSupplier.get());
    }
    public PostStudent postRealStudent(Student student){
        return new PostStudent(student,requestSpecBuilderSupplier.get());
    }
}
