package com.example.dc.refrigeratorproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dc.refrigeratorproject.R;

/**
 * Created by DC on 2019/3/8.
 */

public class ListFragment extends Fragment{
    private static final String TAG = "MyFragment1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        Log.i(TAG, "onCreateView: 0000--------MyFragment1");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: 0000-------MyFragment1");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: 0000--------MyFragment1");
    }

    public ListFragment() {
    }
}
