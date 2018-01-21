package com.example.wanhao.tasktool.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.wanhao.tasktool.R;
import com.example.wanhao.tasktool.SQLite.TaskDao;
import com.example.wanhao.tasktool.activity.AddTaskActivity;
import com.example.wanhao.tasktool.activity.SearchWordActivity;
import com.example.wanhao.tasktool.activity.TaskListActivity;
import com.example.wanhao.tasktool.activity.WordListActivity;
import com.example.wanhao.tasktool.bean.Task;
import com.example.wanhao.tasktool.tool.Constant;
import com.example.wanhao.tasktool.tool.MyDate;

import java.util.List;


/**
 * Created by wanhao on 2017/10/3.
 */

public class MainFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        initView();
        initEvent();

        return view;
    }

    private void initData() {

    }

    private void initEvent() {

    }

    private void initView() {

    }

}
