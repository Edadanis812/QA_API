package post_http_request_method;
import Base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
public class Post01 extends HerokuAppBaseUrl {
    /*
       When
           I send POST Request to the Url https://restful-booker.herokuapp.com/booking
           with the request body {
                                   "firstname": "Murat",
                                   "lastname": "Altyyev",
                                   "totalprice": 11111,
                                   "depositpaid": true,
                                   "bookingdates": {
                                       "checkin": "2021-09-09",
                                       "checkout": "2021-09-21"
                                    }
                                 }
       Then
           Status code is 200
           And response body should be like {
                                               "bookingid": 11,
                                               "booking": {
                                                   "firstname": "Murat",
                                                   "lastname": "Altyyev",
                                                   "totalprice": 11111,
                                                   "depositpaid": true,
                                                   "bookingdates": {
                                                       "checkin": "2021-09-09",
                                                       "checkout": "2021-09-21"
                                                   }
                                               }
                                            }
    */
    @Test
    public void post01(){
        //Set the base url
        spec.pathParam("first", "booking");
        //Set the expected data
            //INNER
        Map<String, Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2021-09-09");
        bookingdates.put("checkout","2021-09-21");
            //OUTER
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname","Murat");
        expectedData.put("lastname", "Altyyev");
        expectedData.put("totalprice", 11111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingdates );
        //Send the Post request and get the response
            //We need body (FOR POST REQUEST, WE NEED BODY)
            //For body, we are using outer data
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        //response.prettyPrint();

        //Do the validation
            //When we use map we are getting info inside the map.It is converting as object
        Map<String,Object> actualData=response.as(HashMap.class);
        //Let's compare them actual and expected data
        //We need to mention (Map) to be clear data type(casting experience)
        assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));

        System.out.println(((Map)actualData.get("booking")).get("firstname"));

        //Example:key=string / value=object(DATA CASTING)
        Map<String,Object> data1= new HashMap<>();
        data1.put("name","Aida");
        data1.put("id",11);
        System.out.println(data1.get("name"));
                //DATA CASTING
        Object object;
        String name= (String) data1.get("name");
    }


}
