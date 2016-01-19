package com.kevin.utils.samples.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.utils.samples.FunctionItem;
import com.kevin.utils.samples.R;
import com.kevin.wraprecyclerview.BaseRecyclerAdapter;

import java.util.LinkedList;

/**
 * Created by zhouwk on 2016/1/19 0019.
 *
 * 功能条目数据适配器
 */
public class ContentAdapter extends BaseRecyclerAdapter<FunctionItem, ContentAdapter.MyViewHolder> {

    public ContentAdapter(Context context) {
        super(context);
    }

    public ContentAdapter(Context mContext, LinkedList mItemLists) {
        super(mContext, mItemLists);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_function, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.text.setText(mItemLists.get(position).functionName);
        holder.position = position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        View rootView;
        TextView text;
        int position;

        public MyViewHolder(View view) {
            super(view);
            rootView = view.findViewById(R.id.home_item_root);
            text = (TextView) view.findViewById(R.id.function_item_text);

            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(v, position);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (null != onRecyclerViewListener) {
                return onRecyclerViewListener.onItemLongClick(position);
            }
            return false;
        }
    }
}
