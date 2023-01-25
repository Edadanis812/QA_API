package get_http_request_method;
import org.codehaus.jackson.map.ObjectMapper;
import Base_urls.DummyApiBaseUrl;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.OuterData;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertTrue;

public class Get11 extends DummyApiBaseUrl {
    /*
      When
        I send a GET Request to the URL http://dummy.restapiexample.com/api/v1/employees
       Status code should be 200
       Use Gson and ObjectMapper
       make sure you have 24 records for data
  */
    //WE ARE GOING TO USE DESERIALIZATION
    @Test
    public void get11() throws IOException {
    //1. Set the base url
        spec.pathParams("first", "api", "second","v1","third","employees");

    //2.Set the expected data.

    //3.Send the Get request and get the response
    Response response=given().spec(spec).when().get("/{first}/{second}/{third}");
    //We can use pretty print in the beginning to get the data on the console.
    //response.prettyPrint();
    //NOTE: status=str , data=object(list)

    //We are using for "serialization and deserialization"
    //Notes:Serialization: Java to Json
    //      Deserialization: Json to Java
    //We do deserialization using Object Mapper
    ObjectMapper obj = new ObjectMapper();
    OuterData outerData=obj.readValue(response.asString(),OuterData.class);
    System.out.println(outerData.getData().size());
    for (int i=0;i<outerData.getData().size();i++){
        System.out.println(outerData.getData().get(i).getEmployee_salary());
    }
    System.out.println(outerData.getMessage());
    System.out.println(outerData.getStatus());
    assertTrue(outerData.getData().size()==24);
    }


}
