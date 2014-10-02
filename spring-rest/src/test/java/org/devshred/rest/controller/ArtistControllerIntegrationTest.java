package org.devshred.rest.controller;

import org.hamcrest.Matchers;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.post;
import static org.hamcrest.Matchers.is;

public class ArtistControllerIntegrationTest {
    private static final String URL_ALL = "/spring-rest/artist/";
    private static final String URL_KEITH = "/spring-rest/artist/Keith";

    @Test
    public void createArtist() throws Exception {
        get(URL_KEITH).then().assertThat().statusCode(404);
        post(URL_KEITH).then().assertThat().statusCode(201);
        get(URL_KEITH).then().assertThat().statusCode(200);
    }
}