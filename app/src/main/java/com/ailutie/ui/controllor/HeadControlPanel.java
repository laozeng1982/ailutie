package com.ailutie.ui.controllor;

import android.app.FragmentManager;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ailutie.R;
import com.ailutie.activity.MainActivity;
import com.ailutie.constants.Constant;
import com.ailutie.fragments.LoginFragment;

public class HeadControlPanel extends RelativeLayout {

    private Context mContext;
    private FragmentManager mFragmentManager;
    private ImageButton mImgBtnSetUser;
    private TextView mMidleTitle;
    private TextView mRightTitle;
    private static final float middle_title_size = 20f;
    private static final float right_title_size = 17f;
//    private static final int default_background_color = Color.rgb(21, 126, 203);

    public HeadControlPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onFinishInflate() {
        // TODO Auto-generated method stub
        super.onFinishInflate();
        mImgBtnSetUser = (ImageButton) findViewById(R.id.imgBtn_setUser);
        mImgBtnSetUser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentManager = MainActivity.getInstance().getFragmentManager();
                LoginFragment loginFragment = new LoginFragment();
                loginFragment.show(mFragmentManager, "Date Pick");
            }
        });
        mMidleTitle = (TextView) findViewById(R.id.midle_title);
//		mRightTitle = (TextView) findViewById(R.id.right_title);
//        setBackgroundColor(default_background_color);
    }

    public void initHeadPanel() {

        if (mMidleTitle != null) {
            setMiddleTitle(Constant.FRAGMENT_FLAG_PLAN);
        }
    }

    public void setMiddleTitle(String s) {
        mMidleTitle.setText(s);
        mMidleTitle.setTextSize(middle_title_size);
    }


}
