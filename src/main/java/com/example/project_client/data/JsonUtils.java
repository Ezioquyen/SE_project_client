package com.example.project_client.data;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static String toJson(Object object) {
        objectMapper.registerModule(new JavaTimeModule());
        try {
            System.out.println("Convert 1 success");
            return objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            System.out.println("Convert 1 failed");
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            System.out.println("Convert 2 success");
            return objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            System.out.println("Convert 2 fail");
            return null;
        }
    }
}

