import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;

public class Activity3{

	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;

	@BeforeClass
	public void setUp() {
		requestSpec = new RequestSpecBuilder().setContentType(ContentType.JSON)
				.setBaseUri("https://petstore.swagger.io/v2/pet").build();

		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/JSON")
				.expectBody("status", equalTo("alive")).build();
	}

	@DataProvider
	public Object[][] petInfoProvider() {
		Object[][] testData = new Object[][] { { 88121, "Edit", "alive" }, { 88121, "Edited", "alive" } };
		return testData;
	}

	@Test(dataProvider = "petInfoProvider", priority = 1)
	public void postReq(int id, String name, String status) {
		String reqBody = "{\"id\": "+id+",\"name\": \"" +name+"\",\"status\": \""+status+"\"}";
		Response response = given().spec(requestSpec).body(reqBody).when().post();
		System.out.println(reqBody);

		response.then().spec(responseSpec);
	}

	@Test(dataProvider = "petInfoProvider", priority = 2)
	public void getReq(int id, String name, String status) {
		Response response = given().spec(requestSpec).pathParam("petID", id).when().get("/{petID}");

		response.then().spec(responseSpec).body("name", equalTo(name));
	}

	@Test(dataProvider = "petInfoProvider", priority = 3)
	public void deleteReq(int id, String name, String status) {
		Response response = given().spec(requestSpec).pathParam("petID", id).when().delete("/{petID}");
		response.then().body("code", equalTo(200));
	}
}