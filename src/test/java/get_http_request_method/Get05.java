package get_http_request_method;
import Base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
public class Get05 extends HerokuAppBaseUrl {
    /*
          Given
              https://restful-booker.herokuapp.com/booking
          When
              User send a request to the URL
          Then
              Status code is 200
          And
             Among the data there should be someone whose firstname is "Jim" and lastname is "Jones"
*/
    @Test
    public void get05(){
        //1. Set the base url
        spec.pathParams("first", "booking").queryParams("firstname", "Mary",
                "lastname", "Smith");
        //2. set the expected data
        //3. Send the Get request and Get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();
        response.then().statusCode(200);
    }
}