package com.andmobi.recuc_keybox.userOrder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andmobi.recuc_keybox.R;
import com.andmobi.recuc_keybox.modle.UserOrder;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Description:
 * Created by andmobi003 on 2016/8/23 15:06
 */
public class OrderListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;// 动态布局映射
    List<UserOrder> mUserOrders;
    int optType;//取或放标识
    Context mContext;

    OrderListAdapter(List<UserOrder> userOrders, Context context, int optType) {
        mUserOrders = userOrders;
        mContext = context;
        this.optType = optType;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mUserOrders.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.activity_key_set_and_get_new_item, null);
            holder.tv_id = (TextView) convertView.findViewById(R.id.tv_dingdanhao);
            holder.tv_carid = (TextView) convertView.findViewById(R.id.iv_carkey);
            holder.tv_time = (TextView) convertView.findViewById(R.id.iv_time);
            holder.ll_but_key = (LinearLayout) convertView.findViewById(R.id.ll_but_key);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        UserOrder userOrder = mUserOrders.get(i);
        holder.tv_id.setText(userOrder.getOrderNo());//订单号
        holder.tv_carid.setText(userOrder.getCarNo());//车牌

        if (userOrder.getCreateDate().contains("-")) {//是否日期格式
            holder.tv_time.setText(userOrder.getCreateDate());//时间
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(Long.valueOf(userOrder.getCreateDate()));
            holder.tv_time.setText(time);//时间
        }
        holder.ll_but_key.setBackgroundResource(R.drawable.login_cor_bg_enter);
        return convertView;
    }

    class ViewHolder {
        TextView tv_id;
        TextView tv_carid;
        TextView tv_time;
        LinearLayout ll_but_key;
    }
}
