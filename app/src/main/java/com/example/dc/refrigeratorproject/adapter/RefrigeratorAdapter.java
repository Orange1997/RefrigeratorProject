package com.example.dc.refrigeratorproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.dc.refrigeratorproject.R;
import com.example.dc.refrigeratorproject.model.FoodBean;
import com.example.dc.refrigeratorproject.model.RefrigeratorBean;
import com.example.dc.refrigeratorproject.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DC on 2019/3/8.
 */


public class RefrigeratorAdapter extends RecyclerView.Adapter<RefrigeratorAdapter.ViewHolder> {
    private static int ITEM_GRID_COLUMNS;//每一页中GridView中item的数量

    private Context context;

    private List<RefrigeratorBean> data;

    private FoodVpAdapter foodVpAdapter;

    private List<GridView> gridList = new ArrayList<>();


    public RefrigeratorAdapter(Context context) {

        this.context = context;

        ITEM_GRID_COLUMNS =
                ScreenUtils.getScreenWidth(context) / context.getResources().getDimensionPixelOffset(R.dimen.item_food_grid_width);

    }

    public void updateList(List<RefrigeratorBean> data) {
        this.data = data;
    }


    private void initVpViews(int position) {
        if (gridList.size() > 0) {
            gridList.clear();
        }

        int gridSize = data.get(position).getFoodBeanList().size();
        int pageSize = gridSize % ITEM_GRID_COLUMNS == 0
                ? gridSize / ITEM_GRID_COLUMNS
                : gridSize / ITEM_GRID_COLUMNS + 1;

        for (int i = 0; i < pageSize; i++) {
            GridView gridView = (GridView) View.inflate(context, R.layout.item_grid, null);
            List<FoodBean> list = new ArrayList<>();
            for (int j = 0; j < ITEM_GRID_COLUMNS; j++) {
                if (j + ITEM_GRID_COLUMNS * i < gridSize) {
                    list.add(data.get(position).getFoodBeanList().get(j + ITEM_GRID_COLUMNS * i));
                }
            }
            FoodGvAdapter foodGvAdapter = new FoodGvAdapter(list);
            gridView.setNumColumns(ITEM_GRID_COLUMNS);
            gridView.setAdapter(foodGvAdapter);
            gridList.add(gridView);
        }
        foodVpAdapter.add(gridList);
    }


    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_refrigerator, parent, false);

        return new ViewHolder(view);

    }


    @Override

    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvKind.setText(data.get(position).getKind());
        initVpViews(position);
//        foodAdapter.updateList (data.get (position).getFoodBeanList ());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//
//            public void onClick(View v) {
//
//
//            }
//
//        });


    }


    @Override

    public int getItemCount() {

        return data.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        private RecyclerView rvItem;
        private TextView tvKind;
        private ViewPager vpFood;


        public ViewHolder(View itemView) {

            super(itemView);
            tvKind = itemView.findViewById(R.id.tv_kind);
            vpFood = itemView.findViewById(R.id.vp_food);
            foodVpAdapter = new FoodVpAdapter();
            vpFood.setAdapter(foodVpAdapter);
        }

    }

}

