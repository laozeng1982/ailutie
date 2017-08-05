package com.ailutie.datamodel.bean;

import com.ailutie.R;
import com.ailutie.activity.MainActivity;
import com.ailutie.tools.LogAndroid;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by deep on 7/31/17.
 */

@DatabaseTable(tableName = "tb_movement")
public class Movement {
    private String id;
    private String bodyPart;
    private String name;
    private int planGroupCount;
    private int planMovementCount;
    private float planMovementWeight;
    private int actualGroupCount;
    private int actualMovementCount;
    private float actualMovementWeight;
    private String feeling;
    private int spinnerAdapterSource;

    public Movement() {

    }

    public Movement(String id, String bodyPart) {
        super();
        this.id = id;
        this.bodyPart = bodyPart;
        setSpinnerAdapterSource(bodyPart);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public float getPlanGroupCount() {
        return planGroupCount;
    }

    public void setPlanGroupCount(int planGroupCount) {
        this.planGroupCount = planGroupCount;
    }

    public void setPlanGroupCount(String planGroupCount) {
        this.planGroupCount = Integer.valueOf(planGroupCount);
    }

    public float getPlanMovementCount() {
        return planMovementCount;
    }

    public void setPlanMovementCount(int planMovementCount) {
        this.planMovementCount = planMovementCount;
    }

    public void setPlanMovementCount(String planMovementCount) {
        this.planMovementCount = Integer.valueOf(planMovementCount);
    }

    public float getPlanMovementWeight() {
        return planMovementWeight;
    }

    public void setPlanMovementWeight(float planMovementWeight) {
        this.planMovementWeight = planMovementWeight;
    }

    public void setPlanMovementWeight(String planMovementWeight) {
        this.planMovementWeight = Integer.valueOf(planMovementWeight);
    }

    public int getActualGroupCount() {
        return actualGroupCount;
    }

    public void setActualGroupCount(int actualGroupCount) {
        this.actualGroupCount = actualGroupCount;
    }

    public int getActualMovementCount() {
        return actualMovementCount;
    }

    public void setActualMovementCount(int actualMovementCount) {
        this.actualMovementCount = actualMovementCount;
    }

    public float getActualMovementWeight() {
        return actualMovementWeight;
    }

    public void setActualMovementWeight(float actualMovementWeight) {
        this.actualMovementWeight = actualMovementWeight;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    private void setSpinnerAdapterSource(String part) {
        LogAndroid.e("add"+part);

        if (part.equals(getResourceString(R.string.bodypart_pectorales)))
            spinnerAdapterSource = R.array.movement_pectorales_Array;
        else if (part.equals(getResourceString(R.string.bodypart_shoulder)))
            spinnerAdapterSource = R.array.movement_shoulder_Array;
        else if (part.equals(getResourceString(R.string.bodypart_dorsal)))
            spinnerAdapterSource = R.array.movement_dorsal_Array;
        else if (part.equals(getResourceString(R.string.bodypart_biceps)))
            spinnerAdapterSource = R.array.movement_biceps_Array;
        else if (part.equals(getResourceString(R.string.bodypart_triceps)))
            spinnerAdapterSource = R.array.movement_triceps_Array;
        else if (part.equals(getResourceString(R.string.bodypart_forearm)))
            spinnerAdapterSource = R.array.movement_forearm_Array;
        else if (part.equals(getResourceString(R.string.bodypart_waist)))
            spinnerAdapterSource = R.array.movement_waist_Array;
        else if (part.equals(getResourceString(R.string.bodypart_abdomen)))
            spinnerAdapterSource = R.array.movement_abdomen_Array;
        else if (part.equals(getResourceString(R.string.bodypart_thigh)))
            spinnerAdapterSource = R.array.movement_thigh_Array;
        else if (part.equals(getResourceString(R.string.bodypart_shank)))
            spinnerAdapterSource = R.array.movement_shank_Array;
        else
            spinnerAdapterSource = -1;
    }

    public int getSpinnerAdapterSource() {
        return spinnerAdapterSource;
    }

    private String getResourceString(int source) {
        return MainActivity.getInstance().getResources().getString(source);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        return name.equals(((Movement) other).name);
    }

//    @Override
//    public String toString() {
//        return "id: " + this.getId() +
//    }

}
