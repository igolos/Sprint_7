import clients.CourierClient;
import clients.Generator;
import clients.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;

public class GetOrdersTest {
    private OrderClient orderClient;
    private ArrayList<String> order;

    @Before
    public void setUp() {
        orderClient = new OrderClient();

    }

    @Test
    @DisplayName("Check status code for get/orders")
    public void getOrders() {
        ValidatableResponse responseGetOrders = orderClient.getOrders();
        int actualStatusCode = responseGetOrders.extract().statusCode();
        order = responseGetOrders.extract().path("orders");
        assertEquals("You can not get orders", 200, actualStatusCode);
      //  MatcherAssert.assertThat(order.size(), notNullValue());
//        MatcherAssert.assertThat(order.isEmpty());
        assertEquals(false, order.isEmpty());

    }
}
