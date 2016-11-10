package com.yuan.mymusic.ui.play;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yuan.mymusic.R;
import com.yuan.mymusic.base.BaseActivity;
import com.yuan.mymusic.ui.play.view.PlayView;

public class PlayActivity extends BaseActivity implements PlayView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
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
        AppCompatSeekBar seekBar = (AppCompatSeekBar) findViewById(R.id.play_seek_bar);
        leftImage.setOnClickListener(onClickListener);
        leftMusic.setOnClickListener(onClickListener);
        play.setOnClickListener(onClickListener);
        rightMusic.setOnClickListener(onClickListener);
        rightImage.setOnClickListener(onClickListener);
        creatorTime.setOnClickListener(onClickListener);
        stopTime.setOnClickListener(onClickListener);
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
            }
        }
    };
}
