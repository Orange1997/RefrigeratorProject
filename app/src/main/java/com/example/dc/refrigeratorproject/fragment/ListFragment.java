package com.example.dc.refrigeratorproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dc.refrigeratorproject.Mock;
import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.adapter.ListAdapter;
import com.example.dc.refrigeratorproject.adapter.item.BaseItem;
import com.example.dc.refrigeratorproject.adapter.item.HeadItem;
import com.example.dc.refrigeratorproject.adapter.item.ListItem;
import com.example.dc.refrigeratorproject.model.ListModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.dc.refrigeratorproject.adapter.ListAdapter.TYPE_HEAD;
import static com.example.dc.refrigeratorproject.adapter.ListAdapter.TYPE_LIST;

/**
 * Created by DC on 2019/3/8.
 */

public class ListFragment extends Fragment{
    private static final String TAG = "MyFragment1";

    private RecyclerView rvList;
    private ListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rvList = view.findViewById (R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager (getActivity ());
        layoutManager.setOrientation (OrientationHelper.VERTICAL);
        rvList.setLayoutManager (layoutManager);
        adapter = new ListAdapter (getActivity ());
        rvList.setAdapter (adapter);
        adapter.setOnListItemClickListener (new ListAdapter.OnListItemClickListener () {
            @Override
            public void onItemClick(ListItem item) {

            }
        });
    }

    private void updateList(List<ListModel> listModelList){
        List<BaseItem> baseItems = new ArrayList<> ();
        baseItems.add (new HeadItem (TYPE_HEAD));
        for (ListModel listModel:listModelList){
            baseItems.add (new ListItem (listModel,TYPE_LIST));
        }
        adapter.updateList (baseItems);
    }

    @Override
    public void onResume(){
        super.onResume ();
        updateList (Mock.getListModelList ());
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
