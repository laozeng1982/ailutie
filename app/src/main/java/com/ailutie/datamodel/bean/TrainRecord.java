package com.ailutie.datamodel.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by deep on 8/2/17.
 */

@DatabaseTable(tableName = "tb_trainRecords")
public class TrainRecord {

    // for QueryBuilder to be able to find the fields
    public static final String TRAIN_DATE = "date";
    public static final String PASSWORD_FIELD_NAME = "passwd";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = TRAIN_DATE, canBeNull = false)
    private Date date;

    @DatabaseField(canBeNull = false, foreign = true, columnName = "user_id", foreignAutoRefresh = true)
    private User user;

    public TrainRecord() {
        // all persisted classes must define a no-arg constructor with at least package visibility
    }

    public TrainRecord(Date date) {
        this.date = date;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
