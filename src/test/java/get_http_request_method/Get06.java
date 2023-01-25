package get_http_request_method;
import Base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
public class Get06 extends DummyApiBaseUrl {
    /*
     When
       I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employees
       Then
           HTTP Status Code should be 200
       And
           Content Type should be JSON
       And
           Status Line should be HTTP/1.1 200 OK
       And
   User can see following employees in the system
   , Jenette Caldwell and Bradley Greer
   24 people in there
 */
    @Test
    public void get06(){
        //1. Set the base url
        spec.pathParams("first", "api", "second","v1", "third","employees");
        //Set the expected data
        //3. Send the Get request and get the response
        //We have to send out data in Gherkin, Put specification,Action key word, what is the request
        Response response = given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();
        //Validate the data
        response.then().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK").
                body("data.employee_name",hasItems("Doris Wilder","Jenette Caldwell","Bradley Greer")).
                body("data.id", hasSize(24));
    }
}