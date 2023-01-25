package get_http_request_method;

import Base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get09 extends HerokuAppBaseUrl {
    /*
           When
               I send GET Request to https://restful-booker.herokuapp.com/booking/1
           Then
               Response body should be like that;
               {
                   "firstname": "Eric",
                   "lastname": "Jones",
                   "totalprice": 355,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2020-11-09",
                       "checkout": "2017-04-30"
                    }
               }
        */
    //In this structure ,  "firstname"(key): "Eric"(value),that is why we are using Map
    //In this case, it is nested json
    //Map is giving key value object in JAVA.
    //Object: "bookingdates": {
    //                    "checkin": "2020-11-09",
    //                    "checkout": "2017-04-30"
    //                 }
    //This is map value.
    //In java, we create inner object after we create outer object.
    //That is why we start from inner map
    @Test
    public void get09(){
        //1. Set the base url
        spec.pathParams("first", "booking", "second", 1);
        //Set the expected data
        Map<String, Object> bookingdates = new HashMap();
        bookingdates.put("checkin","2018-08-01" );
        bookingdates.put("checkout", "2020-10-10");
        Map<String, Object> expectedData = new HashMap();
        expectedData.put("firstname", "Eric");
        expectedData.put("lastname","Brown");
        expectedData.put("totalprice", 354);
        expectedData.put("depositpaid",false);
        expectedData.put("bookingdates",bookingdates);
        //3. Send the get request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        //do the validation
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("expected data map: "+expectedData);
        System.out.println("actual data map: "+actualData);







        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
    }

}
