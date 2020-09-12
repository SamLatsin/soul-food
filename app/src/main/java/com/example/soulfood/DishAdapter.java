package com.example.soulfood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {
    private ArrayList<DishItem> mDishList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onButtonClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public ImageButton mImageButton;

        public ViewHolder(View ItemView, final OnItemClickListener listener) {
            super(ItemView);
            mImageView = itemView.findViewById(R.id.dishImage);
            mTextView1 = itemView.findViewById(R.id.dishName);
            mTextView2 = itemView.findViewById(R.id.dishDesc);
            mTextView3 = itemView.findViewById(R.id.dishPrice);
            mImageButton = ItemView.findViewById(R.id.imageButton3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            mImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onButtonClick(position);
                        }
                    }
                }
            });
        }
    }

    public DishAdapter(ArrayList<DishItem> dishList) {
        mDishList = dishList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item, parent, false);
        ViewHolder evh = new ViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DishItem currentItem = mDishList.get(position);
        holder.mImageView.setImageResource(currentItem.getPhotoId());
        holder.mTextView1.setText(currentItem.getDishName());
        holder.mTextView2.setText(currentItem.getDishDesc());
        holder.mTextView3.setText("$" + Integer.toString(currentItem.getDishPrice()));

    }

    @Override
    public int getItemCount() {
        return mDishList.size();
    }
}
