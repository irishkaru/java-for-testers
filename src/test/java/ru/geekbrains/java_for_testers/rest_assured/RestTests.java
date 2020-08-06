package ru.geekbrains.java_for_testers.rest_assured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class RestTests {
    RequestSpecification baseSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setBasePath("/rest_service/api/v1/")
            .setPort(8180)
            .build();


    @Test
    public void testItemTitle() {
        when()
                .get("http://localhost:8180/rest_service/api/v1/items")
                .then()
                .body("[1].title", equalTo("Square"));
    }

    @Test
    public void testGetItemsResponseCode() {
        when()
                .get("http://localhost:8180/rest_service/api/v1/items")
                .then()
                .statusCode(200);
    }

    @Test
    public void testWithPathParam() {
        given()
                .pathParam("id", 2)
                .when()
                .get("http://localhost:8180/rest_service/api/v1/items/{id}")
                .then()
//            .extract().response().body().print();
                .body("title", equalTo("Square"));
    }

    @Test
    public void testUserWithParam() {
        given()
                .pathParam("id", 1)
                .when()
                .get("http://localhost:8180/rest_service/api/v1/clients/{id}")
                .then()
                .body("items[0].title", equalTo("Box"));
    }

    @Test
    public void testAddItem() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
//            .header("content-type", "application/json")
//            .header("accept", "application/json")
                .body("{\"title\": \"Toy1\"}")
                .post("http://localhost:8180/rest_service/api/v1/items/")
                .then()
                .body("title", equalTo("Toy1"));
    }

    @Test
    public void testAddItemFromObject() {
        Item item = new Item();
        item.setTitle("JavaObject");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(item)
                .post("http://localhost:8180/rest_service/api/v1/items/")
                .then()
                .body("id", notNullValue());
    }

    @Test
    public void testWithRequestSpec() {
        given()
                .spec(baseSpec)
                .get("/items")
                .then()
                .extract().response().body().print();
    }


    @Test
    public void testWithParams() {
        String mathResult = given()
                .param("a", "39")
                .param("b", "3")
                .spec(baseSpec)
                .when()
                .get("/math")
                .then()
                .extract().response().body().asString();

        Assertions.assertEquals("42", mathResult);
    }

}
