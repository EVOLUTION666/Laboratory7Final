package com.example.laba6.adapters;


import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.laba6.R;
import com.example.laba6.db.entities.ProductEntity;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ExampleViewHolder> {

    private List<ProductEntity>models;

    private OnProductClickListener onProductClickListener;

    public MainAdapter(List<ProductEntity>newModels, OnProductClickListener onProductClickListener) {

        models = newModels;

        this.onProductClickListener = onProductClickListener;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ExampleViewHolder(v);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, final int position) {
        ProductEntity currentItem = models.get(position);
        String name = currentItem.getName();
        String description = currentItem.getDescription();
        int count = currentItem.getCount();
        int price = currentItem.getPrice();
        holder.nameTextView.setText(name);
        holder.descriptionTextView.setText("Description: " + description);
        holder.countTextView.setText("Count: " + count);
        holder.priceTextView.setText("Price: " + price + " $");

    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        TextView priceTextView;
        TextView countTextView;
        TextView descriptionTextView;

        Button addToOrder;

        ExampleViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.mainAdapterName);
            descriptionTextView=itemView.findViewById(R.id.mainAdapterDescription);
            countTextView=itemView.findViewById(R.id.mainAdapterCount);
            priceTextView=itemView.findViewById(R.id.mainAdapterPrice);

            addToOrder = itemView.findViewById(R.id.addToCart);

            addToOrder.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onProductClickListener.addCartClickListener(models.get(getAbsoluteAdapterPosition()).getId());
        }
    }


    public interface OnProductClickListener{
        void addCartClickListener(int id);
    }

}

