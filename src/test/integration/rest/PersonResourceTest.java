package integration.rest;

import org.junit.Before;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.parsing.Parser;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import rest.models.ContactInfoPerson;

public class PersonResourceTest {

    @Before
    public void setUp() {
        baseURI = "http://localhost:8084";
        defaultParser = Parser.JSON;
        basePath = "3.semCa.2/api/person";
    }

    @Test
    public void testContactInfo_IsAbleToReturnContactInformation_ById() {
        when().get("/contactinfo/1")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body("firstName", equalTo("Kim"))
                .body("lastName", equalTo("Larsen"));
    }
    
    @Test
    public void testContactInfo_ShouldReturn404_IfNoContactInfoExists() {
        when().get("/contactinfo/9999")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void testContactInfo_IsAbleToReturnAListOfContactInformations() {
        Object contacts = when().get("/contactinfo")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .extract()
                .as(ContactInfoPerson[].class);
        
        assertNotNull(contacts);
    }

    @Test
    public void testGetPersonsComplete() {
    }

    @Test
    public void testGetPersonsContactInfo() {
    }

    @Test
    public void testPutJson() {
    }

}
