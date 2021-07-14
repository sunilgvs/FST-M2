

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Activity1 {
	String baseURI = "https://petstore.swagger.io/v2/pet";
	@Test(priority = '1')
	public void postRequest() {
		String reqBody = "{\"id\": 88123,\"name\": \"sunilgvs\",\"status\": \"alive\"}";
		Response response = given().contentType(ContentType.JSON).body(reqBody).when().post(baseURI);
		// Assertion
		response.then().body("id", equalTo(88123));
		response.then().body("name", equalTo("sunilgvs"));
		response.then().body("status", equalTo("alive"));
	}

	@Test(priority = '2')
	public void getRequest() {
		Response response = given().contentType(ContentType.JSON).when().get(baseURI + "/88123");
		// Assertion
		response.then().body("id", equalTo(88123));
		response.then().body("name", equalTo("sunilgvs"));
		response.then().body("status", equalTo("alive"));
	}

	@Test(priority = '3')
	public void deleteRequest() {
		Response response = given().contentType(ContentType.JSON).when().pathParam("petId", "88123")
				.delete(baseURI + "/{petId}");
		// Assertion
		response.then().body("code", equalTo(200));
		response.then().body("message", equalTo("88123"));
	}
}