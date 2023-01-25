package get_http_request_method;

import Base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Todo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {
     /*
     Given
            https://jsonplaceholder.typicode.com/todos/2
     When I send a Get Request
     Then the actual data should be as following;
        {
        "userId": 1,
        "id": 2,
        "title": "quis ut nam facilis et officia qui",
        "completed": false
    }
     */
    //  {
     //        "userId": 1,
     //        "id": 2,
     //        "title": "quis ut nam facilis et officia qui",
     //        "completed": false
     //    }
    //When we check the data type left side is "string", right side id int, boolean, string==> thats is why we chose "object"
     @Test
    public void get08(){
        //Given task, it looks like map in java
        //1. Set the base url
        spec.pathParams("first","todos","second",2);
        //2. set the expected data
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",2);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        //3. Send the Get request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        //Do validation
        //Now we are going to compare the actual data
        //Object type is coming left side
        Map<String,Object> actualData=response.as(HashMap.class);
//        System.out.println("expectedData:"+expectedData);
//        System.out.println("actualData:"+actualData);

         assertEquals(expectedData.get("userId"), actualData.get("userId"));
         assertEquals(expectedData.get("id"), actualData.get("id"));
         assertEquals(expectedData.get("title"), actualData.get("title"));
         assertEquals(expectedData.get("completed"), actualData.get("completed"));

    }
    @Test
    public void test(){
        //1. Set the base url
        spec.pathParams("first", "todos", "second",2);
        //2.Set the expected data. (It is comming from class Get08)
        //It is parametrezed
        //Pojo is clear than Map method. We are using the same method
        Todo expectedTodo = new Todo(1,2,"quis ut nam facilis et officia qui",false);
        //3.Send the Get request and get the response
        //It is going to return Response body
        Response response=given().spec(spec).when().get("/{first}/{second}");
        //4.When we say "Todo" because our data type is Todo
        //We have get and setter. We can create it directly.Todo is data type in this case
        //This is todo reference.This part is returning Todo object
        Todo actualTodo=response.as(Todo.class);
        //OR
        //Todo actualTodo=new Todo();
        //actualTodo=response.as(Todo.class);
        //5.Validation
        assertEquals(expectedTodo.getId(),actualTodo.getId());
        assertEquals(expectedTodo.getTitle(),actualTodo.getTitle());
        assertEquals(expectedTodo.getUserId(),actualTodo.getUserId());
        assertEquals(expectedTodo.isCompleted(),actualTodo.isCompleted());
        System.out.println("Expected Todo: "+expectedTodo);
        System.out.println("Actual Todo: "+actualTodo);


    }

}
