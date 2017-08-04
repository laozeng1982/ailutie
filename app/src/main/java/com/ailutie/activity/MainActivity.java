package com.ailutie.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;

import com.ailutie.R;
import com.ailutie.constants.Constant;
import com.ailutie.datamodel.bean.User;
import com.ailutie.datamodel.json.GsonUtils;
import com.ailutie.fragments.BaseFragment;
import com.ailutie.ui.controllor.BottomControlPanel;
import com.ailutie.ui.controllor.BottomControlPanel.BottomPanelCallback;
import com.ailutie.ui.controllor.HeadControlPanel;

import java.util.ArrayList;

public class MainActivity extends Activity implements BottomPanelCallback {
    BottomControlPanel bottomPanel = null;
    HeadControlPanel headPanel = null;

    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;

    private static MainActivity instance;

/*	private PlanFragment messageFragment;
    private TrainFragment contactsFragment;
	private CommunityFragment newsFragment;
	private SettingFragment settingFragment;*/

    public static String currFragTag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
        initUI();
        initData();
        fragmentManager = getFragmentManager();
        setDefaultFirstFragment(Constant.FRAGMENT_FLAG_PLAN);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initData() {
        ArrayList<User> userArrayList = GsonUtils.readUsers(Constant.USER_DATA_PATH);
        if (userArrayList.isEmpty()) {
            User user = new User();
            user.setName("JianGe");
            user.setAssociation_name("laozeng1982");
            user.setPassword("abcd");
            user.setDefaultUser(true);
            GsonUtils.addUsers(Constant.USER_DATA_PATH, user);
            userArrayList.add(user);
        }

        for (User user : userArrayList) {
            if (user.isDefaultUser()) {
                Constant.APP_USER = user;
            }
        }


    }

    private void initUI() {
        bottomPanel = (BottomControlPanel) findViewById(R.id.bottom_layout);
        if (bottomPanel != null) {
            bottomPanel.initBottomPanel();
            bottomPanel.setBottomCallback(this);
        }
        headPanel = (HeadControlPanel) findViewById(R.id.head_layout);
        if (headPanel != null) {
            headPanel.initHeadPanel();
        }
    }

    /* Handle BottomControlPanel Callback
     * @see BottomControlPanel.BottomPanelCallback#onBottomPanelClick(int)
     */
    @Override
    public void onBottomPanelClick(int itemId) {
        // TODO Auto-generated method stub
        String tag = "";
        if ((itemId & Constant.BTN_FLAG_PLAN) != 0) {
            tag = Constant.FRAGMENT_FLAG_PLAN;
        } else if ((itemId & Constant.BTN_FLAG_TRAIN) != 0) {
            tag = Constant.FRAGMENT_FLAG_TRAIN;
        } else if ((itemId & Constant.BTN_FLAG_COMMUNITY) != 0) {
            tag = Constant.FRAGMENT_FLAG_COMMUNITY;
        } else if ((itemId & Constant.BTN_FLAG_SETTING) != 0) {
            tag = Constant.FRAGMENT_FLAG_SETTING;
        }
        setTabSelection(tag); //Switch Fragment
        headPanel.setMiddleTitle(tag);//Swtich Title
    }

    private void setDefaultFirstFragment(String tag) {
//        Log.i("yan", "setDefaultFirstFragment enter... currFragTag = " + currFragTag);
        setTabSelection(tag);
        bottomPanel.defaultBtnChecked();
//        Log.i("yan", "setDefaultFirstFragment exit...");
    }

    private void commitTransactions(String tag) {
        if (fragmentTransaction != null && !fragmentTransaction.isEmpty()) {
            fragmentTransaction.commit();
            currFragTag = tag;
            fragmentTransaction = null;
        }
    }

    private FragmentTransaction ensureTransaction() {
        if (fragmentTransaction == null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        }
        return fragmentTransaction;

    }

    private void attachFragment(int layout, Fragment f, String tag) {
        if (f != null) {
            if (f.isDetached()) {
                ensureTransaction();
                fragmentTransaction.attach(f);

            } else if (!f.isAdded()) {
                ensureTransaction();
                fragmentTransaction.add(layout, f, tag);
            }
        }
    }

    private Fragment getFragment(String tag) {

        Fragment frg = fragmentManager.findFragmentByTag(tag);

        if (frg == null) {
            frg = BaseFragment.newInstance(getApplicationContext(), tag);
        }
        return frg;

    }

    private void detachFragment(Fragment f) {

        if (f != null && !f.isDetached()) {
            ensureTransaction();
            fragmentTransaction.detach(f);
        }
    }

    /**
     * Switch fragment
     *
     * @param tag
     */
    private void switchFragment(String tag) {
        if (TextUtils.equals(tag, currFragTag)) {
            return;
        }
        //detach last fragment
        if (currFragTag != null && !currFragTag.equals("")) {
            detachFragment(getFragment(currFragTag));
        }
        attachFragment(R.id.fragment_content, getFragment(tag), tag);
        commitTransactions(tag);
    }

    /**
     * Set selected Tag
     *
     * @param tag
     */
    public void setTabSelection(String tag) {
        // Start a Fragment
        fragmentTransaction = fragmentManager.beginTransaction();
/*		 if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_MESSAGE)){
           if (messageFragment == null) {
				messageFragment = new PlanFragment();
			} 
			
		}else if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_TRAIN)){
			if (contactsFragment == null) {
				contactsFragment = new TrainFragment();
			} 
			
		}else if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_COMMUNITY)){
			if (newsFragment == null) {
				newsFragment = new CommunityFragment();
			}
			
		}else if(TextUtils.equals(tag,Constant.FRAGMENT_FLAG_SETTING)){
			if (settingFragment == null) {
				settingFragment = new SettingFragment();
			}
		}else if(TextUtils.equals(tag, Constant.FRAGMENT_FLAG_SIMPLE)){
			if (simpleFragment == null) {
				simpleFragment = new SimpleFragment();
			} 
			
		}*/
        switchFragment(tag);

    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        currFragTag = "";
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
    }

    public static MainActivity getInstance() {
        // 因为我们程序运行后，Application是首先初始化的，如果在这里不用判断instance是否为空
        return instance;
    }

}
