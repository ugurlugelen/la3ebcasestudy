package api.rawg.util;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utilities.ConfigReader;

public class ApiUtil {

    public static RequestSpecification requestSpecification (){
        return new RequestSpecBuilder().
                setBaseUri(ConfigReader.getProperty("baseURI")).
                setAccept(ContentType.JSON).
                build();
    }

    public static ResponseSpecification responseSpecification(){

        return new ResponseSpecBuilder().expectStatusCode(200).
                    expectContentType(ContentType.JSON).build();
    }
}