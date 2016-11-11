package com.yuan.music.ui.play;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuan.music.R;
import com.yuan.music.base.BaseActivity;
import com.yuan.music.bean.MusicFiles;
import com.yuan.music.ui.play.view.PlayView;

import java.util.List;

/**
 * 音乐播放的界面页面
 */
public class PlayActivity extends BaseActivity implements PlayView {
    private final String TAG = "PlayActivity: ";
    private List<MusicFiles> musicFiles;//播放的音乐列表
    private int position;//用户选中的播放歌曲

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        musicFiles = (List<MusicFiles>) getIntent().getSerializableExtra("music");
        position = getIntent().getIntExtra("position", 0);
        initView();
    }

    private void initView() {
        ImageView leftMusic = (ImageView) findViewById(R.id.play_left_music);
        CheckBox play = (CheckBox) findViewById(R.id.play_check_music);
        ImageView rightMusic = (ImageView) findViewById(R.id.play_right_music);
        ImageView leftImage = (ImageView) findViewById(R.id.play_image_left);
        ImageView rightImage = (ImageView) findViewById(R.id.play_image_right);
        TextView creatorTime = (TextView) findViewById(R.id.play_text_creator_time);
        TextView stopTime = (TextView) findViewById(R.id.play_text_stop_time);
        ImageView view = (ImageView) findViewById(R.id.play_backGroup);
        ImageView icon = (ImageView) findViewById(R.id.play_image_icon);
        TextView musicName = (TextView) findViewById(R.id.play_text_musicName);
        TextView singer = (TextView) findViewById(R.id.play_text_singer);
        View collect = findViewById(R.id.play_image_collect);
        View share = findViewById(R.id.play_image_share);
        AppCompatSeekBar seekBar = (AppCompatSeekBar) findViewById(R.id.play_seek_bar);
        //音乐歌手写真，判断是否存在，不存在就显示默认的界面
        if (musicFiles.get(position).getArtistPhotoURL() != null) {
            Glide.with(PlayActivity.this).load(musicFiles.get(position).getArtistPhotoURL()).fitCenter().error(R.mipmap.collect).into(view);
        } else if (musicFiles.get(position).getArtistPhotoURLFile() != null) {
            Glide.with(PlayActivity.this).load(musicFiles.get(position).getArtistPhotoURLFile()).fitCenter().error(R.mipmap.collect).into(view);
        } else {
            Glide.with(PlayActivity.this).load(R.mipmap.collect_w).fitCenter().error(R.mipmap.collect).into(view);
        }
        //点击事件的汇总
        icon.setOnClickListener(onClickListener);
        musicName.setOnClickListener(onClickListener);
        singer.setOnClickListener(onClickListener);
        collect.setOnClickListener(onClickListener);
        share.setOnClickListener(onClickListener);
        leftImage.setOnClickListener(onClickListener);
        leftMusic.setOnClickListener(onClickListener);
        play.setOnClickListener(onClickListener);
        rightMusic.setOnClickListener(onClickListener);
        rightImage.setOnClickListener(onClickListener);
        creatorTime.setOnClickListener(onClickListener);
        stopTime.setOnClickListener(onClickListener);
        //单独的进度条点击事件
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.play_left_music:

                    break;
                case R.id.play_check_music:

                    break;
                case R.id.play_right_music:

                    break;
                case R.id.play_image_left:

                    break;
                case R.id.play_image_right:

                    break;
                case R.id.play_text_creator_time:

                    break;
                case R.id.play_text_stop_time:

                    break;
                case R.id.play_image_icon:

                    break;
                case R.id.play_text_musicName:

                    break;
                case R.id.play_text_singer:

                    break;
                case R.id.play_image_collect:

                    break;
                case R.id.play_image_share:

                    break;
            }
        }
    };
}
