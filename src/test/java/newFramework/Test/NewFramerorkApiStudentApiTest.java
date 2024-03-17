package newFramework.Test;

import newFramework.Base.NewFrameworkTestBase;
import newFramework.client.ApiClient;
import newFramework.factories.StudentFactory;
import org.apache.hc.core5.http.HttpStatus;
import org.example.Student;
import org.example.StudentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NewFramerorkApiStudentApiTest extends NewFrameworkTestBase {

    private final String studentBaseUri = "https://thetestingworldapi.com";
    private ApiClient i_want;

    private StudentFactory studentFactory;

    @BeforeEach()
    public void setUpClient(){
        i_want = createApiClient(studentBaseUri);
        studentFactory = new StudentFactory();
    }

    @Test
    public void shouldGetStudent(){
        //10093747
        assertThat(i_want.seeDetailsOfNewStudent("10093747")
                .execute()
                .getStatusCode())
                .isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    public void shouldSaveNewStudent(){

        Student requestedStudent = studentFactory.createRegularStudent();
        //Student customStudent = studentFactory.createCustomizedStudent("brian","adams","adam","01/01/2000");
        StudentDto actualStudent = i_want.saveRealStudent(requestedStudent)
                .saveAsDto();

        assertThat(actualStudent).usingRecursiveComparison()    //compare savereq with response
                .ignoringFieldsOfTypes(Integer.class)   //ignoring integer from DTo's object
                .isEqualTo(requestedStudent);

//        assertThat(actualStudent).usingRecursiveComparison()    //compare req with response
//                .ignoringFieldsOfTypes(Integer.class)   //ignoring integer from DTo's object
//                .isEqualTo(customStudent);
//        assertThat(actualStudent.getId()).isNotZero();    //first check
//        assertThat(actualStudent.getMiddle_name().length()).isNotZero();  //first check

    }
}
