package com.bw.guojiny.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.guojiny.R;
import com.bw.guojiny.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ClassName: Guojinyi20200317
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/3/17 10:00
 * @Description: 用途：完成特定功能
 */
public class OrderChildAdapter extends RecyclerView.Adapter<OrderChildAdapter.MyViewHodler> {

    private List<OrderBean.OrderListBean.DetailListBean> list = new ArrayList<>();
    private Context context;

    public OrderChildAdapter(List<OrderBean.OrderListBean.DetailListBean> list, Context context) {
        this.list.addAll( list );
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_order_child, parent, false );
        MyViewHodler myViewHodler = new MyViewHodler( view );
        return myViewHodler;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        OrderBean.OrderListBean.DetailListBean detailListBean = list.get( position );
        String commodityName = detailListBean.getCommodityName();
        holder.tvCommodityName.setText( commodityName );
        String commodityPic = detailListBean.getCommodityPic();
        String[] split = commodityPic.split( "," );
        Glide.with( context ).load( split[0] )
                .error( R.mipmap.ic_launcher )
                .placeholder( R.mipmap.ic_launcher_round )
                .into( holder.ivCommodityPic );
        int commodityCount = detailListBean.getCommodityCount();
        holder.tvSum.setText( "x"+commodityCount );
        int commodityPrice = detailListBean.getCommodityPrice();
        holder.tvCommodityPrice.setText( "￥"+commodityPrice+".0" );

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
        private ImageView ivCommodityPic;
        private TextView tvCommodityName;
        private TextView tvCommodityPrice;
        private TextView tvSum;

        public MyViewHodler(@NonNull View itemView) {
            super( itemView );
            ivCommodityPic = itemView.findViewById( R.id.ivCommodityPic );
            tvCommodityName = itemView.findViewById( R.id.tvCommodityName );
            tvCommodityPrice = itemView.findViewById( R.id.tvCommodityPrice );
            tvSum = itemView.findViewById( R.id.tvSum );
        }
    }
}
