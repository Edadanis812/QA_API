package Base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerokuAppBaseUrl {

    //this will be null for now until I set it.
    protected RequestSpecification spec; //We used protected.Because we want to use it in sub classes.

    @Before
    public void  setUp(){

        spec= new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }
}

