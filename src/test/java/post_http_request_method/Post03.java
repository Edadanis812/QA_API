package post_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Todo;

import java.awt.image.RescaleOp;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Post03 extends JsonPlaceHolderBaseUrl {
     /*
       When
             I send POST Request to the Url https://jsonplaceholder.typicode.com/todos
             with the request body {
                                   "userId": 55,
                                   "title": "Tidy your room",
                                   "completed": false
                                  }
       Then
           Status code is 201
           And response body is like {
                                       "userId": 55,
                                       "title": "Tidy your room",
                                       "completed": false,
                                       "id": 201
                                     }
                                     username: admin
                                     password:1234

    */
    @Test
    public void post03(){
        //1.Set the base url
        spec.pathParam("first","todos");
        //2.Set the expected data
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);
        //3.Send a POST request and get the response
        //If there is authentication on the task, we need to use it after "spec()"
        //When we use auth, we can use different type of auths in API. We use basic auth which requires username and password.
        //We already built our body in the 2. part. That is why we can call expected data inside the body
        //We are going to store all of them inside the response.
        Response response=given().spec(spec).auth().basic("admin","1234").
                contentType(ContentType.JSON).
                body(expectedData).when().post("/{first}");
        //4.Validation
        response.then().statusCode(201);
        response.prettyPrint();
        //expectedData.put("id", 201);
        //1.WAY OF VALIDATION
        Map<String,Object> actualData= response.as(HashMap.class);
        System.out.println("Expected Data: "+expectedData);
        System.out.println("Actual Data: "+actualData);
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        //2.WAY OF VALIDATION USING JSON PATH
        JsonPath json=response.jsonPath();
            //our expected data is coming from map
            //Our "userId" value is integer. That is why we are using "json.getInt"
        assertEquals(expectedData.get("userId"),json.getInt("userId"));
        assertEquals(expectedData.get("title"),json.getString("title"));
        assertEquals(expectedData.get("completed"),json.getBoolean("completed"));

        //3.Way of validation USING Hamcrest matcher api
        response.then().body("userId",equalTo(expectedData.get("userId"))).
                body("title",equalTo(expectedData.get("title"))).
                body("completed",equalTo(expectedData.get("completed")));
        //4.Way of validation USING POJO CLASS
        Todo todo=response.as(Todo.class);
        assertEquals(expectedData.get("userId"),todo.getUserId());
        assertEquals(expectedData.get("title"),todo.getTitle());
        assertEquals(expectedData.get("completed"),todo.isCompleted());
    }
}
