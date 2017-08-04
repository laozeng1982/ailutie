package com.ailutie.datamodel.json;

/**
 * Created by deep on 8/3/17.
 */

import com.ailutie.datamodel.bean.Movement;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class MovementSerialiser implements JsonSerializer<Movement> {

    @Override
    public JsonElement serialize(final Movement movement, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", movement.getId());
        jsonObject.addProperty("name", movement.getName());

        return jsonObject;
    }
}
