package com.example.laba6.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.laba6.R;
import com.example.laba6.db.entities.OrderEntity;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ExampleViewHolder> {

    private List<OrderEntity>models;

    public CartAdapter(List<OrderEntity>newModels) {

        models = newModels;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart, parent, false);
        return new ExampleViewHolder(v);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, final int position) {
        OrderEntity currentItem = models.get(position);
        String name = currentItem.getName();
        String description = currentItem.getDescription();
        int count = currentItem.getCount();
        int price = currentItem.getPrice();
        holder.nameTextView.setText(name);
        holder.descriptionTextView.setText(description);
        holder.countTextView.setText(String.valueOf(count));
//        holder.priceTextView.setText(price + " $");

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class ExampleViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView priceTextView;
        TextView countTextView;
        TextView descriptionTextView;

        ExampleViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.mainAdapterName);
            descriptionTextView=itemView.findViewById(R.id.mainAdapterDescription);
            countTextView=itemView.findViewById(R.id.mainAdapterCount);
            priceTextView=itemView.findViewById(R.id.mainAdapterPrice);

        }

    }

}

