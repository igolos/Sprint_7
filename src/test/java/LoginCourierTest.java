import clients.Courier;
import clients.CourierClient;
import clients.Credentials;
import clients.Generator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginCourierTest {
    private CourierClient courierClient;
    private Courier courier;
    private Credentials credentials;
    private int id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = Generator.getDefault();

    }

    @Test
    @DisplayName("Test for checking the status code and message with courier login")
    @Description("Positive test")
    public void checkCodeAndMessageForLogin_Success() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        int isCourierLogin = responseLogin.extract().path("id");
        int statusCode = responseLogin.extract().statusCode();
        assertEquals("Login failed", id, isCourierLogin);
        assertEquals("StatusCode incorrect", 200, statusCode);

    }
    @After
    public void cleanUp(){
        courierClient.delete(id);
    }
}