package newFramework.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import newFramework.Base.NewFrameworkTestBase;
import newFramework.client.ApiClient;
import newFramework.factories.StudentFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.example.MockStudent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class NewFrameworkMockApiStudentApiTest extends NewFrameworkTestBase {

    private final String studentBaseUri = "https://thetestingworldapi.com";
    private final String basePathMocked = "/studentsDetails/.*";
    private final String basePathMockedPost = "/api/studentsDetails/";
    private final String basePathMockedGet = "/api/studentsDetails/.*";

    private ApiClient i_want;
    private StudentFactory studentFactory;

    @BeforeEach()
    public void setUpClient(){
        i_want = createApiClient(mock.baseUrl());
        studentFactory = new StudentFactory();
        mock.stubFor(post(urlPathMatching(basePathMockedPost))
                .willReturn(aResponse().withStatus(HttpStatus.SC_CREATED)));
    }

    @Test
    public void shouldGetNewMockedStudent() throws JsonProcessingException {

        String studentName = RandomStringUtils.random(8,true,false).toLowerCase();
        MockStudent mockStudent = new MockStudent(studentName);

        mock.stubFor(get(urlPathMatching(basePathMockedGet)).willReturn(aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", ContentType.JSON.toString())
                        .withBody(writer.writeValueAsString(mockStudent))));
                            //.withBody(readResposnse("/student.json") read z file responsa


        assertThat(i_want.seeDetailsOfNewStudentMocked(studentName)
                .execute().getStatusCode()).isEqualTo(HttpStatus.SC_OK);

    }

}
