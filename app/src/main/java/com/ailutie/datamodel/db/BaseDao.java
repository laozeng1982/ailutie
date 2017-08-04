package com.ailutie.datamodel.db;

import android.content.Context;

import com.ailutie.tools.LogAndroid;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by deep on 8/1/17.
 */

public class BaseDao<T> {

    Context context;
    Dao<T, Integer> baseDaoOpe;
    DatabaseHelper helper;

    @SuppressWarnings("unchecked")
    public BaseDao(Context context) {
        this.context = context;
//        try {
//            helper = DatabaseHelper.getHelper(context);
//            baseDaoOpe = helper.getDao(T.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 增加一个用户
     *
     * @param t
     * @throws SQLException
     */
    public void add(T t) {
        //事务操作
        try {
            if (baseDaoOpe.queryForAll().size() > 0) {
                if (!baseDaoOpe.queryForAll().contains(t))
                    baseDaoOpe.create(t);
            } else {
                baseDaoOpe.create(t);
                LogAndroid.e("empty, add!");
            }
//			userDaoOpe.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public T get(int id) {
        try {
            return baseDaoOpe.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
