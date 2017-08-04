package com.ailutie.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.ailutie.R;
import com.ailutie.activity.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by H134959 on 3/8/2016.
 */
public class DatePickPop extends PopupWindow {

    private CalendarView mCalendarView;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");

    private static String DATE_CHOSED = simpleDateFormat.format(Calendar.getInstance().getTime());

    public static String getSelectedDateString() {
        return DATE_CHOSED;
    }

    public static Date getSelectedDate() {
        return Calendar.getInstance().getTime();
    }

    public static void setDateChosed(String dateChosed) {
        DATE_CHOSED = dateChosed;
    }

    public DatePickPop(View view) {
        init(view);
    }

    public void init(View view) {


        View contentView = LayoutInflater.from(MainActivity.getInstance()).inflate(R.layout.calender_layout,
                null, true);
        this.setContentView(contentView);
//        this.setW
        this.setFocusable(true);
        this.setOutsideTouchable(true); // 设置允许在外点击消失
//        this.setBackgroundDrawable(new BitmapDrawable(MainActivity.getInstance().getResources(), "")); // 点击返回键也能使其消失，不影响背景
        this.setAnimationStyle(R.style.animation);// 设置PopupWindow弹出和退出时候的动画效果
        mCalendarView = (CalendarView) contentView.findViewById(R.id.calendarView2);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                DATE_CHOSED = simpleDateFormat.format(mCalendarView.getDate());
//                LogUtils.e("in DatePicker", "Date Selected is: " + DATE_CHOSED);
                Toast.makeText(MainActivity.getInstance(), "您选择了： " + DATE_CHOSED, Toast.LENGTH_SHORT).show();

            }
        });

    }
}