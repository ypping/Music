package com.yuan.music.ui.msicList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuan.music.R;
import com.yuan.music.base.BaseActivity;
import com.yuan.music.bean.Folder;
import com.yuan.music.bean.MusicFiles;
import com.yuan.music.ui.msicList.adapter.MusicListAdapter;
import com.yuan.music.ui.msicList.presenter.MusicListPresenter;
import com.yuan.music.ui.msicList.presenter.MusicListPresenterImpl;
import com.yuan.music.ui.msicList.view.MusicListView;
import com.yuan.music.ui.play.PlayActivity;
import com.yuan.music.utils.util.DensityUtil;
import com.yuan.music.widget.SpacesItemDecoration;
import com.yuan.music.widget.pulltorefres.library.extras.recyclerview.PullToRefreshRecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 播放页面
 *
 * @author YUAN
 * @see 2016/11/10
 */
public class MusicListActivity extends BaseActivity implements MusicListView {
    private final String TAG = "MusicListActivity";
    private MusicListAdapter musicListAdapter;
    private MusicListPresenter musicListPresenter = new MusicListPresenterImpl(this);
    private List<Folder> list = new ArrayList<>();
    private List<MusicFiles> musicFiles = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        //list = (List<Folder>) getIntent().getSerializableExtra("list");
        musicFiles.addAll(musicListPresenter.setPath(MusicListActivity.this, getIntent().getStringExtra("path")));
        //musicFiles = (List<MusicFiles>) getIntent().getSerializableExtra("list");
        initView();
    }

    private void initView() {
        final PullToRefreshRecyclerView pullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.list_music_recycler);
        RecyclerView recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        musicListAdapter = new MusicListAdapter(musicFiles, MusicListActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MusicListActivity.this));
        SpacesItemDecoration decoration = new SpacesItemDecoration(DensityUtil.dip2px(MusicListActivity.this, 5));
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(musicListAdapter);
        musicListAdapter.setOnClickListener(new MusicListAdapter.ClickListener() {
            @Override
            public void onClickListener(View view, int position) {
                Intent intent = new Intent(MusicListActivity.this, PlayActivity.class);
                intent.putExtra("music", (Serializable) musicFiles);
                intent.putExtra("position", position);

                startActivity(intent);
            }
        });
    }
}
