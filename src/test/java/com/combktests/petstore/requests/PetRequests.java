package com.combktests.petstore.requests;

import com.combktests.petstore.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.combktests.petstore.models.Pet; // If using Pet class

import static com.combktests.petstore.uris.Uris.*;


public class PetRequests {
    /**
     * This is another way to approach building the framework. The REST requests can be written in
     * wrapper methods specific to API end points. This will move away the RestAssured given() when() then()
     * syntax from tests. However, wrapper methods like these can be called in step definitions if BDD
     * frameworks such as Cucumber is being used - that way features of RestAssured can be leveraged
     * for step def implementation.  Including this to demonstrate alternative approach.
     *
     */

    // pet param will be set in cucumber step definition() and passed in as param
    // data can originally come from cucumber data table in feature file
    public  Response createPet(Pet pet) throws JsonProcessingException {
        String petJson = new JsonUtils().createJsonStringFromPet(pet);

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petJson)
                .post(createPetEndpoint);
    }

    public static Response getPetById(long petId) {
        return RestAssured.when().get(getPetEndpoint, petId);
    }

    // pet param will be set in cucumber step definition() and passed in as param
    // pet data can originate from cucumber data table in feature file
    public  Response updatePet(Pet pet) throws JsonProcessingException {
        String petJson = new JsonUtils().createJsonStringFromPet(pet);

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petJson)
                .put(updatePetEndpoint);
    }

}