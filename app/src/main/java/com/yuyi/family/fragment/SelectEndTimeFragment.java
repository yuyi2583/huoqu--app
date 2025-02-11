package com.yuyi.family.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yuyi.family.R;
import com.yuyi.family.adapter.NumericWheelAdapter;
import com.yuyi.family.common.util.ApplicationUtil;
import com.yuyi.family.common.util.TimeUtil;
import com.yuyi.family.common.util.ToastUtil;
import com.yuyi.family.component.WheelView;
import com.yuyi.family.listener.interfaces.OnSelectEndTimeListener;

import java.util.Calendar;
import java.util.Locale;

public class SelectEndTimeFragment extends Fragment {

    public SelectEndTimeFragment(){}
    private WheelView hour;
    private WheelView mins;
    private Context context;
    private static OnSelectEndTimeListener onSelectEndTimeListener;
    public static void setOnSelectEndTimeListener(OnSelectEndTimeListener onSelectEndTimeListener1){
        onSelectEndTimeListener=onSelectEndTimeListener1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_end_time_fragment, container, false);
        context= ApplicationUtil.getContext();
        Calendar c = Calendar.getInstance();
        int curHour = c.get(Calendar.HOUR_OF_DAY);
        int curMin = c.get(Calendar.MINUTE);


        hour = (WheelView) view.findViewById(R.id.end_hour);
        initHour();
        mins = (WheelView) view.findViewById(R.id.end_mins);
        initMins();
        // 设置当前时间
        hour.setCurrentItem(curHour);
        mins.setCurrentItem(curMin);


        hour.setVisibleItems(7);
        mins.setVisibleItems(7);

        // 设置监听
        Button ok = (Button) view.findViewById(R.id.end_set);
        Button cancel = (Button) view.findViewById(R.id.end_cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String str = TimeUtil.getFormatTime(hour.getCurrentItem(), mins.getCurrentItem());
                onSelectEndTimeListener.onSelectEndTime(str);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            }
        });
        return view;
    }

    /**
     * 初始化时
     */
    private void initHour() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(context,0, 23, "%02d");
        numericWheelAdapter.setLabel(" 时");
        //		numericWheelAdapter.setTextSize(15);  设置字体大小
        hour.setViewAdapter(numericWheelAdapter);
        hour.setCyclic(true);
    }

    /**
     * 初始化分
     */
    private void initMins() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(context,0, 59, "%02d");
        numericWheelAdapter.setLabel(" 分");
//		numericWheelAdapter.setTextSize(15);  设置字体大小
        mins.setViewAdapter(numericWheelAdapter);
        mins.setCyclic(true);
    }
}
