package com.bw.guojiny.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.guojiny.R;
import com.bw.guojiny.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ClassName: Guojinyi20200317
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/3/17 10:00
 * @Description: 用途：完成特定功能
 */
public class OrderGroupAdapter extends RecyclerView.Adapter<OrderGroupAdapter.MyViewHodler> {

    private List<OrderBean.OrderListBean> list = new ArrayList<>();
    private Context context;


    public OrderGroupAdapter(List<OrderBean.OrderListBean> list, Context context) {
        this.list.addAll( list );
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_order_group, parent, false );
        MyViewHodler myViewHodler = new MyViewHodler( view );
        return myViewHodler;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        OrderBean.OrderListBean orderListBean = list.get( position );

        holder.tvOrderId.setText( orderListBean.getOrderId() );


        List<OrderBean.OrderListBean.DetailListBean> detailList = orderListBean.getDetailList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( context );
        linearLayoutManager.setOrientation( RecyclerView.VERTICAL );
        holder.recyclerChild.setLayoutManager( linearLayoutManager );
        OrderChildAdapter orderChildAdapter = new OrderChildAdapter( detailList, context );
        holder.recyclerChild.setAdapter( orderChildAdapter );

        OrderBean.OrderListBean.DetailListBean detailListBean = detailList.get( 0 );
        int commodityCount = detailListBean.getCommodityCount();
        int commodityPrice = detailListBean.getCommodityPrice();
        holder.tvYF.setText( "共" + commodityCount + "件商品，共计" + commodityPrice + "元(包含运费)" );

        //根据订单状态展示不同信息
        int status = orderListBean.getOrderStatus();
        switch (status) {
            case 1:
                holder.tvZT.setText( "待支付" );
                holder.aaa.setVisibility( View.GONE );
                holder.bbb.setVisibility( View.GONE );
                holder.ccc.setVisibility( View.VISIBLE );
                holder.ddd.setVisibility( View.GONE );
                holder.eee.setVisibility( View.GONE );
                break;
            case 2:
                holder.tvZT.setText( "待收货" );
                holder.aaa.setVisibility( View.GONE );
                holder.bbb.setVisibility( View.GONE );
                holder.ccc.setVisibility( View.GONE );
                holder.ddd.setVisibility( View.VISIBLE );
                holder.eee.setVisibility( View.GONE );
                break;
            case 3:
                holder.tvZT.setText( "待评价" );
                holder.aaa.setVisibility( View.GONE );
                holder.bbb.setVisibility( View.VISIBLE );
                holder.ccc.setVisibility( View.GONE );
                holder.ddd.setVisibility( View.GONE );
                holder.eee.setVisibility( View.GONE );
                break;
            case 9:
                holder.tvZT.setText( "已完成" );
                holder.aaa.setVisibility( View.GONE );
                holder.bbb.setVisibility( View.GONE );
                holder.ccc.setVisibility( View.GONE );
                holder.ddd.setVisibility( View.GONE );
                holder.eee.setVisibility( View.VISIBLE );
                break;
        }

        holder.btnLjfk.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( context, "立即付款", Toast.LENGTH_SHORT ).show();
            }
        } );
        holder.btnQPJ.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( context, "去支付", Toast.LENGTH_SHORT ).show();
            }
        } );
        holder.btnQpj.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( context, "去评价", Toast.LENGTH_SHORT ).show();
            }
        } );
        holder.btnQrsh.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( context, "确认收货", Toast.LENGTH_SHORT ).show();
            }
        } );
        holder.btnQrsh1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( context, "确认收货", Toast.LENGTH_SHORT ).show();
            }
        } );
        holder.btnScdd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( context, "删除订单", Toast.LENGTH_SHORT ).show();
            }
        } );
        holder.btnTH.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( context, "退货", Toast.LENGTH_SHORT ).show();
            }
        } );
        holder.btnQPJ.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( context, "去评价", Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return list.size();
    }



    class MyViewHodler extends RecyclerView.ViewHolder {
        private TextView tvOrderId;
        private TextView tvZT;
        private RecyclerView recyclerChild;
        private TextView tvYF;
        private RelativeLayout aaa;
        private RelativeLayout bbb;
        private RelativeLayout ccc;
        private RelativeLayout ddd;
        private RelativeLayout eee;
        private Button btnQpj;
        private Button btnQrsh;
        private Button btnLjfk;
        private Button btnScdd;
        private Button btnQrsh1;
        private Button btnTH;
        private Button btnQPJ;
        private Button btnScdd1;
        public MyViewHodler(@NonNull View itemView) {
            super( itemView );

            tvOrderId = itemView.findViewById( R.id.tvOrderId );
            tvZT = itemView.findViewById( R.id.tvZT );
            recyclerChild = itemView.findViewById( R.id.recyclerChild );
            tvYF = itemView.findViewById( R.id.tvYF );
            aaa = itemView.findViewById( R.id.aaa );
            bbb = itemView.findViewById( R.id.bbb );
            ccc = itemView.findViewById( R.id.ccc );
            ddd = itemView.findViewById( R.id.ddd );
            eee = itemView.findViewById( R.id.eee );
            btnQpj = itemView.findViewById( R.id.btnQpj );
            btnQrsh = itemView.findViewById( R.id.btnQrsh );
            btnLjfk = itemView.findViewById( R.id.btnLjfk );
            btnScdd = itemView.findViewById( R.id.btnScdd );
            btnQrsh1 = itemView.findViewById( R.id.btnQrsh_1 );
            btnTH = itemView.findViewById( R.id.btnTH );
            btnQPJ = itemView.findViewById( R.id.btnQPJ );
            btnScdd1 = itemView.findViewById( R.id.btnScdd_1 );
        }
    }

    public List<OrderBean.OrderListBean> getList() {
        return list;
    }

    public void setList(List<OrderBean.OrderListBean> list) {
        this.list.clear();
        this.list = list;
    }
}
