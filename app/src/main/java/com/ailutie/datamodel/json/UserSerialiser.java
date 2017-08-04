package com.ailutie.datamodel.json;

/**
 * Created by deep on 8/3/17.
 */

import com.ailutie.datamodel.bean.TrainPlan;
import com.ailutie.datamodel.bean.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

public class UserSerialiser implements JsonSerializer<User> {

    @Override
    public JsonElement serialize(final User user, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", user.getId());
        jsonObject.addProperty("name", user.getName());
        jsonObject.addProperty("association_name", user.getAssociation_name());
        jsonObject.addProperty("passwd", user.getPassword());

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(User.class, new TrainPlanSerialiser());
        gsonBuilder.setPrettyPrinting();
        final Gson gson = gsonBuilder.create();
        TrainPlan t1 = new TrainPlan(new Date(),user.getName());
        jsonObject.addProperty("trainPlans", gson.toJson(t1));

        return jsonObject;
    }
}
