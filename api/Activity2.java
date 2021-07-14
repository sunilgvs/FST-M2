
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;
import java.io.IOException;

public class Activity2 {
	String BASE_URL = "https://petstore.swagger.io/v2/user";

  @Test(priority = '1')
  public void postReq() throws IOException {
	  FileInputStream JSON_File = new FileInputStream("src\\training\\request.json");
	  String reqBody = new String(JSON_File.readAllBytes());
  Response response = given().contentType(ContentType.JSON).body(reqBody).when().post(BASE_URL);
  
  //Assert
  System.out.println(response.asPrettyString());
  JSON_File.close();
  response.then().body("code", equalTo(200));
  response.then().body("message", equalTo("9001"));
}
  @Test (priority = '2')
  public void getReq() {	  
		File JSON_File = new File("\\src\\training\\userGETResponse.json");
  Response response = given().contentType(ContentType.JSON).when().get(BASE_URL+"/sunilgvs");
  String resBody = response.asPrettyString();
  //Save to external file
  try {	
      // Create log file
      JSON_File.createNewFile();
      // Write response body to external file
      FileWriter writer = new FileWriter(JSON_File.getPath());
      writer.write(resBody);
      writer.close();
  } catch (IOException excp) {
      excp.printStackTrace();
  }
  System.out.println(resBody);
  // Assertion
  response.then().body("id", equalTo(9221));
  response.then().body("username", equalTo("sunilgvs"));
  response.then().body("firstName", equalTo("Sunil"));
  response.then().body("lastName", equalTo("G"));
  response.then().body("email", equalTo("gvssunilkumar@mail.com"));
  response.then().body("password", equalTo("password123"));
  response.then().body("phone", equalTo("8790515177"));
}
@Test(priority = '3')
	public void deleteReq() {
		Response response = given().contentType(ContentType.JSON).when().pathParam("username", "sunilgvs")
				.delete(BASE_URL + "/{username}");
		// Assertion
		  System.out.println(response.asPrettyString());
		response.then().body("code", equalTo(200));
		response.then().body("message", equalTo("sunilgvs"));
	}
}