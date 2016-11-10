package com.yuan.mymusic.ui.msicList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuan.mymusic.R;
import com.yuan.mymusic.base.BaseActivity;
import com.yuan.mymusic.bean.Folder;
import com.yuan.mymusic.bean.MusicFiles;
import com.yuan.mymusic.ui.msicList.adapter.MusicListAdapter;
import com.yuan.mymusic.ui.msicList.presenter.MusicListPresenter;
import com.yuan.mymusic.ui.msicList.presenter.MusicListPresenterImpl;
import com.yuan.mymusic.ui.msicList.view.MusicListView;
import com.yuan.mymusic.ui.play.PlayActivity;
import com.yuan.mymusic.utils.util.DensityUtil;
import com.yuan.mymusic.widget.SpacesItemDecoration;
import com.yuan.mymusic.widget.pulltorefres.library.extras.recyclerview.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

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

        musicFiles = (List<MusicFiles>) getIntent().getSerializableExtra("list");
        //list.addAll(musicListPresenter.setPath(path));
        initView();
    }

    private void initView() {
        PullToRefreshRecyclerView pullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.list_music_recycler);
        RecyclerView recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        musicListAdapter = new MusicListAdapter(musicFiles, MusicListActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MusicListActivity.this));
        SpacesItemDecoration decoration = new SpacesItemDecoration(DensityUtil.dip2px(MusicListActivity.this, 5));
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(musicListAdapter);
        musicListAdapter.setOnClickListener(new MusicListAdapter.ClickListener() {
            @Override
            public void onClickListener(View view) {
                Intent intent = new Intent(MusicListActivity.this, PlayActivity.class);
                startActivity(intent);
            }
        });
    }
}
