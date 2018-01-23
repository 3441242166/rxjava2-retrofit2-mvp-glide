package com.example.wanhao.apiapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wanhao.apiapp.R;
import com.example.wanhao.apiapp.activity.WebActivity;
import com.example.wanhao.apiapp.adapter.MessageItemAdapter;
import com.example.wanhao.apiapp.bean.Message;
import com.example.wanhao.apiapp.helper.ImageLoader;
import com.example.wanhao.apiapp.iview.IMainView;
import com.example.wanhao.apiapp.presenter.MainPresenter;
import com.example.wanhao.apiapp.sqlite.HistoryDao;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wanhao on 2017/10/3.
 */

public class MainFragment extends Fragment implements IMainView{
    private static final String TAG = "MainFragment";

    private View view;
    private MainPresenter presenter;
    private MessageItemAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    Banner banner;
    RecyclerView recyclerView;
    HistoryDao historyDao;
    //记录图片是否加载完成
    boolean isLodingComaplete = false;

    List<Message> list;
    List<Message> bannerList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        initData();
        initEvent();
        presenter.getListData();
        presenter.getBannerData();
        return view;
    }

    private void initData() {
        recyclerView = (RecyclerView)view.findViewById(R.id.main_recycler);
        swipeRefreshLayout =(SwipeRefreshLayout) view.findViewById(R.id.main_refresh);
        banner = (Banner) view.findViewById(R.id.main_banner);

        banner.setImageLoader(new ImageLoader());
        //banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);

        historyDao = new HistoryDao(getActivity());

        presenter = new MainPresenter(this,getContext());
        adapter = new MessageItemAdapter(getActivity());
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void initEvent() {
        adapter.setOnItemClickListener(new MessageItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //保存在历史记录中
                presenter.saveHistory(list.get(position));
                //打开webActivity
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url",list.get(position).getContantUrl());
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isLodingComaplete) {
                    //从网络获取数据
                    presenter.getDataByInternet();
                    isLodingComaplete = false;
                }else{
                    Toast.makeText(getActivity(),"加载完再刷新哦",Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //打开webActivity
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url",bannerList.get(position).getContantUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    //加载完成列表 开始加载图片
    public void disimissProgress() {
        Log.i(TAG, "disimissProgress: ");
        presenter.getImage(list);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    //列表更新
    public void loadDataSuccess(List<Message> tData) {
        list = tData;
        adapter.setData(list);
    }

    @Override
    public void loadDataError(Throwable throwable) {
        Toast.makeText(getActivity(),throwable.toString(),Toast.LENGTH_SHORT).show();
        isLodingComaplete = true;
        swipeRefreshLayout.setRefreshing(false);
    }
    @Override
    //全部加载完毕
    public void ImageComplete() {
        isLodingComaplete = true;
    }
    @Override
    public void bannerComplete(final List<Message> list){
        bannerList = list;
        List<String> bitmaps = new ArrayList<>();
        for(int x=0;x<list.size();x++){
            bitmaps.add(list.get(x).getImageUrl());
        }
        banner.setImages(bitmaps);
        banner.start();
    }
}
