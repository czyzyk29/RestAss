package Tests;

import configuration.ProviderRandomStringResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

public class JUnitTipsTest {

    @Test
    @ExtendWith(ProviderRandomStringResolver.class)
    public void shouldCreateRandomStudentName(String randomName, String randomAddress, String randomCity){
        String firstName = randomName;
        String addressName = randomAddress;
        String cityName = randomCity;

        System.out.println("Name: "+ firstName);
        System.out.println("Address: "+ addressName);
        System.out.println("City: "+ cityName);

    }



}
