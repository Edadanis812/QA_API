package get_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;

public class Get04 extends JsonPlaceHolderBaseUrl {
    /*
       Given
           https://jsonplaceholder.typicode.com/todos
       When
       I send a GET request to the Url
    And
        Accept type is "application/json"
    Then
        HTTP Status Code should be 200
    And
        Response format should be "application/json"
    And
        There should be 200 todos
    And
        "quis eius est sint explicabo" should be one of the todos
    And
        2, 7, and 9 should be among the userIds
    */
    @Test
    public void get03() {
        //1. Set the base url
        spec.pathParam("first", "todos"); // DO NOT FORGET THE EXTEND
        //2. set the expected data
        //3. send the Get request and get the response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();
        //Validate the data--> validation keyword then()
        //There should be 200 todos
        response.then().statusCode(200).contentType(ContentType.JSON).
                body("title",hasItem("quis eius est sint explicabo")).
                body("id",hasSize(200)). //There should be 200 todos
                body("userId", hasItems(2,7,9));
        //There are many title. We can not use equalTo.If your item exists among the all item.
        //That is why we can use "hasItem"
        //HasSize gives us last one
        //When we try 199 and 201, test is going to fail. Because our size is 200.
        //When we check the console, we can see as a manually 2, 7, and 9 should be among the userIds

        //Second Way:
        response.then().statusCode(200).contentType(ContentType.JSON).
        body("title", hasItem("quis eius est sint explicabo"),"id",hasSize(200),"userId", hasItems(2,7,9));



    }
}
