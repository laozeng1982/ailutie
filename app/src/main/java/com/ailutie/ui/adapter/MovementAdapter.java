package com.ailutie.ui.adapter;

/**
 * Created by deep on 7/31/17.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ailutie.R;
import com.ailutie.activity.MainActivity;
import com.ailutie.datamodel.bean.Movement;
import com.ailutie.tools.LogAndroid;

import java.util.HashMap;
import java.util.List;


public class MovementAdapter extends BaseAdapter {

    private List<Movement> list;
    private LayoutInflater inflater;
    private ViewHolder mViewHolder;
    private OnChangedTextListener onChangedTextListener;
    private final HashMap<Integer, String> mData = new HashMap<Integer, String>();
    private int mTouchItemPosition = -1;

    public MovementAdapter(Context context, List<Movement> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"DefaultLocale", "ClickableViewAccessibility"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movement item = (Movement) this.getItem(position);

        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.plan_list_item, null);
            viewHolder.movementId = (TextView) convertView.findViewById(R.id.text_movement_id);
            viewHolder.movementBodyPart = (TextView) convertView.findViewById(R.id.text_movement_bodypart);
            viewHolder.movementName = (Spinner) convertView.findViewById(R.id.spinner_movement_name);
            viewHolder.movementGroupCount = (EditText) convertView.findViewById(R.id.text_movement_group_count);
            viewHolder.movementCount = (TextView) convertView.findViewById(R.id.text_movement_count);
            viewHolder.movementWeight = (TextView) convertView.findViewById(R.id.text_movement_weight);

            viewHolder.setListener();

            viewHolder.mTextWatcher = new TextChangeWatch();
            //设置数据监听
            viewHolder.movementGroupCount.addTextChangedListener(viewHolder.mTextWatcher);
            viewHolder.upDataPosition(position);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.upDataPosition(position);
        }

        viewHolder.movementId.setText(item.getId());

        viewHolder.movementBodyPart.setText(item.getBodyPart());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                MainActivity.getInstance(), item.getSpinnerAdapterSource(),
                android.R.layout.simple_spinner_dropdown_item);
        viewHolder.movementName.setAdapter(adapter);
        if (item.getName() == null || item.getName().isEmpty()) {
            viewHolder.movementName.setSelection(0);
        } else {
            LogAndroid.e(item.getName());
            for (int i = 0; i < adapter.getCount(); i++) {
                LogAndroid.e(adapter.getItem(i));
                //if (item)
            }
        }


        viewHolder.movementGroupCount.setText(mData.get(position));
        viewHolder.movementGroupCount.setTag(position);

        viewHolder.movementGroupCount.addTextChangedListener(viewHolder.mTextWatcher);
        viewHolder.upDataPosition(position);

        viewHolder.movementGroupCount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //使用getTag 记录position
//                mTouchItemPosition = (int) v.getTag();
                return false;
            }
        });

        viewHolder.movementCount.setText(item.getPlanMovementCount() + "");

        viewHolder.movementWeight.setText(item.getPlanMovementWeight() + "");


        /**
         * 解决焦点问题
         * 当editView获取焦点时,listview会重新绘制,致使editView的焦点光标失去
         */
        if (mTouchItemPosition == position) {
            viewHolder.movementGroupCount.requestFocus();
            viewHolder.movementGroupCount.setSelection(viewHolder.movementGroupCount.getText().length());
        } else {
            viewHolder.movementGroupCount.clearFocus();
        }

        mViewHolder = viewHolder;

        return convertView;
    }

    public ViewHolder getmViewHolder() {
        return mViewHolder;
    }

    public void setmViewHolder(ViewHolder mViewHolder) {
        this.mViewHolder = mViewHolder;
    }



    public static class ViewHolder implements View.OnClickListener {
        public TextView movementId;
        public TextView movementBodyPart;
        public Spinner movementName;
        public EditText movementGroupCount;
        public TextView movementCount;
        public TextView movementWeight;

        TextChangeWatch mTextWatcher;

        public void setListener() {
            movementGroupCount.setOnClickListener(this);
        }

        //记录position,防止数据复用时,错乱
        public void upDataPosition(int position) {
            mTextWatcher.upDataPosition(position);
        }

        @Override
        public void onClick(View view) {
            LogAndroid.e(view.getClass().toString());
        }
    }

    class TextChangeWatch implements TextWatcher {
        private int position;

        public void upDataPosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mData.put(position, s.toString());
            if (onChangedTextListener != null) {
                //使用回调将数据传递出去,进行数据检测
                onChangedTextListener.onChangedTextListener(position, s.toString());
            }
        }
    }

    public void setOnChangedTextListener(OnChangedTextListener onChangedTextListener) {
        this.onChangedTextListener = onChangedTextListener;
    }

    interface OnChangedTextListener {
        void onChangedTextListener(int position, String str);
    }
}  