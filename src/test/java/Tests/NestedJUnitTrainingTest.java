package Tests;
import org.junit.jupiter.api.*;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@DisplayName("Student service is up and running")
public class NestedJUnitTrainingTest {

    @Test
    @DisplayName("When student object is created")
    public void createNewStudent(){
        System.out.println("Student object is created");
    }

    @Nested
    @Order(1)
    @DisplayName("I can performe")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class GetStudentTest{

        @Order(1)
        @Test
        @DisplayName("Get student details")
        void getStudentId(){
            System.out.println("Student verified");
        }

        @Order(2)
        @Test
        @DisplayName("Update student details")
        void updateStudentId(){
            System.out.println("Student updated");
        }
    }

    @Nested
    @Order(2)
    @DisplayName("I can persist data")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class PersistDataTest{

        @Order(1)
        @Test
        @DisplayName("In postgress DB resist ")
        void saveStudentToPostgress(){
            System.out.println("Student is persisted in postgress");
        }

        @Order(2)
        @Test
        @DisplayName("In kafka")
        void saveStudentToKafka(){
            System.out.println("Student is persisted in kafka");
        }

    }

    @Nested
    @Order(3)
    @DisplayName("I can run university service")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class UniversityServiceTest{

        @Order(1)
        @Test
        @DisplayName("And present data in university dashboard")
        void shouldPresentStudentInDasboard(){
            System.out.println("Student is present in dasboard");
        }

        @Order(2)
        @Test
        @DisplayName("And send object to downstream")
        void shouldSentToObjectDownstream(){
            System.out.println("Student has ben sent to downstream");
        }

        @Order(3)
        @Test
        @DisplayName("And delete student obejct")
        void shouldDeleteStudent(){
            System.out.println("Student is deleted");
        }

    }
}
