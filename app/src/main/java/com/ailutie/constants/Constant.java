package com.ailutie.constants;

import android.os.Environment;

import com.ailutie.datamodel.bean.User;

import java.text.SimpleDateFormat;

public class Constant {
    //Btn Flag
    public static final int BTN_FLAG_PLAN = 0x01;
    public static final int BTN_FLAG_TRAIN = 0x01 << 1;
    public static final int BTN_FLAG_COMMUNITY = 0x01 << 2;
    public static final int BTN_FLAG_SETTING = 0x01 << 3;

    //Fragment Flag
    public static final String FRAGMENT_FLAG_PLAN = "做计划";
    public static final String FRAGMENT_FLAG_TRAIN = "撸铁中";
    public static final String FRAGMENT_FLAG_COMMUNITY = "找基友";
    public static final String FRAGMENT_FLAG_SETTING = "我的";

    public static final String USER_DATA_PATH = Environment.getExternalStorageDirectory() + "/users.dat";

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

    public static User APP_USER = new User();

    public static String Selected_Date;

}
