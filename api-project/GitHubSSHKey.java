package training;
import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GitHubSSHKey {
    // Declare request specification
	RequestSpecification requestSpec;
    // Declare response specification
    ResponseSpecification responseSpec;
    // Global properties
    String sshKey="ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDaO0xV8UOWoTsKNFkEdt1Kjpw4qILi/g793jBkpfJHa0WLwO9Clm6czsAtOTHAKtzwO7nsMojSpw3mCpNMO1IRjZA995WK7ys4iaF2jcxjEs85293AEHC65bjseNYiZdUy3UEOVeCHKgpZmPQx7Sve/Nr3ojG/EoyfXzykPG2oMhsez+HK4YQXJigP/yOvYn4rlZrRziNRLU7hqAdpKSMjFk7nlcRqIOMtXOpF1YaQiHkiIjtIjw1BXpRMcBEW6ikds4KuD5LQKvrF+r2hz7zd7eAgiZN/QZl6GhjHGZFR6DbHW1dBqWSb8cp7bVgpYaBhYuuagM5cZ3n+0cCg+FIt";
    int sshKeyId;
    @BeforeClass
	public void setUp() {
    	requestSpec = new RequestSpecBuilder().setContentType(ContentType.JSON)
				.addHeader("Authorization", "token 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")
				.setBaseUri("https://api.github.com").build();
		//sshKey = "ssh-rsa ";
	}
    
    @Test(priority = 1)
    //method to post ssh key
	public void postSSHKey() {
		String requestBody = "{\"title\": \"TestKey\", \"key\": \"" + sshKey + "\" }";
		Response response = given().spec(requestSpec).body(requestBody).when().post("/user/keys");
		String responseBody = response.getBody().asPrettyString();
		//to print the response body
		System.out.println(responseBody);
		sshKeyId = response.then().extract().path("id");

	}

	@Test(priority = 2)
	//method to get ssh key
	public void getSSHKey() {
		Response response1 = given().spec(requestSpec).when().get("/user/keys");
		String responseBody1 = response1.getBody().asPrettyString();
		System.out.println(responseBody1);
		response1.then().statusCode(200);
	}

	@Test(priority = 3)
	//method to delete ssh key
	public void deleteSShKey() {
		Response response2 = given().spec(requestSpec).pathParam("keyId", sshKeyId).when().delete("/user/keys/{keyId}");
		String responseBody2 = response2.getBody().asPrettyString();
		System.out.println(responseBody2);
		response2.then().statusCode(204);
	}
    
}
