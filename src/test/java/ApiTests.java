import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ApiTests {

    @Test
    @Order(1)
    public void getRequestTest(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
       Response response = given().when().get("/posts/1");

       int statusCode = response.getStatusCode();
       String title = response.jsonPath().getString("title");

       Assertions.assertEquals(200, statusCode);
       Assertions.assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", title);
       System.out.println("GET REQUEST PASSED");
    }

    @Test
    @Order(2)
    public void postRequestTest() {
        RestAssured.baseURI = "https://reqres.in/";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when()
                .post("/api/users");

        int statusCode = response.getStatusCode();
        String name = response.jsonPath().getString("name");

        Assertions.assertEquals(201, statusCode);
        Assertions.assertEquals("morpheus", name);
        System.out.println("POST REQUEST PASSED");
    }

    @Test
    @Order(3)
    public void putRequestTest() {
        RestAssured.baseURI = "https://reqres.in/";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .when()
                .put("/api/users/2");

        int statusCode = response.getStatusCode();
        String job = response.jsonPath().getString("job");

        Assertions.assertEquals(200, statusCode);
        Assertions.assertEquals("zion resident", job);
        System.out.println("PUT REQUEST PASSED");
    }

    @Test
    @Order(4)
    public void deleteRequestTest(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = when().delete("/posts/1");

        int statusCode = response.getStatusCode();

        Assertions.assertEquals(200, statusCode);
        System.out.println("DELETE REQUEST PASSED");
    }
}
