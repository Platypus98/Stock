package com.example.stock;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class StockFragment extends Fragment {

    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
    List<Good> goods = new ArrayList<>();

    MyDatabaseHelper db;
    TextView tv;
    MenuItem fav;
    FloatingActionButton fabBtn;

    public static StockFragment newInstance() {
        StockFragment fragment = new StockFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.fragment_stock, container, false);

        fabBtn = RootView.findViewById((R.id.fab));
        fabBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddGoodActivity.class);
                startActivity(intent);
            }
        });

        db = new MyDatabaseHelper(this.getActivity());

        setGoodsData();
        recyclerView = (RecyclerView) RootView.findViewById(R.id.goods_recycler_view);

        // use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // specify an adapter (see also next example)
        mAdapter = new DataAdapter(this.getActivity(), goods);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Toast.makeText(getContext(), "Название: " + goods.get(position).getName() + " Количество: "+ goods.get(position).getQuantity() + " Цена продажи: " + goods.get(position).getSellingPrice() + " Цена закупки: " + goods.get(position).getPurchasePrice(), Toast.LENGTH_SHORT).show();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), UpdateGoodActivity.class);
                        intent.putExtra("oldGoodName", goods.get(position).getName());
                        startActivity(intent);
                    }
                })
        );
        return RootView;
    }

    private void setGoodsData(){
        goods = db.getAllGoods();
    }

    @Override
    public void onResume() {
        super.onResume();
        setGoodsData();
        mAdapter.updateData(goods);
    }
}
