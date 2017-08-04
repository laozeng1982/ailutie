package com.ailutie.datamodel.json;

/**
 * Created by deep on 8/3/17.
 */

import com.ailutie.datamodel.bean.TrainPlan;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class TrainPlanSerialiser implements JsonSerializer<TrainPlan> {

    @Override
    public JsonElement serialize(final TrainPlan trainPlan, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", trainPlan.getId());
        jsonObject.addProperty("date", trainPlan.getDate().toString());
        jsonObject.addProperty("user",trainPlan.getUser());


//        jsonObject.addProperty("passwd",trainPlan.g());
//        jsonObject.addProperty("train_plan",trainPlan.getTrainRecords());

        return jsonObject;
    }
}
