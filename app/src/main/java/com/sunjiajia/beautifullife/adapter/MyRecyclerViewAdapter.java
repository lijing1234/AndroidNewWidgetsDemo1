/*
 *
 *  *
 *  *  *
 *  *  *  * ===================================
 *  *  *  * Copyright (c) 2016.
 *  *  *  * 作者：安卓猴
 *  *  *  * 微博：@安卓猴
 *  *  *  * 博客：http://sunjiajia.com
 *  *  *  * Github：https://github.com/opengit
 *  *  *  *
 *  *  *  * 注意**：如果您使用或者修改该代码，请务必保留此版权信息。
 *  *  *  * ===================================
 *  *  *
 *  *  *
 *  *
 *
 */

package com.sunjiajia.beautifullife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunjiajia.beautifullife.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monkey on 2015/6/29.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> implements View.OnClickListener{

  @Override
  public void onClick(View v) {
//    if (mOnItemClickListener != null) {
//              //注意这里使用getTag方法获取数据
//      mOnItemClickListener.onItemClick(v,(DataModel)v.getTag());
//    }

  }

  public interface OnItemClickListener {
    void onItemClick(View view, int position);

    void onItemLongClick(View view, int position);
  }

  public OnItemClickListener mOnItemClickListener;

  public void setOnItemClickListener(OnItemClickListener listener) {
    this.mOnItemClickListener = listener;
  }

  public Context mContext;
  public List<String> mDatas;
  public LayoutInflater mLayoutInflater;

  public MyRecyclerViewAdapter(Context mContext) {
    this.mContext = mContext;
    mLayoutInflater = LayoutInflater.from(mContext);
    mDatas = new ArrayList<>();
    for (int i = 'A'; i <= 'z'; i++) {
      mDatas.add((char) i + "");
    }
  }

  /**
   * 创建ViewHolder，被LayoutManager所调用
   */
  @Override
  public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View mView = mLayoutInflater.inflate(R.layout.item_main, parent, false);
    MyRecyclerViewHolder mViewHolder = new MyRecyclerViewHolder(mView);
    mView.setOnClickListener(this);
    return mViewHolder;
  }

  /**
   * 绑定ViewHoler，给item中的控件设置数据，将数据与界面进行绑定操作
   */
  @Override
  public void onBindViewHolder(final MyRecyclerViewHolder holder, final int position) {
    if (mOnItemClickListener != null) {
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          mOnItemClickListener.onItemClick(holder.itemView, position);

        }
      });

      holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          mOnItemClickListener.onItemLongClick(holder.itemView, position);
          return true;
        }
      });
    }
    //将数据保存在itemView的Tag中，以便点击时进行获取
    holder.itemView.setTag(mDatas.get(position));
    holder.mTextView.setText(mDatas.get(position));
  }
//获取数据的数量
  @Override
  public int getItemCount() {
    return mDatas.size();
  }

  //自定义的ViewHolder，持有每个Item的的所有界面元素
  public static class ViewHolder extends RecyclerView.ViewHolder {
           public TextView mTextView;
    public ViewHolder(View view){
      super(view);
      mTextView = (TextView) view.findViewById(R.id.text);
    }
  }

}
