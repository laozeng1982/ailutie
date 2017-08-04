package com.ailutie.datamodel.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by deep on 8/2/17.
 */

@DatabaseTable(tableName = "tb_trainPlan")
public class TrainPlan {

    // for QueryBuilder to be able to find the fields
    public static final String TRAIN_DATE = "date";
    public static final String PASSWORD_FIELD_NAME = "passwd";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = TRAIN_DATE, canBeNull = false)
    private Date date;

    @DatabaseField(canBeNull = false, foreign = true, columnName = "user_id", foreignAutoRefresh = true)
    private String user;

    @ForeignCollectionField
    private ArrayList<Movement> movements = new ArrayList<Movement>();

    public TrainPlan() {
        // all persisted classes must define a no-arg constructor with at least package visibility
    }

    public TrainPlan(Date date) {
        this.date = date;
    }

    public TrainPlan(Date date, String user) {
        this.date = date;
        this.user = user;

    }


    public TrainPlan(Date date, String user, ArrayList<Movement> movements) {
        this(date, user);
        this.movements = movements;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String dateString) {
        this.date = new Date(dateString);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ArrayList<Movement> getMovements() {
        return movements;
    }

    public void setMovements(ArrayList<Movement> movements) {
        this.movements = movements;
    }

    @Override
    public String toString() {
        return this.id + "," + this.user + "," + this.date.toString();    //this.date.toString() +
    }

}
