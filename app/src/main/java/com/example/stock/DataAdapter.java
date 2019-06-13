package com.example.stock;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Good> goods;

    DataAdapter(Context context, List<Good> goods) {
        this.goods = goods;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_goods, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Good good = goods.get(position);
        holder.nameView.setText(good.getName());
        holder.quantityView.setText(good.getQuantity());
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }


    public void updateData(List<Good> goods)
    {
        this.goods = goods;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, quantityView;
        ViewHolder(View view){
            super(view);
            nameView = (TextView) view.findViewById(R.id.name);
            quantityView = view.findViewById(R.id.quantity);
        }
    }
}
