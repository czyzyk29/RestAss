package newFramework.factories;

import org.example.MockStudent;
import org.example.Student;

public class StudentFactory {

    public Student createRegularStudent(){
        return new Student("Jone","Doe","Kowaliski","10/10/2000");
    }

    public Student createCustomizedStudent(String firstName, String middleName, String lastName, String dateOfBirth){
        return new Student(firstName,middleName,lastName,dateOfBirth);
    }

    public MockStudent getRandomStudent(String firstName){
        return new MockStudent(firstName);
    }
}
