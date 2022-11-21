package com.thecat.TesteApi;

import org.junit.Test;
import static io.restassured.RestAssured.*;

public class TesteApi {

    @Test
    public void cadastro(){
        String url = "https://api.thecatapi.com/v1/user/passwordlesssignup";

        String corpo = "{\"email\": \"aldrea.mno@gmail.com\",\"appDescription\": \"Teste the cat api\"}";

        given().contentType("application/json").body(corpo).
        when().post(url).
        then().statusCode(200);
    }
}
