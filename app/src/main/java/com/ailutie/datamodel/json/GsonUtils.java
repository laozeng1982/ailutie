package com.ailutie.datamodel.json;

import com.ailutie.datamodel.bean.User;
import com.ailutie.tools.LogAndroid;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tools.files.IOHelper;

/**
 * Created by deep on 8/3/17.
 */

/*
  * 封装的GSON解析工具类，提供泛型参数
 */
public class GsonUtils {
    // 将Json数据解析成相应的映射对象
    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }

    // 将Json数组解析成相应的映射对象列表
    public static <T> List<T> parseJsonArrayWithGson(String jsonData,
                                                     Class<T> type) {
        Gson gson = new Gson();
        List<T> result = new Gson().fromJson(jsonData, new TypeToken<List<T>>() {
        }.getType());
        return result;
    }

    // 将Json数组解析成相应的映射对象列表
    @org.jetbrains.annotations.NotNull
    public static <T> List<T> parseJsonArrayWithGson2(String jsonData,
                                                      Class<T[]> type) {
        Gson gson = new Gson();
        T arr[] = gson.fromJson(jsonData, type);

        return Arrays.asList(arr);
    }

    /**
     * @param filePath
     * @param user
     * @return
     */
    public static boolean addUsers(String filePath, User user) {
        boolean isAdd = false;
        ArrayList<User> userList = readUsers(filePath);

        if (!userList.contains(user)) {
            userList.add(user);
            isAdd = true;
        } else {
            isAdd = false;
        }

        int user_cnt = userList.size();

        //reset user ID
        for (int i = 0; i < user_cnt; i++) {
            userList.get(i).setId(i + 1);
        }

        Gson gson = new Gson();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[\n");


        if (user_cnt == 1)
            stringBuilder.append(gson.toJson(userList.get(0))).append("\n]");
        else {
            for (int i = 0; i < userList.size(); i++) {
                stringBuilder.append(gson.toJson(userList.get(i)));
                if (i != (user_cnt - 1)) {
                    stringBuilder.append(",\n");
                } else {
                    stringBuilder.append("\n]");
                }
            }
        }

        LogAndroid.e(stringBuilder.toString());
        IOHelper.saveAsciiToFile(filePath, stringBuilder.toString(), false);

        return isAdd;
    }

    public static ArrayList<User> readUsers(String filePath) {
        File file = new File(filePath);
        List<User> userList;
        ArrayList<User> output = new ArrayList<User>();
        if (!file.exists()) {
            LogAndroid.e("No users!");
        } else {
            StringBuilder jsonData = new StringBuilder();
            try {
                String tempLine;
                BufferedReader reader = IOHelper.readAscii(filePath);
                while ((tempLine = reader.readLine()) != null) {
                    jsonData.append(tempLine);
                }

            } catch (IOException e) {
                jsonData.append("No data!");
                LogAndroid.e(jsonData.toString());
            }
//            LogAndroid.e(jsonData.toString());
            if (!jsonData.toString().isEmpty()) {
                userList = GsonUtils.parseJsonArrayWithGson2(jsonData.toString(), User[].class);
                for (int i = 0; i < userList.size(); i++) {
                    output.add(userList.get(i));
//                    LogAndroid.e(userList.get(i).toString());
                }
            }
        }

        return output;
    }
}
