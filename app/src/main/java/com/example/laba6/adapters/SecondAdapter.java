package com.example.laba6.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laba6.R;
import com.example.laba6.db.entities.ProductEntity;

import java.util.List;


public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ExampleViewHolder> {

    private List<ProductEntity> models;

    private OnProductClickListener onProductClickListener;

    public SecondAdapter(List<ProductEntity>newModels, OnProductClickListener onProductClickListener) {

        models = newModels;
        this.onProductClickListener = onProductClickListener;

    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_card, parent, false);
        return new ExampleViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, final int position) {

        final ProductEntity currentItem = models.get(position);

        String name = currentItem.getName();
        String description = currentItem.getDescription();
        int count = currentItem.getCount();
        int price = currentItem.getPrice();
        holder.nameTextView.setText("Name: " + name);
        holder.descriptionTextView.setText("Description: " + description);
        holder.countTextView.setText("Count: " + String.valueOf(count));
        holder.priceTextView.setText("Price: " + price + " $");

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        TextView descriptionTextView;
        TextView priceTextView;
        TextView countTextView;
        RelativeLayout relativeLayout;

        ImageButton imgViewRemoveIcon;
        ImageButton imgViewEditIcon;

        ExampleViewHolder(View itemView) {
            super(itemView);

            imgViewRemoveIcon = itemView.findViewById(R.id.crossButton);
            imgViewEditIcon = itemView.findViewById(R.id.editButton);
            nameTextView =  itemView.findViewById(R.id.mainSecondAdapterName);
            descriptionTextView = itemView.findViewById(R.id.mainSecondAdapterDescription);
            countTextView = itemView.findViewById(R.id.mainSecondAdapterCount);
            priceTextView = itemView.findViewById(R.id.mainSecondAdapterPrice);

            relativeLayout = itemView.findViewById(R.id.relativeAdminPanel);

            imgViewRemoveIcon.setOnClickListener(v -> onProductClickListener.onProductDeleteCLick(models.get(getAdapterPosition()).getId()));
            imgViewEditIcon.setOnClickListener(v -> onProductClickListener.onProductEditClick(models.get(getAdapterPosition()).getId()));

        }

        @Override
        public void onClick(View v) {

        }
    }

    public interface OnProductClickListener{
        void onProductDeleteCLick(int id);
        void onProductEditClick(int id);
    }

}

