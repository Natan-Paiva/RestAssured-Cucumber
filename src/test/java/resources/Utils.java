package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;
    public static ResponseSpecification res;

    public RequestSpecification requestSpecification() throws IOException {
        if(req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    public ResponseSpecification responseSpecification() {
        res = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
        return res;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\Natan\\Documents\\RestAssuredCucumber\\src\\test\\java\\resources\\global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public String getJsonPath(Response response, String key){
        String resp =  response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }
}
