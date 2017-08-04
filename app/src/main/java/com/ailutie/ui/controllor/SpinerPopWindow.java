package com.ailutie.ui.controllor;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.ailutie.R;
import com.ailutie.tools.LogAndroid;
import com.ailutie.ui.adapter.AbstractSpinerAdapter;

import java.util.List;

/**
 * Created by deep on 8/1/17.
 */

public class SpinerPopWindow  extends PopupWindow implements AdapterView.OnItemClickListener {
    private Context mContext;
    private ListView mListView;
    private AbstractSpinerAdapter mAdapter;
    private AbstractSpinerAdapter.IOnItemSelectListener mItemSelectListener;


    public SpinerPopWindow(Context context) {
        super(context);
    }

    public void setItemListener(AbstractSpinerAdapter.IOnItemSelectListener listener){
        mItemSelectListener = listener;
    }

    public void setAdatper(AbstractSpinerAdapter adapter){
        mAdapter = adapter;
        mListView.setAdapter(mAdapter);
    }


    private void init()
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.spiner_window_layout, null);
        setContentView(view);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00);
        setBackgroundDrawable(dw);


        mListView = (ListView) view.findViewById(R.id.spinner_listview);
        mListView.setOnItemClickListener(this);
    }


    public <T> void refreshData(List<T> list, int selIndex)
    {
        if (list != null && selIndex  != -1)
        {
            if (mAdapter != null){
                mAdapter.refreshData(list, selIndex);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
        dismiss();
        if (mItemSelectListener != null){
            mItemSelectListener.onItemClick(pos);
        }
        LogAndroid.e(this.mListView.getAdapter().getItem(pos).toString());
    }

}
