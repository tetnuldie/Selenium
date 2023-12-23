package org.example.api;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.example.requestDto.RequestDto;
import org.example.responseObject.ResponseObject;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTest {
    @BeforeMethod
    public void init() {
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
    }

    @Test
    public void statusCodeTest() {
        RequestDto request = new RequestDto();
        request.setEndpoint("/posts");
        request.setHeader("Accept", "*/*");
        request.setHeader("Content-Type", "application/json");


        executeGetRequest(request)
                .then().statusCode(200);
    }

    @Test
    public void responseHeadersTest() {
        RequestDto request = new RequestDto();
        request.setEndpoint("/posts");
        request.setHeader("Accept", "*/*");
        request.setHeader("Content-Type", "application/json");


        executeGetRequest(request)
                .then()
                .statusCode(200)
                .assertThat()
                .header("Content-Type", Matchers.equalTo("application/json; charset=utf-8"));
    }

    @Test
    public void responseBodyTest() {
        RequestDto request = new RequestDto();
        request.setEndpoint("/posts");
        request.setHeader("Accept", "*/*");
        request.setHeader("Content-Type", "application/json");

        List<ResponseObject> responseObjectList =
                executeGetRequest(request)
                        .then()
                        .statusCode(200)
                        .assertThat()
                        .extract()
                        .as(new TypeRef<>() {
                        });

        Assert.assertEquals(responseObjectList.size(), 100);
    }

    @Test
    public void postRequestTest() {
        RequestDto request = new RequestDto();
        request.setEndpoint("/posts");
        request.setHeader("Accept", "*/*");
        request.setHeader("Content-Type", "application/json");
        request.setBody("{\"title\":\"foo\",\"body\":\"bar\",\"userId\":\"1\"}");

        ResponseObject response = executePostRequest(request)
                .then()
                .statusCode(201)
                .extract().as(new TypeRef<>() {
                });

        Assert.assertNotNull(response);
        System.out.println("Id = "+response.getId());
    }

    @Test
    public void putRequestTest(){
        RequestDto request = new RequestDto();
        request.setEndpoint("/posts/1");
        request.setHeader("Accept", "*/*");
        request.setHeader("Content-Type", "application/json");
        request.setBody("{\"id\":\"1\",\"title\":\"foo\",\"body\":\"bar\",\"userId\":\"1\"}");

        executePutRequest(request)
                .then()
                .statusCode(200)
                .extract()
                .asString()
                .contains("\"id\": 1");
    }

    @Test
    public void deleteRequestTest(){
        RequestDto request = new RequestDto();
        request.setEndpoint("/posts/1");
        request.setHeader("Accept", "*/*");
        request.setHeader("Content-Type", "application/json");
        request.setBody("");

        executePutRequest(request)
                .then()
                .statusCode(200);
    }

    public static Response executeGetRequest(RequestDto requestDto) {
        return given()
                .contentType(requestDto.getHeader("Content-Type"))
                .accept(requestDto.getHeader("Accept"))
                .when()
                .get(requestDto.getEndpoint());
    }

    public static Response executePostRequest(RequestDto requestDto) {
        return given()
                .contentType(requestDto.getHeader("Content-Type"))
                .accept(requestDto.getHeader("Accept"))
                .body(requestDto.getBody())
                .when()
                .post(requestDto.getEndpoint());
    }

    public static Response executePutRequest(RequestDto requestDto) {
        return given()
                .contentType(requestDto.getHeader("Content-Type"))
                .accept(requestDto.getHeader("Accept"))
                .body(requestDto.getBody())
                .when()
                .put(requestDto.getEndpoint());
    }

    public static Response executeDeleteRequest(RequestDto requestDto) {
        return given()
                .contentType(requestDto.getHeader("Content-Type"))
                .accept(requestDto.getHeader("Accept"))
                .body(requestDto.getBody())
                .when()
                .delete(requestDto.getEndpoint());
    }
}
