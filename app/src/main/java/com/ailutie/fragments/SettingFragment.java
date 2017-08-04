package com.ailutie.fragments;

import com.ailutie.activity.MainActivity;
import com.ailutie.constants.Constant;

import com.ailutie.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View settingLayout = inflater.inflate(R.layout.setting_layout,
				container, false);
		return settingLayout;
	}
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		MainActivity.currFragTag = Constant.FRAGMENT_FLAG_SETTING;
		
	}
	

}
