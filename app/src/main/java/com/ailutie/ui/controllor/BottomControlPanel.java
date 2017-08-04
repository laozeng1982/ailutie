package com.ailutie.ui.controllor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.ailutie.R;
import com.ailutie.constants.Constant;

import java.util.ArrayList;
import java.util.List;

public class BottomControlPanel extends RelativeLayout implements View.OnClickListener {
	private Context mContext;
	private ImageText mPlanBtn = null;
	private ImageText mTrainBtn = null;
	private ImageText mCommunityBtn = null;
	private ImageText mSettingBtn = null;
//	private int DEFALUT_BACKGROUND_COLOR = Color.rgb(243, 243, 243); //Color.rgb(192, 192, 192)
	private BottomPanelCallback mBottomCallback = null;
	private List<ImageText> viewList = new ArrayList<ImageText>();

	public interface BottomPanelCallback{
		public void onBottomPanelClick(int itemId);
	}
	public BottomControlPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		mPlanBtn = (ImageText) findViewById(R.id.btn_plan);
		mTrainBtn = (ImageText) findViewById(R.id.btn_train);
		mCommunityBtn = (ImageText) findViewById(R.id.btn_community);
		mSettingBtn = (ImageText) findViewById(R.id.btn_setting);
//		setBackgroundColor(DEFALUT_BACKGROUND_COLOR);
		viewList.add(mPlanBtn);
		viewList.add(mTrainBtn);
		viewList.add(mCommunityBtn);
		viewList.add(mSettingBtn);

	}
	public void initBottomPanel(){
		if(mPlanBtn != null){
			mPlanBtn.setImage(R.drawable.plan_unselected);
			mPlanBtn.setText("计划");
		}
		if(mTrainBtn != null){
			mTrainBtn.setImage(R.drawable.train_unselected);
			mTrainBtn.setText("撸铁中");
		}
		if(mCommunityBtn != null){
			mCommunityBtn.setImage(R.drawable.community_unselected);
			mCommunityBtn.setText("找基友");
		}
		if(mSettingBtn != null){
			mSettingBtn.setImage(R.drawable.setting_unselected);
			mSettingBtn.setText("我的");
		}
		setBtnListener();

	}
	private void setBtnListener(){
		int num = this.getChildCount();
		for(int i = 0; i < num; i++){
			View v = getChildAt(i);
			if(v != null){
				v.setOnClickListener(this);
			}
		}
	}
	public void setBottomCallback(BottomPanelCallback bottomCallback){
		mBottomCallback = bottomCallback;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		initBottomPanel();
		int index = -1;
		switch(v.getId()){
		case R.id.btn_plan:
			index = Constant.BTN_FLAG_PLAN;
			mPlanBtn.setChecked(Constant.BTN_FLAG_PLAN);
			break;
		case R.id.btn_train:
			index = Constant.BTN_FLAG_TRAIN;
			mTrainBtn.setChecked(Constant.BTN_FLAG_TRAIN);
			break;
		case R.id.btn_community:
			index = Constant.BTN_FLAG_COMMUNITY;
			mCommunityBtn.setChecked(Constant.BTN_FLAG_COMMUNITY);
			break;
		case R.id.btn_setting:
			index = Constant.BTN_FLAG_SETTING;
			mSettingBtn.setChecked(Constant.BTN_FLAG_SETTING);
			break;
		default:break;
		}
		if(mBottomCallback != null){
			mBottomCallback.onBottomPanelClick(index);
		}
	}
	public void defaultBtnChecked(){
		if(mPlanBtn != null){
			mPlanBtn.setChecked(Constant.BTN_FLAG_PLAN);
		}
	}
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
		layoutItems(left, top, right, bottom);
	}
	/**����ߺ����ұߵ�view��ĸ���ֵ�padding���п���λ�á�������Ե�2��3��view��λ����������
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	private void layoutItems(int left, int top, int right, int bottom){
		int n = getChildCount();
		if(n == 0){
			return;
		}
		int paddingLeft = getPaddingLeft();
		int paddingRight = getPaddingRight();
//		Log.i("yanguoqi", "paddingLeft = " + paddingLeft + " paddingRight = " + paddingRight);
		int width = right - left;
		int height = bottom - top;
//		Log.i("yanguoqi", "width = " + width + " height = " + height);
		int allViewWidth = 0;
		for(int i = 0; i< n; i++){
			View v = getChildAt(i);
//			Log.i("yanguoqi", "v.getWidth() = " + v.getWidth());
			allViewWidth += v.getWidth();
		}
		int blankWidth = (width - allViewWidth - paddingLeft - paddingRight) / (n - 1);
//		Log.i("yanguoqi", "blankV = " + blankWidth );

		LayoutParams params1 = (LayoutParams) viewList.get(1).getLayoutParams();
		params1.leftMargin = blankWidth;
		viewList.get(1).setLayoutParams(params1);

		LayoutParams params2 = (LayoutParams) viewList.get(2).getLayoutParams();
		params2.leftMargin = blankWidth;
		viewList.get(2).setLayoutParams(params2);
	}



}
