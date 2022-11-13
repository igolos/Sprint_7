import clients.pojo.Courier;
import clients.CourierClient;
import clients.Generator;
import clients.pojo.Credentials;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;

@RunWith(Parameterized.class)
public class NegativeParametrizedCreationCourierTest {
    private CourierClient courierClient;

    private Courier courier;
    private int statusCode;
    private String message;
    private int id;

    public NegativeParametrizedCreationCourierTest(Courier courier, int statusCode, String message) {
        this.courier = courier;
        this.statusCode = statusCode;
        this.message = message;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData()
    {
        return new Object[][]
                {
                        {Generator.getWithFirstNameOnly(), SC_BAD_REQUEST,"Недостаточно данных для создания учетной записи"},
                        {Generator.getWithLoginOnly(), SC_BAD_REQUEST,"Недостаточно данных для создания учетной записи"},
                        {Generator.getWithPasswordsOnly(), SC_BAD_REQUEST,"Недостаточно данных для создания учетной записи"},
                        {Generator.getWithoutCredentials(), SC_BAD_REQUEST,"Недостаточно данных для создания учетной записи"},

                };
    }
    @Before
    public void setUp(){
        courierClient = new CourierClient();
    }
    @Test
    @DisplayName("Test to create courier with the different null params")
    @Description("Negative test")
    public void createCourierWithNullParams(){
        ValidatableResponse responseCreate = courierClient.create(courier);
        String actualMessage = responseCreate.extract().path("message");
        int actualStatusCode = responseCreate.extract().statusCode();


        assertEquals(statusCode,actualStatusCode);
        assertEquals(message,actualMessage);

    }
//    @After
//    public void cleanUp(){
//        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
//        id = responseLogin.extract().path("id");
//        courierClient.delete(id);
//    }
}
