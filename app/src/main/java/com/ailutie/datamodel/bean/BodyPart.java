package com.ailutie.datamodel.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_bodypart")
public class BodyPart {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String partName;
    @DatabaseField(canBeNull = false, foreign = true, columnName = "user_id", foreignAutoRefresh = true)
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BodyPart [id=" + id + ", partName=" + partName + ", user=" + user
                + "]";
    }

}
