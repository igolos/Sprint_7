import clients.*;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private OrderClient orderClient;
    private Order order;
    private int statusCode;
  //  private String message;
    private int orderId;

    public CreateOrderTest(Order order, int statusCode) {
        this.order = order;
        this.statusCode = statusCode;
        //  this.message = message;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]
                {
                        {OrderGenerator.getWithBlackColour(), SC_CREATED},
                        {OrderGenerator.getWithGreyColour(), SC_CREATED},
                        {OrderGenerator.getWithNullColour(), SC_CREATED},
                        {OrderGenerator.getWithTwoColour(), SC_CREATED},

                };
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Test for create/order")
    @Description("Test with the different data of color")
    public void orderCanBeCreated() {
        ValidatableResponse responseCreate = orderClient.createOrder(order);
        int actualStatusCode = responseCreate.extract().statusCode();
        assertEquals(statusCode, actualStatusCode);
        orderId = responseCreate.extract().path("track");
    }

    @After
    public void cleanUp() {
        orderClient.cancelOrder(orderId);
    }
}