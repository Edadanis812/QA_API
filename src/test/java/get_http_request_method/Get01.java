package get_http_request_method;

import Base_urls.HerokuAppBaseUrl;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get01 extends HerokuAppBaseUrl {
//TASK:
    /*
        Given https://restful-booker.herokuapp.com/booking/3 (END POINT IN API)
        When user sends a GET request to the url
        Then HTTP status code should be 200
        And   content type should be Json
        And status line should be HTTP/1.1 200 OK
 */
    @Test
    public void get01(){// We are going to use JUnit
        //In API, we need to follow 4 steps:
        //1.Set the URL
        //Given in Gherkin language
        String endpoint="https://restful-booker.herokuapp.com/booking/9";
        // This is primitive way that we do not prefer!
        //2.Set the expected data
        //3.Send the request and Get the response
        //When in Gherkin language (action)

       Response response=given().when().get(endpoint); //(When we send it, we are going to get response.)
        response.prettyPrint();
        //4.Do the validation and assertion
        //Then in Gherkin language(validation)

        //ACTION: WHEN
        //RESPONSE: GET
    }
    @Test
    public  void test(){
        //1.Set the base URL
        //RequestSpecification spec=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();  //this is our empty container
        //We can use "RequestSpecification" as a reusable method under the "base url"
        //We carry this part to Base url. That is why we delete it. After we got the error. We fix it with extend the class.
        spec.pathParams("firstPar","booking","secPar",3); // There are 2 parameters.

        //2. Set the expected data

        //3.Send the Get request and get the response
        Response response=given().spec(spec).when().get("/{firstPar}/{secPar}"); // "/{}/{}" --> it means there is 2 parameters
        response.prettyPrint();
        //4.Do the validation
        response.then().statusCode(200).statusLine("HTTP/1.1 200 OK").contentType(ContentType.JSON);//contentType("application/json");
        System.out.println(response.headers()); //It is like postman
    }
}
