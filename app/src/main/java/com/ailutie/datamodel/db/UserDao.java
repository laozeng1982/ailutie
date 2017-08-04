package com.ailutie.datamodel.db;

import android.content.Context;

import com.ailutie.datamodel.bean.User;
import com.ailutie.tools.LogAndroid;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class UserDao {
    private Context context;
    private Dao<User, Integer> userDaoOpe;
    private DatabaseHelper helper;

    public UserDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            userDaoOpe = helper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个用户
     *
     * @param user
     * @throws SQLException
     */
    public void add(User user) {
        //事务操作

        try {
            if (userDaoOpe.queryForAll().size() > 0) {
                if (!userDaoOpe.queryForAll().contains(user)) {
                    userDaoOpe.create(user);
                    LogAndroid.e("Don't have, add!");
                }

            } else {
                userDaoOpe.create(user);
                LogAndroid.e("empty, add!");
            }
//			userDaoOpe.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User get(int id) {
        try {
            return userDaoOpe.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
