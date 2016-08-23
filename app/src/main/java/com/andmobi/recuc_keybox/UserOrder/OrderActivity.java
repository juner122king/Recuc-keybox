package com.andmobi.recuc_keybox.userOrder;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andmobi.recuc_keybox.R;
import com.andmobi.recuc_keybox.modle.UserOrder;
import com.andmobi.recuc_keybox.util.Utils;
import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.components.RxActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class OrderActivity extends RxActivity implements OrderContract.View {

    OrderContract.Presenter mPresenter;
    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.iv_topbar_title)
    TextView ivTopbarTitle;
    @Bind(R.id.tv_backlogin)
    TextView tvBacklogin;
    @Bind(R.id.rl_contentview)
    RelativeLayout rlContentview;
    @Bind(R.id.lv_keyboxlist_set)
    ListView lvKeyboxlistSet;
    @Bind(R.id.tv_not_set)
    TextView tvNotSet;
    @Bind(R.id.lv_keyboxlist_get)
    ListView lvKeyboxlistGet;
    @Bind(R.id.tv_not_get)
    TextView tvNotGet;
    @Bind(R.id.tv_number)
    TextView tvNumber;

    @Override
    public void setPresenter(OrderContract.Presenter presenter) {
        mPresenter = Utils.checkNotNull(presenter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        mPresenter = new OrderPresenter(this);
        mPresenter.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.startCountdown();
    }

    @Override
    public void onInitView() {
        RxView.clicks(tvBacklogin).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                onFinish();
            }
        });
        mPresenter.getOrderList();
        mPresenter.setOrderList();
    }

    @Override
    public void onShowGetOrderListData(List<UserOrder> orderList, int optType) {
        lvKeyboxlistGet.setAdapter(new OrderListAdapter(orderList, this, optType));
    }

    @Override
    public void onShowSetOrderListData(List<UserOrder> orderList, int optType) {
        lvKeyboxlistSet.setAdapter(new OrderListAdapter(orderList, this, optType));
    }


    @Override
    public void onHideSetListView() {
        lvKeyboxlistSet.setVisibility(View.GONE);
        tvNotSet.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideGetListView() {
        lvKeyboxlistGet.setVisibility(View.GONE);
        tvNotGet.setVisibility(View.VISIBLE);
    }

    @Override
    public void onShowSetListView() {
        lvKeyboxlistSet.setVisibility(View.VISIBLE);
        tvNotSet.setVisibility(View.GONE);
    }

    @Override
    public void onShowGetListView() {
        lvKeyboxlistGet.setVisibility(View.VISIBLE);
        tvNotGet.setVisibility(View.GONE);
    }

    @Override
    public void onSetNumber(Long number) {
        tvNumber.setText(String.valueOf("剩余操作时间: " + number));

    }

    @Override
    public RxActivity getThis() {
        return this;
    }

    @Override
    public String getToken() {
        String Token = getIntent().getStringExtra("token");
        return Token;
    }

    @Override
    public void onFinish() {
        finish();
    }


}
