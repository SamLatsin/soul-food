package com.example.soulfood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {
    private ArrayList<DishItem> mDishList;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public ViewHolder(View ItemView) {
            super(ItemView);
            mImageView = itemView.findViewById(R.id.dishImage);
            mTextView1 = itemView.findViewById(R.id.dishName);
            mTextView2 = itemView.findViewById(R.id.dishDesc);
            mTextView3 = itemView.findViewById(R.id.dishPrice);
        }
    }

    public DishAdapter(ArrayList<DishItem> dishList) {
        mDishList = dishList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item, parent, false);
        ViewHolder evh = new ViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DishItem currentItem = mDishList.get(position);
        holder.mImageView.setImageResource(currentItem.getPhotoId());
        holder.mTextView1.setText(currentItem.getDishName());
        holder.mTextView2.setText(currentItem.getDishDesc());
        holder.mTextView3.setText(currentItem.getDishPrice());
    }

    @Override
    public int getItemCount() {
        return mDishList.size();
    }
}
