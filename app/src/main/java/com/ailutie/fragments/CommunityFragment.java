package com.ailutie.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ailutie.R;
import com.ailutie.activity.MainActivity;
import com.ailutie.constants.Constant;
import com.ailutie.datamodel.bean.TrainPlan;
import com.ailutie.datamodel.bean.User;
import com.ailutie.datamodel.db.UserDao;
import com.ailutie.datamodel.json.GsonUtils;
import com.ailutie.tools.LogAndroid;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

public class CommunityFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View communityLayout = inflater.inflate(R.layout.community_layout, container,
                false);
        Button btn_addUser = (Button) communityLayout.findViewById(R.id.btn_addUser);
        Button btn_readUser = (Button) communityLayout.findViewById(R.id.btn_readUser);
        btn_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testAddUserInJson();
            }
        });
        btn_readUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testReadUserInJson();
            }
        });
        return communityLayout;
    }


    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        MainActivity.currFragTag = Constant.FRAGMENT_FLAG_COMMUNITY;
    }

    public void testAddUserInDb() {
        User u = new User();
        u.setName("张鸿洋");
        LogAndroid.e("id: " + u.getId());
        new UserDao(MainActivity.getInstance()).add(u);


    }

    public void testAddUserInJson() {
        LogAndroid.e();
        Gson gson = new Gson();

        User u = new User();
        u.setId(1);
        u.setName("JianGe");
        u.setAssociation_name("laozeng1982");
        u.setPassword("fuck you");
        TrainPlan t1 = new TrainPlan(new Date(SystemClock.currentThreadTimeMillis() + 300000), u.getName());
        TrainPlan t2 = new TrainPlan(new Date(), u.getName());
        ArrayList arr = new ArrayList();
        arr.add(t1);
        arr.add(t2);
        u.setTrainPlans(arr);

        final String json = gson.toJson(u);

        LogAndroid.e(json);

        String filePath = Environment.getExternalStorageDirectory() + "/users.dat";

        GsonUtils.addUsers(filePath, u);


    }

    public void testReadUserInJson() {

        Gson gson = new Gson();

        String filePath = Environment.getExternalStorageDirectory() + "/users.dat";
        ArrayList<User> users = GsonUtils.readUsers(filePath);
        if (users.size() > 0) {
            for (User usr : users) {
                LogAndroid.e(gson.toJson(usr));
                for (TrainPlan trainPlan : usr.getTrainPlans()) {
                    LogAndroid.e(gson.toJson(trainPlan));
                }
            }
        } else {
            LogAndroid.e("No users!");
        }


    }


}
