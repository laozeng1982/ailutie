package com.ailutie.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.ailutie.R;
import com.ailutie.activity.MainActivity;
import com.ailutie.constants.Constant;
import com.ailutie.datamodel.bean.Movement;
import com.ailutie.datamodel.bean.TrainPlan;
import com.ailutie.datamodel.json.GsonUtils;
import com.ailutie.tools.LogAndroid;
import com.ailutie.ui.adapter.MovementAdapter;
import com.ailutie.ui.controllor.DatePickerPopWindow;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlanFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "PlanFragment";
    private View thisView;
    private Button btnSelectLastDay;
    private static TextView textShowCalender;
    private Button btnSelectNextDay;
    private ImageButton imgBtnAddMovement;
    private Spinner spinnerBodyPartChoser;
    private Button btnDonePlan;
    private Switch switchBtn_measurement;
    private TextView textView_movement_measurement;
    private ListView tableListView;

    //定义DatePicker PopupWindow
    private DatePickerPopWindow datePickerPopWindow;

    private String today;

    private Calendar currentSelectCalender = Calendar.getInstance();

    //dynamic add movement class
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    //add movement
    List<Movement> planList = new ArrayList<Movement>();

    public PlanFragment() {
    }

    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mFragmentManager = getActivity().getFragmentManager();

        final View planLayout = inflater.inflate(R.layout.plan_layout,
                container, false);

        //First collect view items
        collectViewItem(planLayout);
        //Then, set listeners if necessary
        setListener(planLayout);

        thisView = planLayout;

        return planLayout;
    }

    private void collectViewItem(View view) {

        btnSelectLastDay = (Button) view.findViewById(R.id.btn_last_day);

        textShowCalender = (TextView) view.findViewById(R.id.text_select_day);
        textShowCalender.setText(Constant.sdf.format(new Date()));

        datePickerPopWindow = new DatePickerPopWindow(MainActivity.getInstance());

        btnSelectNextDay = (Button) view.findViewById(R.id.btn_next_day);

        // measurement unit control
        textView_movement_measurement = (TextView) view.findViewById(R.id.text_movement_measurement);

        switchBtn_measurement = (Switch) view.findViewById(R.id.switch_measurement);

        // prepare for select body part
        spinnerBodyPartChoser = (Spinner) view.findViewById(R.id.spinner_movement_bodypart);

        tableListView = (ListView) view.findViewById(R.id.list);
        MovementAdapter adapter = new MovementAdapter(MainActivity.getInstance(), planList);
        tableListView.setAdapter(adapter);

        imgBtnAddMovement = (ImageButton) view.findViewById(R.id.imgbtn_add_movement);

        btnDonePlan = (Button) view.findViewById(R.id.btn_confirm);

    }

    private void setListener(final View view) {
        btnSelectLastDay.setOnClickListener(this);
        textShowCalender.setOnClickListener(this);
        btnSelectNextDay.setOnClickListener(this);

        switchBtn_measurement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    textView_movement_measurement.setText("Lbs");
                } else {
                    textView_movement_measurement.setText("Kg");
                }
            }
        });

        imgBtnAddMovement.setOnClickListener(this);

        btnDonePlan.setOnClickListener(this);

        tableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LogAndroid.e(view.getClass().toString());
                LogAndroid.e(view.getTag().toString());
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        today = Constant.sdf.format(Calendar.getInstance().getTime());
//        initData();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        MainActivity.currFragTag = Constant.FRAGMENT_FLAG_PLAN;
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
//		Log.e(TAG, "onpause");
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
//		Log.e(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
//		Log.e(TAG, "ondestoryView");
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
//		Log.e(TAG, "ondestory");
    }

    @Override
    public void onDetach() {
        // TODO Auto-generated method stub
        super.onDetach();
//		Log.d(TAG, "onDetach------");

    }

    public static void updateDate(String date) {

        LogAndroid.e(date);
        textShowCalender.setText(date);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //select last day
            case R.id.btn_last_day:
                selectLastDay();
                break;
            //select one day
            case R.id.text_select_day:
                selectOneDay();
                break;
            //select next
            case R.id.btn_next_day:
                selectNextDay();
                break;
            //点击更多按钮
            case R.id.imgbtn_add_movement:
                addMovement();
                break;
            //点击中间按钮
            case R.id.btn_confirm:
                donePlan();
                break;
        }
    }

    private void selectLastDay() {
        //TODO add action when days selected
        int day = currentSelectCalender.get(Calendar.DATE);
        currentSelectCalender.set(Calendar.DATE, day - 1);
        textShowCalender.setText(Constant.sdf.format(currentSelectCalender.getTime()));
    }

    private void selectNextDay() {
        //TODO add action when days selected
        int day = currentSelectCalender.get(Calendar.DATE);
        currentSelectCalender.set(Calendar.DATE, day + 1);
        textShowCalender.setText(Constant.sdf.format(currentSelectCalender.getTime()));
    }

    private void selectOneDay() {
        showPopupWindow(thisView);
        LogAndroid.e(Constant.Selected_Date);
        textShowCalender.setText(Constant.Selected_Date);

    }

    private void addMovement() {
        int newId = planList.size() + 1;
        Movement movement = new Movement(newId + "", spinnerBodyPartChoser.getSelectedItem().toString());
        //add a movement
        planList.add(movement);
        LogAndroid.e(tableListView.getAdapter().toString());
        LogAndroid.e(tableListView.getAdapter().getClass().toString());
        LogAndroid.e(tableListView.getAdapter().getItem(0).toString());
        LogAndroid.e(tableListView.getAdapter().getCount());
    }

    private void donePlan() {
        ArrayList<Movement> movements = new ArrayList<Movement>();
        for (int i = 0; i < tableListView.getAdapter().getCount(); i++) {
            Movement movement = (Movement) tableListView.getAdapter().getItem(i);
            MovementAdapter adapter1 = (MovementAdapter) tableListView.getAdapter();
            String name = adapter1.getmViewHolder().movementName.getSelectedItem().toString();
            LogAndroid.e(name + "," + adapter1.getmViewHolder().movementGroupCount.getText());

            LogAndroid.e(new Gson().toJson(movement));
            movements.add(movement);

        }
        planList.clear();
        planList.addAll(movements);
        Constant.APP_USER.getTrainPlans().add(new TrainPlan(new Date(), Constant.APP_USER.getName(), movements));
        GsonUtils.addUsers(Constant.USER_DATA_PATH, Constant.APP_USER);
    }

    /**
     * 显示PopupWindow弹出菜单
     *
     * @param parent
     */
    private void showPopupWindow(View parent) {
        datePickerPopWindow.showPopupWindow(parent.findViewById(R.id.text_select_day));
    }
}
