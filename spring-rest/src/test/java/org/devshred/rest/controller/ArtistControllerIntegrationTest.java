package org.devshred.rest.controller;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.post;

public class ArtistControllerIntegrationTest {
    private static final String URL_ALL = "/spring-rest/artist/";
    private static final String URL_KEITH = "/spring-rest/artist/Keith";

    @Test
    public void createAndDeleteArtist() throws Exception {
        get(URL_KEITH).then().assertThat().statusCode(404);
        post(URL_KEITH).then().assertThat().statusCode(201);
        get(URL_KEITH).then().assertThat().statusCode(200);
        delete(URL_KEITH).then().assertThat().statusCode(200);
        get(URL_KEITH).then().assertThat().statusCode(404);
    }
}