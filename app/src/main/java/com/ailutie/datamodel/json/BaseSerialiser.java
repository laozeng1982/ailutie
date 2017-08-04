package com.ailutie.datamodel.json;

/**
 * Created by deep on 8/3/17.
 */

import com.ailutie.datamodel.bean.User;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class BaseSerialiser implements JsonSerializer<Type> {

//    @Override
    public JsonElement serialize(final User user, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", user.getId());
        jsonObject.addProperty("name", user.getName());

        return jsonObject;
    }

    @Override
    public JsonElement serialize(Type src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("id", src.getId());
//        jsonObject.addProperty("name", user.getName());
        return null;
    }
}
