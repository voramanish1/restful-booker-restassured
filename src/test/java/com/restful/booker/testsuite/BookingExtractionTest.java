package com.restful.booker.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BookingExtractionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        response = given()
                .when()
                .get("/booking")
                .then().statusCode(200);
    }
    //Extract all bookingid
    @Test
    public void test001() {
        List<Integer> allIds = response.extract().path("bookingid");
        System.out.println("------------------Test start---------------------------");
        System.out.println("List of bookingIds are : " + allIds);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the all Firstnames
    @Test
    public void test002() {
        List<String> allnames = response.extract().path("lastname");
        System.out.println("------------------List of all names---------------------------");
        System.out.println("List of all names are : " + allnames);
        System.out.println("------------------End of Test---------------------------");

    }

    //Extract total numbers of records
    @Test
    public void test004() {
        int size=  response.extract().path("size()");
        System.out.println("------------------Test start---------------------------");
        System.out.println("List of bookingIds are : " + size);
        System.out.println("------------------End of Test---------------------------");
    }


    // 1. Verify the if the total record is 3790
    @Test
    public void test005() {
        response.body("size()", equalTo(3790));
    }
}
