package post_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Todo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post02 extends JsonPlaceHolderBaseUrl {
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
     */
    @Test
    public void post02(){
        //1.Set the base url
        spec.pathParams("first","todos");
        //Note:For this data type, I need to use Map.Key=string Value=Object
        // {
        //                                    "userId": 55,
        //                                    "title": "Tidy your room",
        //                                    "completed": false
        //                                   }
        //2.Set the expected data
        Map<String,Object> expectedData= new HashMap<>();
        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);

        //3.Send a POST request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        //4.Then do the validation
        response.then().statusCode(201);
        expectedData.put("id", 201);
        Map<String,Object> actualData=response.as(HashMap.class);

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals("data does not match",expectedData.get("id"), actualData.get("id"));
        System.out.println("expected data: "+expectedData);
        System.out.println("actual data: "+actualData);
    }
    //It is the same as above process. But in this case, we are using "POJO" class.
    @Test
    //For this "Test" class, we updated the  POJO Todo class according to our parameters
    //We added:
    // public Todo(int userId, String title, boolean completed) {
    //        this.userId = userId;
    //        this.title = title;
    //        this.completed = completed;
    //    }
    public  void testPost(){
        //Set the base url
        spec.pathParam("first", "todos");
        //Set the expected data
        Todo expectedData = new Todo(55, "Tidy your room",false);
        //Send the Post request and Get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData  ).when().post("/{first}");
        //Do the validation
        response.then().statusCode(201);
        //we get the actual data into our pojo
        Todo actualData = response.as(Todo.class);
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.isCompleted(), actualData.isCompleted());

    }



}
