package com.yuan.music.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuan.music.R;
import com.yuan.music.bean.Folder;

import java.util.List;

/**
 * Created by YUAN on 2016/11/2.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final String TAG = "HomeAdapter";
    private List<Folder> list;
    private Context context;
    private ItemClickListener itemClickListener;
    private ItemLongClickListener itemLongClickListener;

    public HomeAdapter(List<Folder> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setData(List<Folder> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_recyclew, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.name = (TextView) view.findViewById(R.id.item_home_text);
        viewHolder.view = view.findViewById(R.id.item_home_layout);
        viewHolder.backGround = (ImageView) view.findViewById(R.id.item_home_image);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getFileName());
        Glide.with(context).load(list.get(position).getPhoto()).centerCrop().error(R.mipmap.ic_launcher).into(holder.backGround);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClickListener(holder.view, position);
            }
        });
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                itemLongClickListener.onItemLongClickListener(v, position);
                return true;
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(ItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        View view;
        ImageView backGround;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setItemLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public interface ItemLongClickListener {
        void onItemLongClickListener(View view, int position);
    }
}
