package com.ailutie.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ailutie.R;
import com.ailutie.datamodel.bean.TrainPlan;

import java.util.List;

public class TrainingDataAdapter extends BaseAdapter {
	private List<TrainPlan> mListTrainBean = null;
	private Context mContext;
	private LayoutInflater mInflater;
//	public TrainingDataAdapter(List<TrainingDataBean> listMsgBean, Context context){
//		mListTrainBean = listMsgBean;
//		mContext = context;
//		mInflater = LayoutInflater.from(mContext);
//	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListTrainBean.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mListTrainBean.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = mInflater.inflate(R.layout.training_list_item, null);
//
//		ImageView imageView = (ImageView) v.findViewById(R.id.img_msg_item);
//		imageView.setImageResource(mListTrainBean.get(position).getPhotoDrawableId());
//
//		TextView nameMsg = (TextView)v.findViewById(R.id.name_msg_item);
//		nameMsg.setText(mListTrainBean.get(position).getMessageName());
//
//		TextView contentMsg = (TextView)v.findViewById(R.id.content_msg_item);
//		contentMsg.setText(mListTrainBean.get(position).getMessageContent());
//
//		TextView timeMsg = (TextView)v.findViewById(R.id.time_msg_item);
//		timeMsg.setText(mListTrainBean.get(position).getMessageTime());
//
		return v;
	}

}
