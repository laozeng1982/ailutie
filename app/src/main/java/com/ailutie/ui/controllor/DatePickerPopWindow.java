package com.ailutie.ui.controllor;

/**
 * Created by deep on 8/4/17.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.ailutie.R;
import com.ailutie.constants.Constant;
import com.ailutie.fragments.PlanFragment;
import com.ailutie.tools.LogAndroid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.aigestudio.datepicker.bizs.calendars.DPCManager;
import cn.aigestudio.datepicker.bizs.decors.DPDecor;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;

/**
 * 自定义popupWindow
 *
 * @author wwj
 */
public class DatePickerPopWindow extends PopupWindow {
    private View conentView;
    private DatePicker datePicker;
    private static String selectedDate;
    private ArrayList<String> selectedDateList;

    public DatePickerPopWindow(final Activity context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.calender_layout, null);

        datePicker = (DatePicker) conentView.findViewById(R.id.calendarView2);
        Calendar now = Calendar.getInstance();
        datePicker.setDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH) + 1);
//        datePicker.set

        //set datepicker to single mode
        setDatePicker(false);

        setRecordsDate();

        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.animation);

    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, 0, 18);
        } else {
            this.dismiss();
        }
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public ArrayList<String> getSelectedDateList() {
        return selectedDateList;
    }

    public void setSelectedDateList(ArrayList<String> selectedDateList) {
        this.selectedDateList = selectedDateList;
    }

    public void setDatePicker(boolean mulitple) {
        if (mulitple) {
            this.datePicker.setMode(DPMode.MULTIPLE);
            this.datePicker.setOnDateSelectedListener(new DatePicker.OnDateSelectedListener() {
                @Override
                public void onDateSelected(List<String> date) {
                    for (String string : date) {
                        selectedDateList.add(string);
                    }
                    LogAndroid.e("Selected date: " + selectedDateList.toString());
                }
            });
        } else {
            final DatePickerPopWindow datePickerPopWindow = this;
            this.datePicker.setMode(DPMode.SINGLE);
            this.datePicker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
                @Override
                public void onDatePicked(String date) {
                    selectedDate = date;
                    LogAndroid.e("Selected date: " + date);
                    Constant.Selected_Date = date;
                    PlanFragment.updateDate(changeDateFormat(date));
                    datePickerPopWindow.dismiss();
                }
            });
        }
    }

    private String changeDateFormat(String input) {
        String output = "";
        try {
            output = Constant.sdf.format(new SimpleDateFormat("yyyy-MM-dd").parse(input));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output;
    }

    private void setRecordsDate() {
        List<String> tmpTL = new ArrayList<String>();
        tmpTL.add("2017-8-5");
        DPCManager.getInstance().setDecorTL(tmpTL);

        List<String> tmpTR = new ArrayList<String>();
        tmpTR.add("2017-8-10");
        DPCManager.getInstance().setDecorTR(tmpTR);


        datePicker.setDate(2017, 8);
        datePicker.setDPDecor(new DPDecor() {
            @Override
            public void drawDecorTL(Canvas canvas, Rect rect, Paint paint) {
                paint.setColor(Color.GREEN);

                canvas.drawRect(rect, paint);
            }

            @Override
            public void drawDecorTR(Canvas canvas, Rect rect, Paint paint) {
                paint.setColor(Color.BLUE);
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2, paint);
            }
        });

    }
}

