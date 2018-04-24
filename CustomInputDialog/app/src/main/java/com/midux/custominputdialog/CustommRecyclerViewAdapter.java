package com.midux.custominputdialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yu_midux on 2018/4/20.
 */

public class CustommRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<String> pic_data = new ArrayList<>();
    List<CommentBean> comment_data = new ArrayList<>();
    OnItemClickListener mOnItemClickListener;

    public CustommRecyclerViewAdapter(Context context, List<String> pic_data, List<CommentBean> comment_data) {
        this.context = context;
        this.pic_data = pic_data;
        this.comment_data = comment_data;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder mViewHolder = null;
        if (viewType == ITEM_TYPE.ITEM_TYPE_PIC.ordinal()) {
            View view = LayoutInflater.from(context).inflate(R.layout.module_rlv_pic, null);
            mViewHolder = new PicViewHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_COMMENT.ordinal()) {
            View view = LayoutInflater.from(context).inflate(R.layout.module_rlv_comment, null);
            mViewHolder = new CommentViewHolder(view);
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PicViewHolder) {
            ((PicViewHolder) holder).iv_pic.setImageResource(R.mipmap.timg);
        } else if (holder instanceof CommentViewHolder) {
            final int now_position = position - 1;
            ((CommentViewHolder) holder).tv_person.setText(comment_data.get(position - 1).getPerson());
            ((CommentViewHolder) holder).tv_comment.setText(comment_data.get(position - 1).getComment());
            ((CommentViewHolder) holder).tv_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClick(((CommentViewHolder) holder).tv_comment, now_position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_TYPE.ITEM_TYPE_PIC.ordinal() : ITEM_TYPE.ITEM_TYPE_COMMENT.ordinal();
    }

    @Override
    public int getItemCount() {
        int count = 1;
        if (comment_data != null) {
            count += comment_data.size();
        }
        return count;
    }

    public static enum ITEM_TYPE {
        ITEM_TYPE_PIC,
        ITEM_TYPE_COMMENT
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    //图片的viewholder
    class PicViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_pic;

        public PicViewHolder(View itemView) {
            super(itemView);
            iv_pic = itemView.findViewById(R.id.iv_pic);
        }
    }

    //评论的viewholder
    class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView tv_person;
        TextView tv_comment;

        public CommentViewHolder(View itemView) {
            super(itemView);
            tv_person = itemView.findViewById(R.id.tv_person);
            tv_comment = itemView.findViewById(R.id.tv_comment);
        }
    }
}
