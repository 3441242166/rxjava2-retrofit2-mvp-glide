package com.example.wanhao.apiapp.fragment;

import android.app.ProgressDialog;
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
import com.example.wanhao.apiapp.iview.IMainView;
import com.example.wanhao.apiapp.presenter.MainPresenter;
import com.youth.banner.Banner;

import java.util.List;


/**
 * Created by wanhao on 2017/10/3.
 */

public class MainFragment extends Fragment implements IMainView{
    private static final String TAG = "MainFragment";

    private View view;
    private MainPresenter presenter;
    private MessageItemAdapter adapter;

    ProgressDialog dialog;
    SwipeRefreshLayout swipeRefreshLayout;
    Banner banner;
    RecyclerView recyclerView;
    boolean isLodingComaplete = false;

    List<Message> list;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        initData();
        initEvent();
        presenter.getListData();
        return view;
    }

    private void initData() {
        recyclerView = (RecyclerView)view.findViewById(R.id.main_recycler);
        swipeRefreshLayout =(SwipeRefreshLayout) view.findViewById(R.id.main_refresh);

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
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url",list.get(position).getContantUrl());
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isLodingComaplete) {
                    presenter.getListData();
                    isLodingComaplete = false;
                    swipeRefreshLayout.setRefreshing(false);
                }else{
                    Toast.makeText(getActivity(),"不用刷新这么快哦",Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void showProgress() {
        dialog = new ProgressDialog(getActivity());
        dialog.show();
    }

    @Override
    public void disimissProgress() {
        Log.i(TAG, "disimissProgress: ");
        presenter.getImage(list);
        dialog.cancel();
    }

    @Override
    public void loadDataSuccess(List<Message> tData) {
        list = tData;
        for(int x=0;x<tData.size();x++){
            Log.i(TAG, "accept: "+tData.get(x).getTitle());
        }
        adapter.setData(list);
    }

    @Override
    public void loadDataError(Throwable throwable) {
        Toast.makeText(getActivity(),throwable.toString(),Toast.LENGTH_SHORT).show();
    }


    @Override
    public void ImageComplete() {
        isLodingComaplete = true;
    }
}
