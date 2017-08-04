package com.ailutie.datamodel.bean;

import com.ailutie.tools.LogAndroid;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

@DatabaseTable(tableName = "tb_user")
public class User {
    public static final String NAME_FIELD_NAME = "name";
    public static final String ASSOCIATION_NAME = "association name";
    public static final String PASSWORD_FIELD_NAME = "passwd";

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = NAME_FIELD_NAME)
    private String name;
    @DatabaseField(columnName = ASSOCIATION_NAME)
    private String association_name;
    @DatabaseField(columnName = PASSWORD_FIELD_NAME)
    private String password;

    @ForeignCollectionField
    private ArrayList<TrainRecord> trainRecords = new ArrayList<TrainRecord>();

    @ForeignCollectionField
    private ArrayList<TrainPlan> trainPlans = new ArrayList<TrainPlan>();

    private boolean defaultUser = false;

    public User() {
        // all persisted classes must define a no-arg constructor with at least package visibility
    }

    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssociation_name() {
        return association_name;
    }

    public void setAssociation_name(String association_name) {
        this.association_name = association_name;
        LogAndroid.e();
    }

    public String getPassword() {
        if (password.isEmpty())
            makepasswd(this.password);
        return password;
    }

    public void setPassword(String password) {
        this.password = makepasswd(password);
//        LogAndroid.e(this.password);
    }

    public ArrayList<TrainRecord> getTrainRecords() {
        return trainRecords;
    }

    public void setTrainRecords(ArrayList<TrainRecord> records) {
        this.trainRecords = records;
    }

    public ArrayList<TrainPlan> getTrainPlans() {
        return trainPlans;
    }

    public void setTrainPlans(ArrayList<TrainPlan> trainPlans) {
        this.trainPlans = trainPlans;
    }

    public boolean isDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(boolean defaultUser) {
        this.defaultUser = defaultUser;
    }

    public void addTrainRecord(TrainRecord trainRecord) {
        this.trainRecords.add(trainRecord);
    }

    public void addTrainPlan(TrainPlan trainPlan) {
        this.trainPlans.add(trainPlan);
    }

    private String makepasswd(String password) {
        return String.valueOf(this.name.hashCode()) + String.valueOf(password.hashCode());
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        boolean result = name.equals(((User) other).name) || association_name.equals(((User) other).association_name);
        return result;
    }

    @Override
    public String toString() {
        int trainPlanCnt = 0;
        if (trainPlans != null)
            trainPlanCnt = trainPlans.size();
        return "User [id=" + id + ", name=" + name + ", association_name=" + association_name + ", passwd=" + password
                + ", Plans number=" + trainPlanCnt + "]";
    }


}
