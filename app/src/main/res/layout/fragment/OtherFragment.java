package com.example.wanhao.tasktool.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



/**
 * Created by wanhao on 2017/10/3.
 */

public class OtherFragment extends Fragment {
    private View view;
    private Button addWord;
    private Button addTask;
    private Button addTip;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_other, container, false);
        initView();
        initEvent();
        return view;
    }

    private void initEvent() {

    }

    private void initView() {

    }
}
