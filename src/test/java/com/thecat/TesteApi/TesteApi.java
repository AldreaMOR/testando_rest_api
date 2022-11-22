package com.thecat.TesteApi;

import io.restassured.response.Response;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TesteApi {
    String vote_id;

    @Test
    public void cadastro(){
        String url = "https://api.thecatapi.com/v1/user/passwordlesssignup";

        String corpo = "{\"email\": \"aldreaneves@yahoo.com.br\",\"appDescription\": \"Teste the cat api\"}";

        Response response =
                given().contentType("application/json")
                .body(corpo)
                .when().post(url);

        response.then().statusCode(200).body("message", containsString("SUCCESS"));
        System.out.println("Retorno => "+ response.body().asString());
    }

    @Test
    public void votacao(){
        String url = "https://api.thecatapi.com/v1/votes";

        String corpo = "{\"image_id\": \"6lh\",\"value\": \"true\",\"sub_id\": \"demo-c26f9f\"}";

        Response response =
                given().contentType("application/json")
                .body(corpo)
                .when().post(url);

        response.then().statusCode(200).body("message", containsString("SUCCESS"));
        System.out.println("Retorno => "+ response.body().asString());
        String id = response.jsonPath().getString("id");
        vote_id = id;
        System.out.println("Id => "+ id);
    }

    @Test
    public void deletaVotacao(){
        votacao();
        deletaVoto();
    }

    private void deletaVoto() {
        String url = "https://api.thecatapi.com/v1/votes/:{vote_id}";

        Response response =
                given()
                .contentType("application/json")
                .header("x-api-key","live_pMjs4bSpoNxmhj1xiCRgRYkzikouxC1xPOzZfzKEksr164yPtfU0pkk5im6ZmbRw")
                .pathParam("vote_id",vote_id)
                .when().delete(url);

        System.out.println("Retorno => "+ response.body().asString());
        response.then().statusCode(200).body("message", containsString("SUCCESS"));

    }
}
