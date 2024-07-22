package com.combktests.petstore.utils;

import com.combktests.petstore.models.Pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    public String createJsonStringFromPet(Pet pet) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(pet);
    }
}