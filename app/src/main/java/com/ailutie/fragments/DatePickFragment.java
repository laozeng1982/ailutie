package com.ailutie.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.ailutie.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by H134959 on 3/8/2016.
 */
public class DatePickFragment extends DialogFragment {

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

    public interface DateSelectListener {
        void onDateSelectComplete(Date date);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //设置自定义的title  layout
        getDialog().getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.date_pick_title);
        View view = inflater.inflate(R.layout.date_picker, container);
        LogUtils.e("in DatePicker", "CalendarView Created");
        mCalendarView = (CalendarView) view.findViewById(R.id.calendarView);
        Long nowTime = mCalendarView.getDate();
        final SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String time = f.format(nowTime);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                LogUtils.e("in DatePicker", "Date Selected is: " + f.format(mCalendarView.getDate()));
                Toast.makeText(getActivity(),  "is Picked", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        //TODO
        super.onResume();
    }*/

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.calender_layout, null);

        mCalendarView = (CalendarView) view.findViewById(R.id.calendarView2);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                DATE_CHOSED = simpleDateFormat.format(mCalendarView.getDate());
//                LogUtils.e("in DatePicker", "Date Selected is: " + DATE_CHOSED);
                Toast.makeText(getActivity(), "您选择了： " + DATE_CHOSED, Toast.LENGTH_SHORT).show();

            }
        });
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //TODO，日期选中，如果有记录，调取选中日期的记录，并显示；如果没有弹出没有记录
                                PlanFragment.updateDate(getSelectedDateString());
//                                DateSelectListener listener= (DateSelectListener) PlanFragment ;
//                                listener.onDateSelectComplete(mCalendarView.getDate());
                                DATE_CHOSED = simpleDateFormat.format(mCalendarView.getDate());
                            }
                        }).setNegativeButton("Cancel", null);
        return builder.create();
    }
}