package com.bw.guojiny.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bw.guojiny.R;
import com.bw.guojiny.adapter.OrderGroupAdapter;
import com.bw.guojiny.base.BaseFragment;
import com.bw.guojiny.bean.OrderBean;
import com.bw.guojiny.mvp.order.IOrderContract;
import com.bw.guojiny.mvp.order.OrderPresenterImpl;

import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ClassName: Guojinyi20200317
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/3/17 9:48
 * @Description: 用途：完成特定功能
 */
public class OrderFragment extends BaseFragment<OrderPresenterImpl> implements IOrderContract.IOrderView {
    @BindView(R.id.recyclerGroup)
    RecyclerView recyclerGroup;
    @BindView(R.id.tvAdd)
    TextView tvAdd;
    private Unbinder unbind;
    int i=0;
    private OrderGroupAdapter orderGroupAdapter;

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView(View v) {
        unbind = ButterKnife.bind( this, v );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getContext() );
        linearLayoutManager.setOrientation( RecyclerView.VERTICAL );
        recyclerGroup.setLayoutManager( linearLayoutManager );
        orderGroupAdapter = null;
    }

    @Override
    protected void initListener() {
        tvAdd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle arguments = getArguments();
                int status = arguments.getInt( "status" );
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put( "status", String.valueOf( status ) );
                hashMap.put( "page", "1" );
                hashMap.put( "count", "10" );
                presenter.getOrder( hashMap );
            }
        } );
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        int status = arguments.getInt( "status" );
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put( "status", String.valueOf( status ) );
        hashMap.put( "page","1");
        hashMap.put( "count", "5" );
        presenter.getOrder( hashMap );
    }

    public static OrderFragment newInstance(int status) {

        Bundle args = new Bundle();
        args.putInt( "status", status );
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    protected OrderPresenterImpl initPresenter() {
        return new OrderPresenterImpl();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbind != null) {
            unbind.unbind();
        }
    }

    @Override
    public void onSuccess(OrderBean bean) {
        if (bean != null) {
            List<OrderBean.OrderListBean> orderList = bean.getOrderList();
            if (orderList != null) {
                if (orderGroupAdapter == null) {
                    orderGroupAdapter = new OrderGroupAdapter( orderList, getContext() );
                    recyclerGroup.setAdapter( orderGroupAdapter );

                }else {
                    List<OrderBean.OrderListBean> orderList1 = bean.getOrderList();
                    orderGroupAdapter.setList( orderList1 );
                    orderGroupAdapter.notifyDataSetChanged();
                }

            }
        }
    }

    @Override
    public void onFailed(String error) {

    }
}
