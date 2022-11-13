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

public class CreateCourierTest {
    private CourierClient courierClient;
    private Courier courier;
    private int id;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = Generator.getDefault();

    }

    @Test
    @DisplayName("Check status code and message for create/courier")
    @Description("Basic test for one courier creation")
    public void courierCanBeCreated_True() {
      ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
        boolean isCourierCreated = responseCreate.extract().path("ok");
        int statusCode = responseCreate.extract().statusCode();
        assertEquals("Courier created is not ok", true, isCourierCreated);
        assertEquals("Courier created status code uncorrected", 201, statusCode);

    }

    @Test
    @DisplayName("Check status code and message for create/2 couriers with the same data")
    @Description("Test for 2 couriers with the same data")
    public void createTwoCouriersWithTheSameLogin_Failed() {
       ValidatableResponse responseCreate = courierClient.create(courier);
        ValidatableResponse responseCreateForSecondCourier = courierClient.create(courier);
        String actualMessage = responseCreateForSecondCourier.extract().path("message");
        int actualStatusCode = responseCreateForSecondCourier.extract().statusCode();
        assertEquals("Second courier created.This is bug", "Этот логин уже используется. Попробуйте другой.", actualMessage);
        assertEquals("Courier created, status code 200.", 409, actualStatusCode);

        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        id = responseLogin.extract().path("id");
    }
@After
    public void cleanUp(){
    courierClient.delete(id);
}

}
