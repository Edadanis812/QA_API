package Base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyApiBaseUrl {
    //this will be null for now until I set it.
    protected RequestSpecification spec; //We used protected.Because we want to use it in sub classes.

    @Before
    public void  setUp(){
        //Set the base URL
        spec= new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com").build();
    }
}
