import clients.pojo.Courier;
import clients.CourierClient;
import clients.pojo.Credentials;
import clients.Generator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NegativeParametrizedLoginCourierTest {
    private CourierClient courierClient;
    private Courier courier;
    // private int id;
    private Credentials credentials;
    private int statusCode;
    private String message;

    public NegativeParametrizedLoginCourierTest(Credentials credentials, int statusCode, String message) {
        this.credentials = credentials;
        this.statusCode = statusCode;
        this.message = message;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]
                {
                        {Generator.getLoginWithoutLogin(), SC_BAD_REQUEST, "Недостаточно данных для входа"},
                        // баг в системе  {Generator.getLoginWithoutPassword(), SC_BAD_REQUEST, "Недостаточно данных для входа"},
                        // баг в системе  {Generator.getLoginWithoutParams(), SC_BAD_REQUEST, "Недостаточно данных для входа"},
                        {Generator.getLoginWithIncorrectLogin(), SC_NOT_FOUND, "Учетная запись не найдена"},
                        {Generator.getLoginWithIncorrectPassword(), SC_NOT_FOUND, "Учетная запись не найдена"},
                        {Generator.getLoginWithoutIncorrectParams(), SC_NOT_FOUND, "Учетная запись не найдена"},


                };
    }

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = Generator.getDefault();
    }

    @Test
    @DisplayName("Test to login courier with null or incorrect data")
    @Description("Negative test")
    public void loginCourierWithIncorrectAndEmptyFields() {
        ValidatableResponse responseLogin = courierClient.login(credentials);
        String actualMessage = responseLogin.extract().path("message");
        int actualStatusCode = responseLogin.extract().statusCode();


        assertEquals(statusCode, actualStatusCode);
        assertEquals(message, actualMessage);
    }
}
