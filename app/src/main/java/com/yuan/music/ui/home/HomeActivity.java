package com.yuan.music.ui.home;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yuan.music.R;
import com.yuan.music.base.BaseActivity;
import com.yuan.music.bean.Folder;
import com.yuan.music.ui.home.adapter.HomeAdapter;
import com.yuan.music.ui.home.presenter.HomePresenter;
import com.yuan.music.ui.home.presenter.HomePresenterImpl;
import com.yuan.music.ui.home.view.HomeView;
import com.yuan.music.ui.msicList.MusicListActivity;
import com.yuan.music.utils.util.DensityUtil;
import com.yuan.music.utils.util.PhotoUtils;
import com.yuan.music.widget.SpacesItemDecoration;
import com.yuan.music.widget.pulltorefres.library.extras.recyclerview.PullToRefreshRecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeView {
    private final String TAG = "HomeActivity";
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private HomeAdapter homeAdapter;
    private List<Folder> list = new ArrayList<>();
    private HomePresenter homePresenter = new HomePresenterImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.home_recyclew);
        RecyclerView recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        recyclerView.setLayoutManager(new GridLayoutManager(HomeActivity.this, 2));
        SpacesItemDecoration decoration = new SpacesItemDecoration(DensityUtil.dip2px(HomeActivity.this, 10));
        recyclerView.addItemDecoration(decoration);
        list = homePresenter.getData(HomeActivity.this, list);
        homeAdapter = new HomeAdapter(list, this);
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener(new HomeAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Intent intent = new Intent(HomeActivity.this, MusicListActivity.class);
                intent.putExtra("list", (Serializable) list.get(position).getMusicFiles());
                intent.putExtra("path", list.get(position).getPath());
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        homeAdapter.setItemLongClickListener(new HomeAdapter.ItemLongClickListener() {
            @Override
            public void onItemLongClickListener(View view, int position) {
                Intent intent;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                } else {
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                }
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, position);
                setSetPhotoPath(new SetPhotoPath() {
                    @Override
                    public void photoPath(String path) {

                    }
                });
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, TAG + ": requestCode:" + requestCode + "  resultCode:" + resultCode + " data:" + data);

        homeAdapter.setData(list);
        PhotoUtils.getPhotoURLByAlbum(HomeActivity.this, data, new PhotoUtils.PhotoBack() {
            @Override
            public void onSuccess(String picturePath) {
                Log.i("picturePath", picturePath);
                setPhotoPath.photoPath(picturePath);
                list.get(requestCode).setPhoto(picturePath);
            }

            @Override
            public void onFailure() {
                Toast.makeText(HomeActivity.this, "图片获取失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private interface SetPhotoPath {
        void photoPath(String path);
    }

    private SetPhotoPath setPhotoPath;

    private void setSetPhotoPath(SetPhotoPath setPhotoPath) {
        this.setPhotoPath = setPhotoPath;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connection != null) {
            //  unbindService(connection);
        }
    }
}
