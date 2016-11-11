package com.yuan.music.ui.msicList.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuan.music.R;
import com.yuan.music.bean.MusicFiles;
import com.yuan.music.utils.FileUtils;
import com.yuan.music.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by YUAN on 2016/11/4.
 */

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {
    private final String TAG = "MusicListAdapter";
    private List<MusicFiles> list;
    private Context context;
    private ClickListener clickListener;

    public MusicListAdapter(List<MusicFiles> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setData(List<MusicFiles> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_music, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getArtistPhotoURLFile()).fitCenter().error(R.mipmap.ic_launcher).into(holder.icon);
        holder.songName.setText(list.get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClickListener(v, position);
            }
        });
        holder.singerName.setText(list.get(position).getArtist());
        holder.songSize.setText(FileUtils.getCacheLengthStr(list.get(position).getSize()));
        holder.songTime.setText(String.valueOf(TimeUtils.milliseconds2String(list.get(position).getTime(), new SimpleDateFormat("mm:ss"))));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView songName, singerName, songTime, songSize;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.item_list_music_view);
            icon = (ImageView) itemView.findViewById(R.id.item_list_music_icon);
            songName = (TextView) itemView.findViewById(R.id.item_list_music_songName);
            singerName = (TextView) itemView.findViewById(R.id.item_list_music_singerName);
            songTime = (TextView) itemView.findViewById(R.id.item_list_music_songTime);
            songSize = (TextView) itemView.findViewById(R.id.item_list_music_songSize);
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    public void setOnClickListener(ClickListener onClickListener) {
        this.clickListener = onClickListener;
    }

    public interface ClickListener {
        void onClickListener(View view, int position);
    }

}
