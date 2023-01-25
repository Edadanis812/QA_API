package post_http_request_method;
import Base_urls.MedunnaBaseUrl;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Registrant;

import static io.restassured.RestAssured.given;
import static utility.WriteToTxt.generateData;

public class Post04 extends MedunnaBaseUrl {

    // https://medunna.com/api/register

    @Test
    public void testPost(){
        spec.pathParams("first", "api", "second", "register");
//        RequestSpecification spec1 = spec.pathParams("first", "api", "second", "register");  --> bu tip kullanim denenebilir

        Faker faker = new Faker();
        Registrant registrant = new Registrant();

        registrant.setFirstName("Ozen");
        registrant.setLastName(faker.name().lastName());
        registrant.setEmail(faker.internet().emailAddress());
        registrant.setLangKey("en");
        registrant.setLogin("OTurkoz");
        registrant.setPassword(faker.internet().password(8,20,true));
        registrant.setSsn(faker.idNumber().ssnValid());

        //send the Post request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).   // data olusturuyoruz, type json olsun diyoruz sadece
                body(registrant).when().post("/{first}/{second}");          // body sadece put, post ve patch de kullanilir(diger komutlarda gerek yok)
//
        response.then().statusCode(201); // status code validation kontrol --> then()     action durumlarinda when()


        String fileName = "./src/test/java/test_data/medunna_info.txt";


        // ./src/test/java/test_data

        generateData(registrant,fileName);

        // post --> create  --> post ile veri gonderdigimiz database e ekliyor. mesela 200 tane varsa, 201. olarak yeni id ekleniyor
        // put --> update
        // oop concept in your framework--> static metod class name, / object olusturma / parent-child ile class lar arasi siniflar arasi bag kuruyoruz
        // selenium webdriver bir interface ve full abstract, driver util de switch case kodlariyle interface den subclass lara ulastik(firefox-chromedriver)
        // alt class lar selenium webdriver ozelligini aliyorlar cinku o parent, polymorphisim oluyor, hatta override da oluyor. webdriver chrome = new Chromedriver
        // abstract - abstraction farkli
        // abstract class da concrete ve abstract metod kullanilir ama interface de metodlar sadece abstract olur
        // abstract class amaci sadece guvenlik, obje olusturulmaz
        // bir sinifi iki class a extend edemiyoruz

    }

}