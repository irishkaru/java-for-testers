package ru.geekbrains.java_for_testers.rest_assured;

import org.junit.jupiter.api.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class RestAssuredTests {
    RequestSpecification baseSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setBasePath("/rest_service/api/v1/")
            .setPort(8180)
            .build();

    @ParameterizedTest
    @CsvSource({ "7, 2, 14", "10, 20, 200", "5, 5, 25" })
    void parameterizedTestMultiplicationOfNumbers(String a, String b, String rezult) {
        String mathResult = given()
                .param("a",a)
                .param("b", b)
                .spec(baseSpec)
                .when()
                .get("/math/multiply")
                .then()
                .extract().response().body().asString();
        Assertions.assertEquals(rezult, mathResult);
    }

    @ParameterizedTest
    @CsvSource({ "7, 2", "10, 20", "5, 5" })
    public void parameterizedTestGetResponseCode(String a, String b) {
        given()
                .param("a",a)
                .param("b", b)
                .spec(baseSpec)
                .when()
                .get("/math/multiply")
                .then()
                .statusCode(200);
    }


}
//ЗДЕСЬ ИЗМЕНЕНИЯ В MATHCONTROLLER
//package com.flamexander.demo.rest.controllers;
//
//        import org.springframework.stereotype.Controller;
//        import org.springframework.web.bind.annotation.GetMapping;
//        import org.springframework.web.bind.annotation.RequestMapping;
//        import org.springframework.web.bind.annotation.RequestParam;
//        import org.springframework.web.bind.annotation.RestController;
//
//@RestController // <- На вебинаре проблема была в том, что я прописывал @Controller вместо @RestController.
//@RequestMapping("/api/v1/math")
//public class MathController {
//
//    @GetMapping("/sum")
//    public String getSum(@RequestParam Integer a, @RequestParam Integer b) {
//        return String.valueOf(a + b);
//    }
//
//    @GetMapping("/multiply")
//    public String getMultiplication(@RequestParam Integer a, @RequestParam Integer b) {
//        return String.valueOf(a * b);
//    }
//
//}
