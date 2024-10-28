package com.aiiagcu.air.util;


import com.aiiagcu.air.config.LocalDateTimeTypeAdapter;
import com.aiiagcu.air.entity.User;
import com.google.gson.*;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

public class JsonStringToUser implements Converter<String, User> {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .create();

    @Override
    public User convert(String source) {
        try {
            System.out.println("source = " + source);
            return gson.fromJson(source, User.class);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }
}