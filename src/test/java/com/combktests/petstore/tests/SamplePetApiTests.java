package com.combktests.petstore.tests;
import com.combktests.petstore.BaseTest;
import com.combktests.petstore.models.Pet;
import com.combktests.petstore.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.Test;

import static com.combktests.petstore.uris.Uris.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Demonstrating this way of writing the tests as a way for better contract adherence,
 * and stricter control. Create data objects to store request and response
 * bodies - then construct JSON on run time rather than having JSON in json files.
 * This also uses RestAssured given() when() then() methods directly in the tests
 * for readability. Also look at the PetRequests class for an alternative approach.
 *
 */

public class SamplePetApiTests extends BaseTest {

    @Test(priority = 1)
    public void testCreatePet() throws JsonProcessingException {
        Pet pet = new Pet();
        pet.setId(123);
        pet.setName("Buddy");
        pet.setStatus("available");

        String petJson = new JsonUtils().createJsonStringFromPet(pet);

        given()
                .header("api_key", API_KEY)
                .contentType("application/json")
                .body(petJson)
        .when()
                .post(createPetEndpoint)
        .then()
                .statusCode(200)
                .body("id", equalTo(123))
                .body("name", equalTo("Buddy"))
                .body("status", equalTo("available"));
    }

    @Test(priority = 2)
    public void testGetPetById() {
        int petId = 123;

        given()
                .header("api_key", API_KEY)
        .when()
                .get(getPetEndpoint, petId)
        .then()
                .statusCode(200)
                .body("id", equalTo(petId))
                .body("name", notNullValue())
                .body("status", notNullValue());
    }



    @Test(priority = 3)
    public void testUpdatePet() throws JsonProcessingException {
        Pet pet = new Pet();
        pet.setId(123);
        pet.setName("Snowy");
        pet.setStatus("sold");

        String updatedPetBody = new JsonUtils().createJsonStringFromPet(pet);

        given()
                .header("api_key", API_KEY)
                .contentType("application/json")
                .body(updatedPetBody)
        .when()
                .put(updatePetEndpoint)
        .then()
                .statusCode(200)
                .body("status", equalTo("sold"));
    }

    @Test(priority = 4)
    public void testDeletePet() {
        int petId = 123;

        given()
                .header("api_key", API_KEY)
        .when()
                .delete(deletePetEndpoint, petId)
        .then()
                .statusCode(200);
    }
}