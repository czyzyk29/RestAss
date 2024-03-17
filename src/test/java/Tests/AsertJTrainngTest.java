package Tests;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class AsertJTrainngTest {

    @Test
    public void differentAssertionsTest(){
        int a = 33;
        int b = 44;
        assertThat(a).isEqualTo(44);

        //for objects
        //assertThat(a).usingRecursiveComparison().isEqualTo();
        //assertThat(a).usingRecursiveComparison().isNotEqualTo();

    }

    @Test
    public void differentAssertionsTestNext(){
        UUID a = UUID.randomUUID();
        UUID b = UUID.randomUUID();

        assertThat(a).usingRecursiveComparison()
                .ignoringCollectionOrder()
                .ignoringFieldsOfTypes(UUID.class)  //ignoring different fields like uuid and dates
                .isEqualTo(b);

    }

    @Test
    public void sosftAssert(){


        //field by field when us check all data in response
        SoftAssertions softAssertions = new SoftAssertions();
        UUID actual = UUID.randomUUID();
        UUID expect = UUID.randomUUID();

        softAssertions.assertThat(actual).as("desc").isEqualTo(expect);

        softAssertions.assertAll();

    }
}
