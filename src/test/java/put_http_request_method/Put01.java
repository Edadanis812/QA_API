package put_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Todo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static test_data.JsonPlaceHolderData.expectedData;

public class Put01 extends JsonPlaceHolderBaseUrl {


       /*
         When
            I send PUT Request to the Url https://jsonplaceholder.typicode.com/todos/198
            with the PUT Request body like {
                                            "userId": 21,
                                            "title": "Walk the dog",
                                            "completed": true
                                           }
         Then
           Status code is 200
           And response body is like   {
                                        "userId": 21,
                                        "title": "Walk the dog",
                                        "completed": true,
                                        "
                                       }
     */

    @Test
    public void put01(){
        //1.Set the base url
        spec.pathParams("first","todos","second",198);

        //2.Set the expected data
//        Map<String,Object> expectedData=new HashMap<>();
//        expectedData.put("userId",21);
//        expectedData.put("title","Walk the dog");
//        expectedData.put("completed",true);
        //Rather than here, we can create "JsonPlaceHolderData" and call here.
        //If we want to update data, we can go to "test_data" package and change
        //FIRST WAY OF THE SETTING DATA:
        Map<String,Object> expectedData =expectedData();

        //SECOND WAY OF THE SETTING DATA:
        Todo expectedTodo=new Todo(21,"Walk the dog",true);
        //3.Get the Put request and get the repsonse
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");

        //4.Validation
        response.then().statusCode(200);
        response.prettyPrint();

        //5.Validate the body
        response.then().body("userId",equalTo(expectedTodo.getUserId())).
                body("title",equalTo(expectedTodo.getTitle())).
                body("completed",equalTo(expectedTodo.isCompleted()));

                    //2.Way
        Map<String,Object> actualDataMap=response.as(HashMap.class);
        assertEquals(expectedTodo.getUserId(),actualDataMap.get("userId"));
        assertEquals(expectedTodo.getTitle(),actualDataMap.get("title"));
        assertEquals(expectedTodo.isCompleted(),actualDataMap.get("completed"));
                    //3.Way
        Todo actualTodo=response.as(Todo.class);
        assertEquals(expectedTodo.getUserId(),actualTodo.getUserId());
        assertEquals(expectedTodo.getTitle(),actualTodo.getTitle());
        assertEquals(expectedTodo.isCompleted(),actualTodo.isCompleted());
                    //4.Way(Json)
        JsonPath json=response.jsonPath();
        assertEquals(expectedTodo.getUserId(),json.getInt("userId"));
        assertEquals(expectedTodo.getTitle(),json.getString("title"));
        assertEquals(expectedTodo.isCompleted(),json.getBoolean("completed"));

    }

}
