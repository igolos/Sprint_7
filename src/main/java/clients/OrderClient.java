package clients;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client{
    private static final String PATH_ORDER = "/api/v1/orders";
    private static final String PATH_ORDER_CANCEL = "/api/v1/orders/cancel?track=";


    public ValidatableResponse createOrder (Order order){
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(PATH_ORDER)
                .then();
    }
    public ValidatableResponse cancelOrder (int orderId){
        return given()
                .spec(getSpec())
                .when()
                .put(PATH_ORDER_CANCEL+orderId)
                .then();
    }
    public ValidatableResponse getOrders (){
        return given()
                .spec(getSpec())
                .when()
                .get(PATH_ORDER)
                .then();
    }
}
