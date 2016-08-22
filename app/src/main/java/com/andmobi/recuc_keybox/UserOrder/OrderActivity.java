package com.andmobi.recuc_keybox.userOrder;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andmobi.recuc_keybox.R;
import com.andmobi.recuc_keybox.util.Utils;
import com.trello.rxlifecycle.components.RxActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.startCountdown();
    }

    @Override
    public void onHideSetList() {
        lvKeyboxlistSet.setVisibility(View.GONE);
        tvNotSet.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideGetList() {
        lvKeyboxlistGet.setVisibility(View.GONE);
        tvNotGet.setVisibility(View.VISIBLE);
    }

    @Override
    public void onShowSetList() {
        lvKeyboxlistSet.setVisibility(View.VISIBLE);
        tvNotSet.setVisibility(View.GONE);
    }

    @Override
    public void onShowGetList() {
        lvKeyboxlistGet.setVisibility(View.VISIBLE);
        tvNotGet.setVisibility(View.GONE);
    }

    @Override
    public void onSetNumber(Long number) {
        tvNumber.setText(String.valueOf(number));
    }

    @Override
    public RxActivity getThis() {
        return this;
    }

    @Override
    public void onFinish() {
        finish();
    }

}
